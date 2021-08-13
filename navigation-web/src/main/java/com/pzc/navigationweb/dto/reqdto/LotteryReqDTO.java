package com.pzc.navigationweb.dto.reqdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

/**
 * @author ryf
 * @date 8/13/21 1:07 PM
 */
@Data
public class LotteryReqDTO extends ReqDTO {

    /**
     * 彩票期号
     */
    private String eventDate;
}
