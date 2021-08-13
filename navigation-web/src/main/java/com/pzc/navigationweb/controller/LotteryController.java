package com.pzc.navigationweb.controller;

import com.pzc.navigationweb.application.process.AddCategoryProcess;
import com.pzc.navigationweb.application.process.PageLotteryProcess;
import com.pzc.navigationweb.application.process.QueryCategoryListProcess;
import com.pzc.navigationweb.application.process.RemoveCategoryProcess;
import com.pzc.navigationweb.common.util.Page;
import com.pzc.navigationweb.common.util.Result;
import com.pzc.navigationweb.dto.query.LotteryQuery;
import com.pzc.navigationweb.dto.reqdto.CategoryReqDTO;
import com.pzc.navigationweb.dto.respdto.CategoryRespDTO;
import com.pzc.navigationweb.dto.respdto.LotteryRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ryf
 * @date 5/8/21 3:41 PM
 */
@RestController
@RequestMapping(LotteryController.PAGE_NAME)
public class LotteryController extends BaseController {
    public static final String PAGE_NAME = "/lottery";

    @Autowired
    private QueryCategoryListProcess queryCategoryListProcess;

    @Autowired
    private PageLotteryProcess pageLotteryProcess;

    @RequestMapping("/addCustomLotteryNumber")
    public Result<List<CategoryRespDTO>> getCategoryList(CategoryReqDTO categoryReqDTO) {
        return queryCategoryListProcess.start(categoryReqDTO);
    }

    @RequestMapping("/pageLottery")
    public Result<Page<LotteryRespDTO>> pageLottery(LotteryQuery lotteryQuery) {
        return pageLotteryProcess.start(lotteryQuery);
    }
}
