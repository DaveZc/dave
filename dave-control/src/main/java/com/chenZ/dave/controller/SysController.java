package com.chenZ.dave.controller;

import com.chenZ.dave.common.ResultModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chen.z
 * @date 2018/7/11
 */
@Controller("sys")
public class SysController {

    @RequestMapping("check")
    @ResponseBody
    public ResultModel sysCheck() {
        return new ResultModel();
    }

}
