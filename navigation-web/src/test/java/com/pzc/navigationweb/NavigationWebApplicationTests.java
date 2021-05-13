package com.pzc.navigationweb;

import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.NavigationResourcesService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = NavigationWebApplication.class)
@ImportResource("classpath:spring-application.xml")
@RunWith(SpringJUnit4ClassRunner.class)
class NavigationWebApplicationTests {

    @Autowired
    private NavigationResourcesService navigationResourcesService;

    @Test
    void contextLoads() {
    }

    @Test
    void listTest(){
        Result<PageInfo<NavigationResourcesRespDTO>> pageInfoResult = navigationResourcesService.pageNavigation(new NavigationQuery());
        if (pageInfoResult.getModule() != null) {
            System.out.println(111);
        }

    }

}
