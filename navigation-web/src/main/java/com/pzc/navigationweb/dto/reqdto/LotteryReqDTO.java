package com.pzc.navigationweb.dto.reqdto;

import cn.hutool.core.lang.Assert;
import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

/**
 * @author ryf
 * @date 8/13/21 1:07 PM
 */
@Data
public class LotteryReqDTO extends ReqDTO {


    private String normalNum;

    private String specialNum;

    @Override
    public void validation(){
        Assert.notBlank(getNormalNum(),"普通号码不能为空");
        Assert.notBlank(getSpecialNum(),"特殊号码不能为空");
    }
}
