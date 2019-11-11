package com.company.project.web;

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
@RequestMapping("/mini")
public class WxController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private WxService WxService;
    private NumberService numberService;

    /**
     * code登录（版本1.0）
     * @param code
     * @param request
     * @return
     */
    /*@RequestMapping("Login")
    public ReturnData openid(@RequestParam("code") String code,
     HttpServletRequest request){
     ReturnData returnData = WxService.codeToOpenId(code, request);
     Map data = new HashMap();
     data.put("info", request.getSession().getId());
     returnData.setData(data);
     return returnData;
     }*/

    /**
     * code登录（版本2.0）
     * @param code
     * @return
     */
    @PostMapping("/login")
    public Result wxLogin(@RequestParam String code,String number1) throws NullPointerException {

        //System.out.println(code+"--------------------");
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        //替换appid，appsecret，和code
        String requestUrl = WX_URL.replace("APPID", WxApp.APPID)
                .replace("SECRET", WxApp.SECRET)
                .replace("JSCODE", code);
        String data = UrlUtil.Get(requestUrl);
        JSONObject jsonObject;
        jsonObject = (JSONObject) JSON.parse(data);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        WxUser wx = new WxUser();
        number num=new number();
        wx.setOpenId((String) jsonObject.get("openid"));
        wx.setToken((String) jsonObject.get("session_key"));
        wx.setLoginTime(df.format(new Date()));
        num.setId(number1);
        //token令牌
        Token t = new Token();
        t.setToken(wx.getToken());
        t.setOpenId(wx.getOpenId());
        //用户未注册则插入数据到数据库
        if (WxService.findById(wx.getOpenId()) == null && wx.getOpenId() != null) {
            if(numberService.findById(num.getId())==null) {
                return ResultGenerator.genFailResult("学号不存在");
            }
            num.setOpenId(wx.getOpenId());
            numberService.save(num);
            WxService.save(wx);
            return ResultGenerator.genSuccessResult(t);
        } else if (WxService.findById(wx.getOpenId()) != null) {
            if(numberService.findById(num.getId())==null) {
                return ResultGenerator.genFailResult("学号不存在");
            }
            WxUser wxUser = WxService.findById(wx.getOpenId());
            if(wxUser.getNumber()!=null) {
                return ResultGenerator.genFailResult("用户已经绑定学号");
            }
            num.setOpenId(wxUser.getOpenId());
            //WxUser wxUser =new WxUser();
            t.setToken(wxUser.getToken());
            //判断令牌失效时间期限为15天
            System.out.println(TimeDiff.timeDiff(wx.getLoginTime(), wxUser.getLoginTime()) + "-----------");
            if (TimeDiff.timeDiff(wx.getLoginTime(), wxUser.getLoginTime()) > 15 * 24) {
                WxService.update(wx);
                t.setToken(wx.getToken());
                return ResultGenerator.genSuccessResult(t);
            }
            return ResultGenerator.genSuccessResult(t);
        }
        return ResultGenerator.genFailResult("登录错误");
    }

    /**
     * 保存用户信息
     * @param encryptedData
     * @param iv
     * @param request
     * @return
     */
    /*@RequestMapping("SaveUser")
    public ReturnData savaUserInfo(@RequestParam("encryptedData") String encryptedData,
                                   @RequestParam("iv") String iv,
                                   HttpServletRequest request){
        WxSession userSession = (WxSession) request.getSession().getAttribute("userSession");
        WxUser user = WxService.selectUserByOpenId(userSession.getOpenId());
        JSONObject userInfo = WXAppletUserInfo.getUserInfo(encryptedData, user.getSessionKey(), iv);
        logger.info(userInfo.toJSONString());
        WxService.saveUserInfo(userInfo);
        return new ReturnData(userInfo);
    }*/
}
