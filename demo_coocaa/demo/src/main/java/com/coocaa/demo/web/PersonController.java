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
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IssueService issueService;
    //图表一 工作效率
    @RequestMapping(value = "/efficiency",method = RequestMethod.POST)
    private Map<String,Object>getIssueAssigneeEfficiency(HttpServletResponse response, RequestVo requestVo) throws RuntimeException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getEndTime()!=null){
            try{
                res=issueService.getIssueAssigneeEfficiency(requestVo);
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
                return modelMap;
            }
        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入完整的查询条件");
        }

        return modelMap;
    }
    //图表三（任务图-单人）
    @RequestMapping(value = "/storypoint",method = RequestMethod.POST)
    private Map<String,Object>getTimeStory(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.getTimeStory(requestVo);
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
//    使用：图表四（剩余工作报告-单人）
    @RequestMapping(value = "/remaintime",method = RequestMethod.POST)
    private Map<String,Object>getUnfixedTime(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.getUnfixedTime(requestVo);
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
    //图表五（效率比较图-多人）
    @RequestMapping(value = "/compare",method = RequestMethod.POST)
    private Map<String,Object>getPersonEfficiency(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.getPersonEfficiency(requestVo);
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
    //图表二（细分工作量-单人）
    @RequestMapping(value = "/issuetime",method = RequestMethod.POST)
    private Map<String,Object>getqueryIssueTime(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        List<Chart2Vo> res = new ArrayList<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.getIssueTime(requestVo);
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
