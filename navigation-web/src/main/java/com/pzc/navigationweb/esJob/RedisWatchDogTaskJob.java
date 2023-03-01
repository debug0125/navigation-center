package com.pzc.navigationweb.esJob;

import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.constant.RedisLockDefinitionHolder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ryf
 * @date 10/13/22 1:58 PM
 */
@Component
public class RedisWatchDogTaskJob {

    public static ConcurrentLinkedQueue<RedisLockDefinitionHolder> holderList = new ConcurrentLinkedQueue();

    private static final ScheduledExecutorService SCHEDULER =
            new ScheduledThreadPoolExecutor(1,
                    new BasicThreadFactory.Builder().namingPattern("redisLock-schedule-pool").daemon(true).build());

    static {
        Logger logger = LoggerFactory.getLogger("task");
        SCHEDULER.scheduleAtFixedRate(() -> {
            // 这里记得加 try-catch，否者报错后定时任务将不会再执行=-=

            try {
                Iterator<RedisLockDefinitionHolder> iterator = holderList.iterator();
                while (iterator.hasNext()) {
                    logger.info("RedisWatchDogTaskJob >>>>>>>> 检测到有分布式锁，看门狗启动.....");
                    RedisLockDefinitionHolder holder = iterator.next();
                    // 判空
                    if (holder == null) {
                        iterator.remove();
                        logger.info("无任务，取消");
                        continue;
                    }
                    // 判断 key 是否还有效，无效的话进行移除

                    if (RedisUtil.op().getV(holder.getBusinessKey()) == null) {
                        iterator.remove();
                        logger.info("无key，取消");
                        continue;
                    }
                    // 超时重试次数，超过时给线程设定中断
                    if (holder.getCurrentCount() > holder.getTryCount()) {
                        holder.getCurrentTread().interrupt();
                        iterator.remove();
                        logger.info("重试超过3次，取消该任务，释放锁.....");
                        continue;
                    }
                    // 判断是否进入最后三分之一时间
                    long curTime = System.currentTimeMillis();
                    boolean shouldExtend = (holder.getLastModifyTime() + holder.getModifyPeriod()) <= curTime;
                    if (shouldExtend) {
                        holder.setLastModifyTime(curTime);
                        RedisUtil.op().expireV(holder.getBusinessKey(), holder.getLockTime());
                        logger.info("businessKey : [" + holder.getBusinessKey() + "], try count : " + holder.getCurrentCount());
                        holder.setCurrentCount(holder.getCurrentCount() + 1);
                    }
                }
            } catch (Exception e) {
                logger.error("exception >>>>>> ", e);
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
