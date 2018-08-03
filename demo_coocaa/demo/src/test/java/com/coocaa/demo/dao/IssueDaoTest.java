package com.coocaa.demo.dao;

import com.coocaa.demo.vo.IssueVo;
import com.coocaa.demo.vo.RequestVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueDaoTest {
    @Autowired
    private IssueDao issueDao;


    @Test
    public void queryIssueAssigneeTest(){
        List<String> res = new ArrayList<>();
        res = issueDao.queryIssueAssignee();
        System.out.println(res.size());
    }

    @Test
    public void queryIssueTest() throws ParseException {
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        requestVo.setAssignee(assignee);
        Date startTime=sdf.parse("2018-03-09");
        Date endTime=sdf.parse("2018-04-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        List<IssueVo> res = new ArrayList<>();
        res = issueDao.queryProjectEfficiency(requestVo);
        System.out.println(res.size());
    }

    @Test
    public void queryTimeStoryTest()throws ParseException{
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        requestVo.setAssignee(assignee);
        Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-06-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        List<IssueVo> res = new ArrayList<>();
        res = issueDao.queryTimeStory(requestVo);
        String date = sdf.format(res.get(14).getnewCreated());
        System.out.println(date);

    }
    @Test
    public void queryUnfixedTimeTest()throws ParseException{
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        requestVo.setAssignee(assignee);
        Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        List<IssueVo> res = new ArrayList<>();
        res = issueDao.queryUnfixedTime(requestVo);
        System.out.println(res.size());

    }
    @Test
    public void queryPersonEfficiencyTest()throws ParseException{
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
        List<IssueVo> res = new ArrayList<>();
        res = issueDao.queryPersonEfficiency(requestVo);
        System.out.println(res.size());

    }
    @Test
    public void queryIssueTimeTest()throws ParseException{
        RequestVo requestVo = new RequestVo();
        List<String> assignee = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        assignee.add("zhaolei01");
        requestVo.setAssignee(assignee);
        Date startTime=sdf.parse("2018-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        List<String>projectNameList = new ArrayList<>();
        projectNameList.add("ARCH-酷开架构设计");
        projectNameList.add("TEST-测试项目");
        requestVo.setProjectName(projectNameList);
        List<IssueVo> res = new ArrayList<>();
        res = issueDao.queryIssueTime(requestVo);
        System.out.println(res.size());

    }

    @Test
    public void querySprintStoryTest()throws ParseException{
        RequestVo requestVo = new RequestVo();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime=sdf.parse("2015-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        List<String>projectNameList = new ArrayList<>();
        projectNameList.add("ARCH-酷开架构设计");
        requestVo.setProjectName(projectNameList);
        List<IssueVo> res = new ArrayList<>();
        res = issueDao.querySprintStory(requestVo);
        System.out.println(res.size());

    }
    @Test
    public void querySpritEfficiencyTest()throws ParseException{
        RequestVo requestVo = new RequestVo();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime=sdf.parse("2015-01-06");
        Date endTime=sdf.parse("2018-08-06");
        requestVo.setStartTime(startTime);
        requestVo.setEndTime(endTime);
        List<String>projectNameList = new ArrayList<>();
        projectNameList.add("ARCH-酷开架构设计");
        requestVo.setProjectName(projectNameList);
        List<IssueVo> res = new ArrayList<>();
        res = issueDao.querySprintEfficiency(requestVo);
        System.out.println(res.size());

    }

}
