package com.coocaa.demo.web;

import com.coocaa.demo.service.IssueService;
import com.coocaa.demo.service.ProductService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/demo")
    public ModelAndView test(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        mv.getModel().put("url",basePath);
        return mv;
    }

    @RequestMapping(value = "/assignee",method = RequestMethod.GET)
    private Map<String,Object>getIssueAssignee(HttpServletResponse response,String projectName){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map<String,Object> modelMap = new HashMap<>();
        List<String> assigneeList = issueService.getIssueAssignee(projectName);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("assignee",assigneeList);
        if(assigneeList!=null&&assigneeList.size()!=0){
            modelMap.put("success",true);
            modelMap.put("msg","success");
            modelMap.put("data",resMap);
        }else{
            modelMap.put("success",false);
            modelMap.put("msg","没有查询到经办人信息");
        }
        return  modelMap;
    }
    @RequestMapping(value = "/projectname",method = RequestMethod.GET)
    private Map<String,Object>getProductNameByAssignee(HttpServletResponse response,String assignee){
        response.setHeader("Access-Control-Allow-Origin","*");
        Map<String,Object> modelMap = new HashMap<>();
        List<String> productNameList = issueService.getProductNameByAssignee(assignee);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("projectName",productNameList);
        if(productNameList!=null&&productNameList.size()!=0){
            modelMap.put("success",true);
            modelMap.put("msg","success");
            modelMap.put("data",resMap);
        }else{
            modelMap.put("success",false);
            modelMap.put("msg","没有查询到该经办人的相关项目信息");
        }
        return  modelMap;
    }
}
