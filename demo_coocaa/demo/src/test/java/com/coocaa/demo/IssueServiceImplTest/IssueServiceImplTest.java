package com.coocaa.demo.IssueServiceImplTest;

import com.coocaa.demo.service.IssueService;
import com.coocaa.demo.vo.Chart2Vo;
import com.coocaa.demo.vo.IssueVo;
import com.coocaa.demo.vo.RequestVo;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueServiceImplTest {
    @Autowired
    private IssueService issueService;

    @Test
    public void queryIssueAssigneeEfficiencyTest() throws ParseException {
        Map<String, Object> res = new HashMap<>();
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        requestVo.setAssignee(assignee);
        //Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-04-06");
        //requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        res=issueService.getIssueAssigneeEfficiency(requestVo);
        System.out.println(res.size());
    }

    @Test
    public void queryTimeStoryTest()throws ParseException{
        Map<String, Object> res = new HashMap<>();
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        requestVo.setAssignee(assignee);
        Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-06-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        res=issueService.getTimeStory(requestVo);
        System.out.println(res.size());
    }

    @Test
    public void queryUnfixedTimeTest()throws ParseException{
        Map<String, Object> res = new HashMap<>();
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        requestVo.setAssignee(assignee);
        Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        res=issueService.getUnfixedTime(requestVo);
        System.out.println(res.size());
    }

    @Test
    public void queryPersonEfficiencyTest()throws ParseException{
        Map<String, Object> res = new HashMap<>();
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        assignee.add("luyuxiang");
        assignee.add("liupeng");
        assignee.add("zouzijian");
        requestVo.setAssignee(assignee);
        Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        res=issueService.getPersonEfficiency(requestVo);
        System.out.println(res.size());
    }

    @Test
    public void queryIssueTimeTest()throws ParseException{
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        requestVo.setAssignee(assignee);
        List<String> projectName = new ArrayList<>();
        projectName.add("ARCH-酷开架构设计");
        projectName.add("TEST-测试项目");
        requestVo.setProjectName(projectName);
        Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        List<Chart2Vo>res = new ArrayList<>();
        res = issueService.getIssueTime(requestVo);
        System.out.println(res.size());
    }
    //砍掉
//    @Test
//    public void querySprintStoryAndEfficiencyTest()throws ParseException{
//        RequestVo requestVo = new RequestVo();
//        List<String> assignee = new ArrayList<>();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        List<String> projectName = new ArrayList<>();
//        projectName.add("ARCH-酷开架构设计");
//        requestVo.setProjectName(projectName);
//        Date startTime=sdf.parse("2015-01-06");
//        Date endTime=sdf.parse("2018-08-06");
//        requestVo.setStartTime(startTime);
//        requestVo.setEndTime(endTime);
//        Map<String ,Object>resMap=new HashMap<>();
//        resMap = issueService.querySprintStoryAndEfficiency(requestVo);
//        System.out.println(resMap.size());
//    }
    @Test
    public void queryPersonStoryTest()throws ParseException{
        RequestVo requestVo = new RequestVo();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<String> projectName = new ArrayList<>();
        projectName.add("ARCH-酷开架构设计");
        requestVo.setProjectName(projectName);
        Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        Map<String,Object>res = issueService.getPersonStory(requestVo);
        System.out.println(res.size());
    }

    @Test
    public void getProjectEfficiencyTest() throws ParseException {
        RequestVo requestVo = new RequestVo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf.parse("2018-01-06");
        Date endTime = sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        List<String> projectNameList = new ArrayList<>();
        projectNameList.add("ARCH-酷开架构设计");
        projectNameList.add("TEST-测试项目");
        requestVo.setProjectName(projectNameList);
        Map<String,Object>res = new HashMap<>();
        res = issueService.getProjectEfficiency(requestVo);
        System.out.println(res.size());

    }
    @Test
    public void getMemberEfficiencyTest()throws ParseException{
        Map<String, Object> res = new HashMap<>();
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        assignee.add("luyuxiang");
        assignee.add("liupeng");
        assignee.add("zouzijian");
        requestVo.setAssignee(assignee);
        List<String> projectNameList = new ArrayList<>();
        projectNameList.add("ARCH-酷开架构设计");
        requestVo.setProjectName(projectNameList);
        Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        res=issueService.getMemberEfficiency(requestVo);
        System.out.println(res.size());
    }
}
