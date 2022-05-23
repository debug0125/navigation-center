package com.pzc.navigationweb;

import com.pzc.navigationweb.constant.RedisKeyConstant;
import com.pzc.navigationweb.esJob.GetLotteryNumberTaskJob;
import com.pzc.navigationweb.application.process.PageFavoriteProcess;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.dto.query.CountThemeQuery;
import com.pzc.navigationweb.dto.reqdto.LoginUser;
import com.pzc.navigationweb.dto.respdto.CountThemeRespDTO;
import com.pzc.navigationweb.service.LoginService;
import com.pzc.navigationweb.service.NavigationResourcesService;
import com.pzc.navigationweb.service.PeopleCountInService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
//        PageInfo<NavigationResourcesRespDTO> pageInfoResult = navigationResourcesService.pageNavigation(new NavigationQuery());
//        if (pageInfoResult != null) {
//            System.out.println(111);
//        }

    }

    @Test
    void register(){
        LoginUser loginUser = new LoginUser();
        loginUser.setAccount("tian");
        loginUser.setName("小田");
        loginUser.setPassword("tian123");
        loginUser.setPasswordTwo("tian123");
        loginService.register(loginUser);

    }

    @Test
    void redisTest(){
//        RedisUtil.op().setV("name","ryf");
//        System.out.println((String) RedisUtil.op().getV("name"));
//        Long testLong = RedisUtil.op().incrV("test");
//        System.out.println(">>>>>>>>>>>>>>>>>>>>" + testLong);
//        String spuCode = RedisUtil.op().getSpuCode("JM");
//        System.out.println(">>>>>>>>>>>>>>>>>>>>" + spuCode);

        RedisUtil.op().setV(RedisKeyConstant.DLT_DATE_KEY,"22055");

    }

    @Test
    void applicationContextTest(){

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-process.xml");
        PageFavoriteProcess pageFavoriteProcess = context.getBean("pageFavoriteProcess", PageFavoriteProcess.class);
        Logger logger = pageFavoriteProcess.getLogger();
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>123");
        System.out.println("123");

    }

    @Autowired
    private PeopleCountInService peopleCountInService;
    @Test
    void countThemeTest() {
        CountThemeQuery query = new CountThemeQuery();
        query.setPageNo(1);
        query.setPageSize(5);
        Page<CountThemeRespDTO> countThemeDOIPage = peopleCountInService.pageCountTheme(query);
        System.out.println("总数======》》》》" + countThemeDOIPage.getTotal());
    }

    @Autowired
    private GetLotteryNumberTaskJob getLotteryNumberTaskJob;

    @Test
    void getNumber() {
        getLotteryNumberTaskJob.execute();
    }


}
