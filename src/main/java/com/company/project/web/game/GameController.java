package com.company.project.web.game;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.game.Player;
import com.company.project.service.GameService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {
    @Resource
    private GameService gameService;
    private Player player = new Player();

    /**
     * 发起约场
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("addGameOrder")
    @ResponseBody
    public Result addGameOrder(HttpServletRequest request) throws ParseException {
        List<Player> list = gameService.getMyGameOrder();
        String openId = request.getParameter("openId");
        String weiXinId = request.getParameter("weixinId");
        String weiXin2Id = request.getParameter("weixin2Id");
        int state = Integer.parseInt(request.getParameter("orderState"));
        int gameState=Integer.parseInt(request.getParameter("gameState"));
        String myTeam = request.getParameter("myTeamName");
        String team2 = request.getParameter("teamName");
        String timeTmp = request.getParameter("time");
        String formId = request.getParameter("formId");
        //截取时间的年月日，并改成****-**-**的格式
        String time1 = timeTmp.substring(0, timeTmp.indexOf('日')).replace("年", "-")
                .replace("月", "-") + " ";
        //截取约场信息的结束时间
        String time2 = timeTmp.substring(timeTmp.length() - 5);
        player.setOpenId(openId);
        player.setWeiXinId(weiXinId);
        player.setWeiXin2Id(weiXin2Id);
        player.setOrderState(state);
        player.setGameState(gameState);
        player.setMyTeamName(myTeam);
        player.setTeamName(team2);
        player.setTime(timeTmp);
        player.setFormId(formId);
        player.setTimeToState(Timestamp.valueOf(time1 + time2 + ":00"));
        Timestamp t = new Timestamp(System.currentTimeMillis());
        //返回false说明时间小于当前时间，返回true说明时间大于当前时间
        if (t.compareTo(player.getTimeToState()) < 0) {
            gameService.addGameOrder(player);
            list.add(player);
            return ResultGenerator.genSuccessResult(list);
        } else {
            return ResultGenerator.genFailResult("所选时间不能小于当前时间");
        }
    }

    @RequestMapping("showAll")
    @ResponseBody
    public List<Player> showAllGameOrder(HttpServletRequest request, Model model) {
        return gameService.getAll();
    }


    /**
     * 获取我的发起
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("showMyGameOrder")
    @ResponseBody
    public Result showMyGameOrder(HttpServletRequest request, Model model) {
        List<Player> list = gameService.getAll();
        List<Player> res = new ArrayList<Player>();
        String openId = request.getParameter("openId");
        for (int i = 0; i < list.size(); i++) {
            Player playerTmp = list.get(i);
            String tmp = playerTmp.getOpenId();
            if (tmp.equals(openId)) {
                res.add(playerTmp);
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
        List<Player> list = gameService.getAll();
        List<Player> res = new ArrayList<Player>();
        String openId = request.getParameter("openId");
        for (int i = 0; i < list.size(); i++) {
            Player playerTmp = list.get(i);
            String tmp = playerTmp.getOpenId();
            if (!tmp.equals(openId)) {
                res.add(playerTmp);
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
        Player player = gameService.selectByID(id);
        String weiXin2Id = request.getParameter("weixin2Id");
        String team2 = request.getParameter("teamName");
        int orderState = Integer.parseInt(request.getParameter("orderState"));
        int gameState = Integer.parseInt(request.getParameter("gameState"));
        String token = request.getParameter("token");
        player.setWeiXin2Id(weiXin2Id);
        player.setTeamName(team2);
        player.setOrderState(orderState);
        player.setGameState(gameState);
        player.setToken(token);
        int result = gameService.updateGameOrder(player);
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
        Player player = gameService.selectByID(id);
        int orderState = Integer.parseInt(request.getParameter("orderState"));
        String formId = request.getParameter("formId");
        player.setOrderState(orderState);
        player.setFormId(formId);
        int result = gameService.updateGameOrderOfSate(player);
        if (result == 1 && orderState == 4) {
            return ResultGenerator.genSuccessResult("同意");
        } else if (result == 1 && orderState == 0) {
            return ResultGenerator.genSuccessResult("拒绝");
        } else {
            return ResultGenerator.genFailResult("失败");
        }
    }


    /**
     * 邀请撤销接口
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("delInvite")
    @ResponseBody
    public Result delOrder(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        int result = gameService.deleteGameOrder(id);
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
        int state = gameService.getGameOrderStateById(id);
        return ResultGenerator.genSuccessResult(state);
    }
}
