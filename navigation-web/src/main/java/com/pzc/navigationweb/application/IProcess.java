package com.pzc.navigationweb.application;

import com.pzc.navigationweb.common.util.Result;

/**
 * @author ryf
 * @date 7/29/21 11:01 AM
 */
public interface IProcess<P, M> {
    Result<M> start(P var1);
}
