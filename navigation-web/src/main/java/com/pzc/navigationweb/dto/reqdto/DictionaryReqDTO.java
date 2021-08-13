package com.pzc.navigationweb.dto.reqdto;

import cn.hutool.core.lang.Assert;
import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

/**
 * @author ryf
 * @date 8/13/21 1:07 PM
 */
@Data
public class DictionaryReqDTO extends ReqDTO {


    private String dicKey;

    private String dicValue;

    @Override
    public void validation() {
        Assert.notNull(getDicKey(),"字典key不能为空");
        Assert.notNull(getDicValue(),"字典key不能为空");
    }
}
