package com.pzc.navigationweb;

import com.github.pagehelper.PageInfo;
import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.domain.dbdo.NavigationResourcesDO;
import com.pzc.navigationweb.dto.query.NavigationQuery;
import com.pzc.navigationweb.dto.reqdto.LoginUser;
import com.pzc.navigationweb.dto.reqdto.NavigationResourcesReqDTO;
import com.pzc.navigationweb.dto.respdto.NavigationResourcesRespDTO;
import com.pzc.navigationweb.service.LoginService;
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
    @Autowired
    private LoginService loginService;

    @Test
    void contextLoads() {
    }

    @Test
    void listTest(){
        PageInfo<NavigationResourcesRespDTO> pageInfoResult = navigationResourcesService.pageNavigation(new NavigationQuery());
        if (pageInfoResult != null) {
            System.out.println(111);
        }

    }

    @Test
    void register(){
        LoginUser loginUser = new LoginUser();
        loginUser.setAccount("ryf");
        loginUser.setName("痞子橙");
        loginUser.setPassword("123456");
        loginUser.setPasswordTwo("123456");
        loginService.register(loginUser);

    }

    @Test
    void redisTest(){
        RedisUtil.op().setV("name","ryf");
        System.out.println(RedisUtil.op().get("name"));

    }

}
