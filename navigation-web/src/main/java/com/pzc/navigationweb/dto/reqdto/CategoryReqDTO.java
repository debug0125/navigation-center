package com.pzc.navigationweb.dto.reqdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

/**
 * @author ryf
 * @date 5/11/21 2:46 PM
 */
@Data
public class CategoryReqDTO extends ReqDTO {

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 上级ID
     */
    private String fatherId;
}
