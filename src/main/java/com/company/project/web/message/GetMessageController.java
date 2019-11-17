package com.company.project.web.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.*;
import com.company.project.model.WxUser;
import com.company.project.model.number;
import com.company.project.model.Token;
import com.company.project.model.wxInfo;
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
import java.util.*;

@RestController
@RequestMapping("/message")
public class GetMessageController {
    @Autowired
    private WxService WxService;

    /**
     * 获取微信头像、微信名
     *
     * @param openId
     * @return
     * @throws NullPointerException
     */
    @PostMapping("/getPicWithName")
    public Result getPic(@RequestParam String openId) throws NullPointerException {
        if (WxService.findById(openId) == null) {
            return ResultGenerator.genFailResult("微信不存在");
        }
        WxUser wxUser = WxService.findById(openId);
        wxInfo wxInfo = new wxInfo();
        wxInfo.setUrl(wxUser.getUrl());
        wxInfo.setWxName(wxUser.getWeixinId());
        return ResultGenerator.genSuccessResult(wxInfo);
    }

    /**
     * 获取微信头像、微信名列表
     *
     * @param openId
     * @return
     * @throws NullPointerException
     */
    @PostMapping("/getpicAndwxName")
    public Result getpic(@RequestParam String openId) throws NullPointerException {
        List<wxInfo> list = new ArrayList<>();
        String[] strArr = openId.split(",");//注意分隔符是需要转译
        for (int i = 0; i < strArr.length; i++) {

            if (WxService.findById(strArr[i].substring(1, strArr[i].length() - 1)) == null) {
                return ResultGenerator.genFailResult("微信不存在");
            }
            WxUser wxUser = WxService.findById(strArr[i].substring(1, strArr[i].length() - 1));
            wxInfo wxInfo = new wxInfo();
            wxInfo.setUrl(wxUser.getUrl());
            wxInfo.setWxName(wxUser.getWeixinId());
            list.add(wxInfo);
        }
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 检查token的有效性
     *
     * @param openId
     * @return
     * @throws NullPointerException
     */
    @PostMapping("/token")
    public Result getToken(@RequestParam String openId) throws NullPointerException {
        if (WxService.findById(openId) == null) {
            return ResultGenerator.genFailResult("微信不存在");
        }
        WxUser wxUser = WxService.findById(openId);
        return ResultGenerator.genSuccessResult(wxUser.getToken());
    }
}
