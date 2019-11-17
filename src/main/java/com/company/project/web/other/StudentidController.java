package com.company.project.web.other;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Studentid;
import com.company.project.service.StudentidService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/11/13.
*/
@RestController
@RequestMapping("/studentid")
public class StudentidController {
    @Resource
    private StudentidService studentidService;

    @PostMapping("/add")
    public Result add(Studentid studentid) {
        studentidService.save(studentid);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        studentidService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Studentid studentid) {
        studentidService.update(studentid);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        Studentid studentid = studentidService.findById(id);
        return ResultGenerator.genSuccessResult(studentid);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Studentid> list = studentidService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
