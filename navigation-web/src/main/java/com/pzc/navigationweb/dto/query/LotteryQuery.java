package com.pzc.navigationweb.dto.query;

import com.pzc.navigationweb.dto.basedto.PageDTO;
import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

/**
 * @author ryf
 * @date 8/13/21 1:07 PM
 */
@Data
public class LotteryQuery extends PageDTO {

    /**
     * 彩票期号
     */
    private String eventDate;

    /**
     * 类型
     */
    private Integer type;
}
