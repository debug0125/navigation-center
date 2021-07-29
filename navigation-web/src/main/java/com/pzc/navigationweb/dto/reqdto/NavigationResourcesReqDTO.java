package com.pzc.navigationweb.dto.reqdto;

import cn.hutool.core.lang.Assert;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.pzc.navigationweb.constant.enumtype.UserErrorCodeEnum;
import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

import java.util.List;

/**
 * @author ryf
 * @date 5/11/21 2:46 PM
 */
@Data
public class NavigationResourcesReqDTO extends ReqDTO {

    private String name;

    private String description;

    private String navigationUrl;

    private String logoUrl;

    private String detail;

    private String type;

    private List<String> categoryIds;

    @Override
    public void validation() {
        Assert.notEmpty(getCategoryIds(), UserErrorCodeEnum.ERROR_NAV_ID.getErrMsg());
    }
}
