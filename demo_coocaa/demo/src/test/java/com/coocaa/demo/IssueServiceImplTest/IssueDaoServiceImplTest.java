package com.coocaa.demo.IssueServiceImplTest;

import com.coocaa.demo.service.IssueService;
import com.coocaa.demo.vo.Chart2;
import com.coocaa.demo.vo.RequestVo;
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
public class IssueDaoServiceImplTest {
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
        res=issueService.queryIssueAssigneeEfficiency(requestVo);
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
        res=issueService.queryTimeStory(requestVo);
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
        res=issueService.queryUnfixedTime(requestVo);
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
        res=issueService.queryPersonEfficiency(requestVo);
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
        List<Chart2>res = new ArrayList<>();
        res = issueService.queryIssueTime(requestVo);
        System.out.println(res.size());
    }
    @Test
    public void querySprintStoryAndEfficiencyTest()throws ParseException{
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<String> projectName = new ArrayList<>();
        projectName.add("ARCH-酷开架构设计");
        requestVo.setProjectName(projectName);
        Date startTime=sdf.parse("2015-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        Map<String ,Object>resMap=new HashMap<>();
        resMap = issueService.querySprintStoryAndEfficiency(requestVo);
        System.out.println(resMap.size());
    }
}
