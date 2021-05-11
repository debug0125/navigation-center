package com.pzc.navigationweb.domain.dbdo;

import java.util.Date;

/**
 * @author ryf
 * @date 5/11/21 2:47 PM
 */
public interface BaseDOInt {

    public String getId();

    public void setId(String id);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public String getCreateName();

    public void setCreateName(String createName);

    public Date getModifyDate();

    public void setModifyDate(Date modifyDate);

    public String getModifyName();

    public void setModifyName(String modifyName);

    public Integer getVersion();

    public void setVersion(Integer version);

    public Boolean getIsDel();

    public void setIsDel(Boolean del);
}
