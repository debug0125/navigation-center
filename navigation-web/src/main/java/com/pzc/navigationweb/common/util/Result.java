package com.pzc.navigationweb.common.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ryf
 * @date 5/12/21 3:20 PM
 */
@Data
public class Result<T>  implements Serializable {
    private static final long serialVersionUID = 1727773535408886961L;

    private T module;
    private String errCode;
    private String errMsg;
    private boolean success;
    private String successMsg;
}
