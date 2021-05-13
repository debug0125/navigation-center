
package com.pzc.navigationweb.dto.basedto;

import java.io.Serializable;
import java.util.Objects;

public class PageDTO implements Serializable {

    private Integer pageSize;
    private Integer pageNo;

    public PageDTO() {
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getLimit() {
        return this.pageSize;
    }

    public Integer getOffset() {
        return this.pageNo != null && this.pageSize != null ? (this.pageNo - 1) * this.pageSize : null;
    }

    public void validation() {
        if (Objects.isNull(this.pageSize)) {
            throw new IllegalArgumentException("分页大小不能为空");
        } else if (Objects.isNull(this.pageNo)) {
            throw new IllegalArgumentException("分页页码不能为空");
        } else if (this.pageSize <= 0) {
            throw new IllegalArgumentException("分页大小必须大于0");
        } else if (this.pageNo <= 0) {
            throw new IllegalArgumentException("分页页码必须大于0");
        }
    }
}
