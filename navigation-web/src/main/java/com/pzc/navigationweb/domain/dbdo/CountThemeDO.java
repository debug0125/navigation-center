package com.pzc.navigationweb.domain.dbdo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("count_theme")
@Data
public class CountThemeDO extends BaseDO {

    private String themeName;

    private Date themeDate;


}