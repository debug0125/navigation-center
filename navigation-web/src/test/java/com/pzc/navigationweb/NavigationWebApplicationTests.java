package com.pzc.navigationweb;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
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

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        loginUser.setAccount("ryf");
        loginUser.setName("痞子橙");
        loginUser.setPassword("123456");
        loginUser.setPasswordTwo("123456");
        loginService.register(loginUser);

    }

    @Test
    void redisTest(){
        RedisUtil.op().setV("name","ryf");
        System.out.println((String) RedisUtil.op().getV("name"));

    }

    @Test
    void PaChong(){

    }

    public static void main(String[] args){
        String path = "/Users/orange_r/Desktop/DLT.txt";
        String url = "http://kaijiang.500.com/shtml/dlt/21092.shtml?0_ala_baidu";
        try {
            String content = HttpUtil.get(url);
            File file = new File(path);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            osw.write(content);
            osw.close();


            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            String regexDate = "<strong>\\d*</strong>";
            String regexNum = "<td>\\d{1,2}(\\s\\d+)*.\\d{1,2}\\s\\d+</td>";
            List<String> regexList = new ArrayList<>();
            regexList.add(regexDate);
            regexList.add(regexNum);
            String buf ;
            while ((buf = br.readLine()) != null) {

                String finalBuf = buf;
                regexList.stream().forEach(x -> {
                    Pattern proInfo = Pattern.compile(x, Pattern.DOTALL);
                    Matcher buf_m = proInfo.matcher(finalBuf);
                    if (buf_m.find()) {
                        String bugGroup = buf_m.group();
                        System.out.println(bugGroup.substring(bugGroup.indexOf('>')+1,
                                bugGroup.lastIndexOf('<')));
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
