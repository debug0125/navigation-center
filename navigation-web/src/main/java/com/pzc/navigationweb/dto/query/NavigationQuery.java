package com.pzc.navigationweb.dto.query;

import com.pzc.navigationweb.dto.basedto.PageDTO;
import lombok.Data;

/**
 * @author ryf
 * @date 5/12/21 8:43 PM
 */
@Data
public class NavigationQuery extends PageDTO {

    private String name;

    private String type;
}
