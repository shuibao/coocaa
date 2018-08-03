package com.coocaa.demo.web;

import com.coocaa.demo.service.IssueService;
import com.coocaa.demo.vo.Chart2;
import com.coocaa.demo.vo.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @RequestMapping(value = "/efficiency",method = RequestMethod.POST)
    private Map<String,Object>getIssueAssigneeEfficiency(HttpServletResponse response, RequestVo requestVo) throws RuntimeException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getEndTime()!=null){
            try{
                res=issueService.queryIssueAssigneeEfficiency(requestVo);
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
    @RequestMapping(value = "/storypoint",method = RequestMethod.POST)
    private Map<String,Object>getTimeStory(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.queryTimeStory(requestVo);
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
    @RequestMapping(value = "/remaintime",method = RequestMethod.POST)
    private Map<String,Object>getUnfixedTime(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.queryUnfixedTime(requestVo);
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
    @RequestMapping(value = "/compare",method = RequestMethod.POST)
    private Map<String,Object>getPersonEfficiency(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        Map<String, Object> res = new HashMap<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.queryPersonEfficiency(requestVo);
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
    @RequestMapping(value = "/issuetime",method = RequestMethod.POST)
    private Map<String,Object>getqueryIssueTime(HttpServletResponse response, RequestVo requestVo) throws RuntimeException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> modelMap = new HashMap<>();
        List<Chart2> res = new ArrayList<>();
        if(requestVo!=null&&requestVo.getAssignee()!=null&&requestVo.getStartTime()!=null&&requestVo.getEndTime()!=null){
            try {
                res = issueService.queryIssueTime(requestVo);
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
