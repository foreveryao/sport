package com.company.project.web.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.*;
import com.company.project.model.WxUser;
import com.company.project.model.number;
import com.company.project.model.Token;
import com.company.project.service.WxService;
import com.company.project.service.NumberService;
import com.company.project.common.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class GetMessageController {
    @Autowired
    private WxService WxService;

    @PostMapping("/getpic")
    public Result getpic(@RequestParam String openId) throws NullPointerException {
        if(WxService.findById(openId)==null) {
            return ResultGenerator.genFailResult("微信不存在");
        }
        WxUser wxUser = WxService.findById(openId);
        return ResultGenerator.genSuccessResult(wxUser.getUrl());
    }

    @PostMapping("/getWeixinId")
    public Result getWeixinId(@RequestParam String openId) throws NullPointerException {
        if(WxService.findById(openId)==null) {
            return ResultGenerator.genFailResult("微信不存在");
        }
        WxUser wxUser = WxService.findById(openId);
        return ResultGenerator.genSuccessResult(wxUser.getWeixinId());
    }
}
