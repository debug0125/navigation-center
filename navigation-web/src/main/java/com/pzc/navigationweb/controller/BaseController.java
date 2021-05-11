package com.pzc.navigationweb.controller;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author ryf
 * @date 5/8/21 6:24 PM
 */
public class BaseController {

    @ModelAttribute
    public void init() {
//        Logininfo logininfo = UserContext.getCurreentUser();
        RpcContext.getContext().setAttachment("username", "痞子橙");
    }
}
