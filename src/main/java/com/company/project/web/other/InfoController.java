package com.company.project.web.other;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Info;
import com.company.project.service.InfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.swing.plaf.nimbus.State;
import java.sql.Time;
import java.util.List;
import com.company.project.dao.InfoMapper;

@RestController
@RequestMapping("/Info")
public class InfoController {
    @Resource
    private InfoMapper infoMapper;
    @Resource
    private InfoService infoService;

    @PostMapping("/detail2")
    public Result detail2(@RequestParam String MyWechat,Integer State) {
        Info info = infoMapper.selectOrde(MyWechat, State);
        return ResultGenerator.genSuccessResult(info);
    }

}
