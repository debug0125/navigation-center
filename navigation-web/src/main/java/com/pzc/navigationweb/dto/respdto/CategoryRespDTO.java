package com.pzc.navigationweb.dto.respdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

import java.util.List;

/**
 * @author ryf
 * @date 5/11/21 2:46 PM
 */
@Data
public class CategoryRespDTO extends ReqDTO {

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 上级ID
     */
    private String fatherId;

    /**
     * 子类
     */
    private List<CategoryRespDTO> categoryList;
}
