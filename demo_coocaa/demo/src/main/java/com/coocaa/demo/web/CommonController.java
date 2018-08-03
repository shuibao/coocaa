package com.coocaa.demo.web;

import com.coocaa.demo.service.IssueService;
import com.coocaa.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/assginee",method = RequestMethod.GET)
    private Map<String,Object>getIssueAssignee(HttpServletResponse respons){
        respons.setHeader("Access-Control-Allow-Origin","*");
        respons.setHeader("Access-Control-Allow-Credentials","true");
        respons.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");

        Map<String,Object> modelMap = new HashMap<>();
        List<String> assigneeList = issueService.queryIssueAssignee();
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
    private Map<String,Object>getProductNameByAssignee(HttpServletResponse respons,String assignee){
        respons.setHeader("Access-Control-Allow-Origin","*");
        Map<String,Object> modelMap = new HashMap<>();
        List<String> productNameList = productService.queryProductNameByAssignee(assignee);
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
