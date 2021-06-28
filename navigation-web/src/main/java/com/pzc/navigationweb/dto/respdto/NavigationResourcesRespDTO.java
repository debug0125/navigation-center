package com.pzc.navigationweb.dto.respdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

import java.util.List;

/**
 * @author ryf
 * @date 5/11/21 2:46 PM
 */
@Data
public class NavigationResourcesRespDTO extends ReqDTO {

    private String createDateStr;

    private String name;

    private String description;

    private String navigationUrl;

    private String logoUrl;

    private String detail;

    private String type;

    private Integer openCount;

    private Integer likeCount;

    /**
     * 是否已收藏
     */
    private Boolean isLiked;

    /**
     * 标记集合
     */
    List<CategoryRespDTO> categoryRespDTOList;
}
