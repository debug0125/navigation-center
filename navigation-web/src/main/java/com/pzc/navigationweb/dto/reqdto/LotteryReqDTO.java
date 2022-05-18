package com.pzc.navigationweb.dto.reqdto;

import cn.hutool.core.lang.Assert;
import com.pzc.navigationweb.constant.enumtype.LotteryType;
import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

/**
 * @author ryf
 * @date 8/13/21 1:07 PM
 */
@Data
public class LotteryReqDTO extends ReqDTO {

    private String eventDate;

    private String normalNum;

    private String specialNum;

    private int type;

}
