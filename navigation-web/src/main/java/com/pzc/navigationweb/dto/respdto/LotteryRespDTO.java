package com.pzc.navigationweb.dto.respdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

import java.util.List;

/**
 * @author ryf
 * @date 5/11/21 2:46 PM
 */
@Data
public class LotteryRespDTO extends ReqDTO {

    /**
     * 期号
     */
    private String eventDate;

    /**
     * 中奖普通号码
     */
    private String normalNum;

    /**
     * 中奖特殊号码
     */
    private String specialNum;

    /**
     * 自定义普通号码
     */
    private String customNormalNum;

    /**
     * 自动以特殊号码
     */
    private String customSpecialNum;
}
