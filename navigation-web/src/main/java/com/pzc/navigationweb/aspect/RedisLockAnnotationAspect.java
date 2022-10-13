package com.pzc.navigationweb.aspect;

import com.pzc.navigationweb.aspect.annotation.RedisLockAnnotation;
import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.constant.RedisLockDefinitionHolder;
import com.pzc.navigationweb.constant.enumtype.RedisLockTypeEnum;
import com.pzc.navigationweb.esJob.RedisWatchDogTaskJob;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;
/**
 * @author ryf
 * @date 5/17/21 1:53 PM
 */
@Aspect
@Component
public class RedisLockAnnotationAspect {

    private Logger logger = LoggerFactory.getLogger("task");


    static Method resloveMothed(ProceedingJoinPoint joinPoint) {
        // 切面所在类
        Object target = joinPoint.getTarget();

        String methodName = joinPoint.getSignature().getName();

        Method method = null;
        for (Method m : target.getClass().getMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        return method;
    }

    @Pointcut("@annotation(function)")
    public void Pointcut(RedisLockAnnotation function) {
    }

    @Around(value = "Pointcut(function)", argNames = "joinPoint,function")
    public Object around(ProceedingJoinPoint joinPoint, RedisLockAnnotation function) throws Throwable {

        Method method = resloveMothed(joinPoint);
        RedisLockAnnotation annotation = method.getAnnotation(RedisLockAnnotation.class);
        RedisLockTypeEnum typeEnum = annotation.typeEnum();

        Object[] params = joinPoint.getArgs();
        String ukString = params[annotation.lockFiled()].toString();
        // 省略很多参数校验和判空
        String businessKey = typeEnum.getUniqueKey(ukString);
        String uniqueValue = UUID.randomUUID().toString();

        Object result = null;

        Thread currentThread = Thread.currentThread();
        try {
            boolean isSuccess = RedisUtil.op().setIfAbsent(businessKey,uniqueValue);
            if (!isSuccess) {
                throw new Exception("You can't do it，because another has get the lock =-=");
            }
            RedisUtil.op().expireV(businessKey, annotation.lockTime());

            RedisWatchDogTaskJob.holderList.add(new RedisLockDefinitionHolder(businessKey, annotation.lockTime(), System.currentTimeMillis(),
                    currentThread, annotation.tryCount()));

            result = joinPoint.proceed();

            if (currentThread.isInterrupted()) {
                throw new InterruptedException("You had been interrupted =-=");
            }
            RedisUtil.op().delV(businessKey);
            logger.info("release the lock, businessKey is [" + businessKey + "]");

        } catch (InterruptedException e) {
            logger.error("Interrupt exception, rollback transaction", e);
            throw new Exception("Interrupt exception, please send request again");

        } catch (Exception e) {
            logger.error("has some error, please check again", e);
        }
        return result;
    }
}
