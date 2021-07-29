package com.pzc.navigationweb.dto.reqdto;

import cn.hutool.core.lang.Assert;
import com.pzc.navigationweb.constant.enumtype.UserErrorCodeEnum;
import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;


/**
 * @author ryf
 * @date 5/11/21 2:46 PM
 */
@Data
public class NavToFavoriteReqDTO extends ReqDTO {

    private Boolean isLiked;

    private String navId;

    private String userId;

    @Override
    public void validation() {
        Assert.notBlank(getNavId(),UserErrorCodeEnum.ERROR_NAV_ID.getErrMsg());
        Assert.notBlank(getUserId(), UserErrorCodeEnum.NOT_FIND_USER.getErrMsg());
        Assert.notNull(getIsLiked(),UserErrorCodeEnum.ILLGAL_ARGUMENT.getErrMsg());
    }
}
