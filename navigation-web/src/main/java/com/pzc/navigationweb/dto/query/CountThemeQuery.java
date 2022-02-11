package com.pzc.navigationweb.dto.query;

import com.pzc.navigationweb.dto.basedto.PageDTO;
import lombok.Data;

import java.util.Date;

@Data
public class CountThemeQuery extends PageDTO {

    private Date createDate;

    private String themeName;

    private Date themeDate;


}