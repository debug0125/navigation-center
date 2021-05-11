package com.pzc.navigationweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryf
 * @date 5/8/21 3:41 PM
 */
@RestController
@RequestMapping(NavigationController.PAGE_NAME)
public class NavigationController {

    public static final String PAGE_NAME = "/navigation";

    @RequestMapping("/submit")
    public String submit(String aa) {
        return "index";
    }
}
