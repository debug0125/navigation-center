package com.pzc.navigationweb.application;

import com.alibaba.fastjson.JSON;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.constant.enumtype.CommonExceptionEnum;
import com.pzc.navigationweb.dto.basedto.ReqDTO;
import com.pzc.navigationweb.exception.IdempotentException;
import com.pzc.navigationweb.exception.InvokeRpcServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author ryf
 * @date 7/29/21 11:00 AM
 */
public abstract class DefaultProcess<P extends ReqDTO, M>  implements IProcess<P, M> {

    protected static Logger logger = LoggerFactory.getLogger("process");

    private long startTime;
    private long endTime;

    public DefaultProcess() {
    }

    public Logger getLogger() {
        return logger;
    }

    public void start(Context<P, M> context) {
        try {
            this.onStarted(context);
            this.process(context);
            this.onSuccess(context);
        } catch (IdempotentException var9) {
            String businessKey = var9.getBusinessKey();
            this.onIdempotent(context, businessKey);
        } catch (InvokeRpcServiceException var10) {
            this.onInvokeRpcServiceError(context, var10);
        } catch (Throwable var11) {
            this.onError(context, var11);
        } finally {
            this.onEnd(context);
        }

    }

    @Override
    public Result<M> start(P param) {
        Context<P, M> context = new Context(param, new Result());
        this.start(context);
        return context.getResult();
    }
    public void onStarted(Context<P, M> context) {
        this.startTime = (new Date()).getTime();
        (context.getParam()).validation();
    }
    public abstract void process(Context<P, M> var1);

    public void onSuccess(Context<P, M> context) {
        context.getResultBuilder().ѕetSuccess(true).build();
    }

    public void onIdempotent(Context<P, M> context, String businessKey) {
        context.getResultBuilder().ѕetSuccess(true).setErrCode(CommonExceptionEnum.IDEMPOTENT_INVOKE_ERROR.getErrCode()).setMessage(String.format(CommonExceptionEnum.IDEMPOTENT_INVOKE_ERROR.getErrMsg(), businessKey)).build();
    }

    public boolean customExceptionHandler(Context<P, M> context, Throwable e) {
        return false;
    }
    private void onInvokeRpcServiceError(Context<P, M> context, InvokeRpcServiceException invokeRpcServiceException) {
        context.getResultBuilder().ѕetSuccess(false).setErrCode(CommonExceptionEnum.INVOKE_RPC_SERVICE_ERROR.getErrCode()).setMessage(String.format(CommonExceptionEnum.INVOKE_RPC_SERVICE_ERROR.getErrMsg(), invokeRpcServiceException.getRpcServiceName(), invokeRpcServiceException.getMessage())).build();
        this.getLogger().error(invokeRpcServiceException.getMessage(), invokeRpcServiceException);
    }

    public void onError(Context<P, M> context, Throwable e) {
        if (!this.customExceptionHandler(context, e)) {
            context.getResultBuilder().ѕetSuccess(false).setErrCode(CommonExceptionEnum.INTERNAL_ERROR.getErrCode()).setMessage(e.getMessage()).build();
        }

        this.getLogger().error(e.getMessage(), e);
    }

    public void onEnd(Context<P, M> context) {
        this.endTime = (new Date()).getTime();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        String processName = this.getClass().getName();
        sb.append("********** " + processName + "**********\n");
        sb.append("param:\n");
        sb.append("    " + JSON.toJSON(context.getParam()) + "\n");
        Result result = context.getResult();
        if (!result.isSuccess()) {
            sb.append("errCode:\n");
            sb.append("    " + result.getErrCode() + "\n");
            sb.append("errMsg:\n");
            sb.append("    " + result.getErrMsg() + "\n");
            sb.append("result:\n");
            sb.append("    " + processName + " is fail\n");
        } else {
            sb.append("result:\n");
            sb.append(JSON.toJSONString(result) + "\n");
            sb.append("    " + processName + " is success\n");
        }

        long useTime = this.endTime - this.startTime;
        sb.append(processName).append(" useTime: ").append(useTime).append("ms \n");
        sb.append("**********");
        this.getLogger().info(sb.toString());
    }
}
