package com.pzc.navigationweb.model;

import lombok.Data;

/**
 * @author ryf
 * @date 5/10/21 3:27 PM
 */
@Data
public class RequestResourceData extends ResourceData {
    private static final long serialVersionUID = 1L;

    private byte[] fileData;
}
