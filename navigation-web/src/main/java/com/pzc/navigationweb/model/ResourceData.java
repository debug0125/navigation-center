package com.pzc.navigationweb.model;

import com.pzc.navigationweb.constant.FileConstants;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ryf
 * @date 5/10/21 3:21 PM
 */
@Data
public class ResourceData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileName;
    private FileConstants.FILE_TYPE fileType;
    private String fileDescription;
    private String createBy;
    private String createUser;
    private Date createTime;
    private String updateBy;
    private String updateUser;
    private Date updateTime;
}
