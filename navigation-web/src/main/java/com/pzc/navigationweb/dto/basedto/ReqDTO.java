package com.pzc.navigationweb.dto.basedto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ryf
 * @date 5/11/21 2:47 PM
 */
@Data
public class ReqDTO implements Serializable {
    private static final long serialVersionUID = -7649015500742126341L;

    private String id;
    private Date createDate;
    private String createName;
    private Date modifyDate;
    private String modifyName;
    private Integer version;
    private Boolean isDel;

}
