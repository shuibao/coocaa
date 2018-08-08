package com.coocaa.demo.web;

import com.coocaa.demo.service.IssueService;
import com.coocaa.demo.vo.Chart2Vo;
import com.coocaa.demo.vo.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private IssueService issueService;
    //人员工作量
    @RequestMapping(value = "/assigneestory",method = RequestMethod.POST)
    private Map<String,Object>getAssigneestory(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getProjectName()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.getPersonStory(requestVo);
                if(res.size()!=0&&res!=null) {
                    modelMap.put("success", true);
                    modelMap.put("msg", "");
                    modelMap.put("data", res);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("msg","没有查询到相关信息");
                }
            }catch (Exception e){
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入完整的查询条件");
        }
        return modelMap;
    }
    //团队人均效率
    @RequestMapping(value = "/efficiency",method = RequestMethod.POST)
    private Map<String,Object>getProjectEfficiency(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getProjectName()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.getProjectEfficiency(requestVo);
                if(res.size()!=0&&res!=null) {
                    modelMap.put("success", true);
                    modelMap.put("msg", "");
                    modelMap.put("data", res);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("msg","没有查询到相关信息");
                }
            }catch (Exception e){
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入完整的查询条件");
        }
        return modelMap;
    }
    //团队产能
    @RequestMapping(value = "/storypoint",method = RequestMethod.POST)
    private Map<String,Object>getProjectStoryPoint(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getProjectName()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.getProjectStoryPoint(requestVo);
                if(res.size()!=0&&res!=null) {
                    modelMap.put("success", true);
                    modelMap.put("msg", "");
                    modelMap.put("data", res);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("msg","没有查询到相关信息");
                }
            }catch (Exception e){
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入完整的查询条件");
        }
        return modelMap;
    }

    @RequestMapping(value = "/memberefficiency",method = RequestMethod.POST)
    private Map<String,Object>getMemberEfficiency(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getProjectName()!=null&&requestVo.getAssignee()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.getMemberEfficiency(requestVo);
                if(res.size()!=0&&res!=null) {
                    modelMap.put("success", true);
                    modelMap.put("msg", "");
                    modelMap.put("data", res);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("msg","没有查询到相关信息");
                }
            }catch (Exception e){
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入完整的查询条件");
        }
        return modelMap;
    }

}
