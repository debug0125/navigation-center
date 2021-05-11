package com.pzc.navigationweb.dto.reqdto;

import com.pzc.navigationweb.dto.basedto.ReqDTO;
import lombok.Data;

/**
 * @author ryf
 * @date 5/11/21 2:46 PM
 */
@Data
public class NavigationResourcesReqDTO extends ReqDTO {

    private String name;

    private String desc;

    private String navigationUrl;

    private String logoUrl;

    private String detail;

    private String type;
}
