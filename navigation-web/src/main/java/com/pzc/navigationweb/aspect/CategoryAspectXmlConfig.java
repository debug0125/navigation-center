/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pzc.navigationweb.aspect;

import com.pzc.navigationweb.common.util.RedisUtil;
import com.pzc.navigationweb.constant.RedisKeyConstant;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.service.CategoryInService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

/**
 * Aspect XML 配置类
 * @author ryf
 * @date 8/3/21 3:44 PM
 */
public class CategoryAspectXmlConfig {

    @Autowired
    private CategoryInService categoryInService;

    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {

//        Random random = new Random();
//        if (random.nextBoolean()) {
//            throw new RuntimeException("For Purpose from XML configuration.");
//        }
//        System.out.println("@Around any public method : " + pjp.getSignature());
        return pjp.proceed();
    }

    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method.");
    }

    public void finalizeAnyPublicMethod() {
        categoryInService.getCategoryList();
        System.out.println(1);
//        RedisUtil.op().setV(RedisKeyConstant.CATEGORY_REDIS_KEY,categoryList);
    }

    public void afterAnyPublicMethod() {
        System.out.println("@AfterReturning any public method.");
    }

    public void afterThrowingAnyPublicMethod() {
        System.out.println("@AfterThrowing any public method.");
    }
}
