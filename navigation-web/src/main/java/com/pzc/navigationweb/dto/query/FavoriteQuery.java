package com.pzc.navigationweb.dto.query;

import com.pzc.navigationweb.dto.basedto.PageDTO;
import lombok.Data;

import java.util.List;

/**
 * @author ryf
 * @date 7/16/21 3:13 PM
 */
@Data
public class FavoriteQuery extends PageDTO {

    private String userId;

    private String navId;

    private List<String> categoryIds;
}
