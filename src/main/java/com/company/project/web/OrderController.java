package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Order;
import com.company.project.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    private Order order = new Order();

    /**
     * 发起约场
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("addOrder")
    @ResponseBody
    public Result addOrder(HttpServletRequest request) throws ParseException {
        List<Order> list = orderService.getMyOrder();
        String openId = request.getParameter("openId");
        String weiXinId = request.getParameter("weixinId");
        String weiXin2Id = request.getParameter("weixin2Id");
        int state = Integer.parseInt(request.getParameter("orderState"));
        String myTeam = request.getParameter("myTeamName");
        String team2 = request.getParameter("teamName");
        String timeTmp = request.getParameter("time");
        String formId = request.getParameter("formId");
        String time1 = timeTmp.substring(0, timeTmp.indexOf('日')).replace("年", "-")
                .replace("月", "-") + " ";
        String time2 = timeTmp.substring(timeTmp.length() - 5);
        order.setOpenId(openId);
        order.setWeiXinId(weiXinId);
        order.setWeiXin2Id(weiXin2Id);
        order.setOrderState(state);
        order.setMyTeamName(myTeam);
        order.setTeamName(team2);
        order.setTime(timeTmp);
        order.setFormId(formId);
        order.setTime_to_state(Timestamp.valueOf(time1 + time2 + ":00"));
        Timestamp t = new Timestamp(System.currentTimeMillis());
        //返回false说明时间小于当前时间，返回true说明时间大于当前时间
        if (t.compareTo(order.getTime_to_state()) < 0) {
            orderService.addOrder(order);
            list.add(order);
            return ResultGenerator.genSuccessResult(list);
        } else {
            return ResultGenerator.genFailResult("所选时间不能小于当前时间");
        }
    }

    @RequestMapping("showAll")
    @ResponseBody
    public List<Order> showAllOrder(HttpServletRequest request, Model model) {
        return orderService.getAll();
    }


    /**
     * 获取我的发起
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("showMyOrder")
    @ResponseBody
    public Result showMyOrder(HttpServletRequest request, Model model) {
        List<Order> list = orderService.getAll();
        List<Order> res = new ArrayList<Order>();
        String openId = request.getParameter("openId");
        for (int i = 0; i < list.size(); i++) {
            Order orderTmp = list.get(i);
            String tmp = orderTmp.getOpenId();
            if (tmp.equals(openId)) {
                res.add(orderTmp);
            }
        }
        Collections.reverse(res);
        return ResultGenerator.genSuccessResult(res);
    }

    /**
     * 获取对方发起
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("showOthers")
    @ResponseBody
    public Result showOthers(HttpServletRequest request, Model model) {
        List<Order> list = orderService.getAll();
        List<Order> res = new ArrayList<Order>();
        String openId = request.getParameter("openId");
        for (int i = 0; i < list.size(); i++) {
            Order orderTmp = list.get(i);
            String tmp = orderTmp.getOpenId();
            if (!tmp.equals(openId)) {
                res.add(orderTmp);
            }
        }
        Collections.reverse(res);
        return ResultGenerator.genSuccessResult(res);
    }

    /**
     * 邀请接口
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("invite")
    @ResponseBody
    public Result inviteOrder(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.selectByID(id);
        String weiXin2Id = request.getParameter("weixin2Id");
        String team2 = request.getParameter("teamName");
        int orderState = Integer.parseInt(request.getParameter("orderState"));
        String token = request.getParameter("token");
        order.setWeiXin2Id(weiXin2Id);
        order.setTeamName(team2);
        order.setOrderState(orderState);
        order.setToken(token);
        int result = orderService.updateOrder(order);
        System.out.println(result);
        if (result == 1) {
            return ResultGenerator.genSuccessResult("成功邀请，待确认");
        } else {
            return ResultGenerator.genFailResult("邀请失败，对方已被邀请");
        }

    }

    /**
     * 确认邀请接口
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("sureInvite")
    @ResponseBody
    public Result sureOrder(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.selectByID(id);
        int orderState = Integer.parseInt(request.getParameter("orderState"));
        String formId = request.getParameter("formId");
        order.setOrderState(orderState);
        order.setFormId(formId);
        int result = orderService.updateOrderOfSate(order);
        if (result == 1 && orderState == 4) {
            return ResultGenerator.genSuccessResult("同意");
        } else if (result == 1 && orderState == 0) {
            return ResultGenerator.genSuccessResult("拒绝");
        } else {
            return ResultGenerator.genFailResult("失败");
        }
    }


    /**
     * 撤销接口
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("delInvite")
    @ResponseBody
    public Result delOrder(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        int result = orderService.deleteOrder(id);
        if (result == 1) {
            return ResultGenerator.genSuccessResult("撤销成功");
        } else {
            return ResultGenerator.genFailResult("撤销失败");
        }
    }
    /**
     * 根据状态值判断是否已确认邀请
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("isSure")
    @ResponseBody
    public Result isSure(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        int state = orderService.getOrderStateById(id);
        return ResultGenerator.genSuccessResult(state);
    }
}
