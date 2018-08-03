package com.coocaa.demo.service.impl;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.coocaa.demo.dao.IssueDao;
import com.coocaa.demo.dao.ProductDao;
import com.coocaa.demo.service.IssueService;
import com.coocaa.demo.util.MathUtil;
import com.coocaa.demo.vo.Chart2;
import com.coocaa.demo.vo.IssueVo;
import com.coocaa.demo.vo.RequestVo;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class IssueServiceImpl implements IssueService {
    public static String averageEfficiency ="7";
    public static int fixStatue=1;
    public static int unfixStatue=0;
    @Autowired
    private IssueDao issueDao;


    @Override
    public List<String> queryIssueAssignee() {
        return issueDao.queryIssueAssignee();
    }

    @Override
    public Map<String, Object> queryIssueAssigneeEfficiency(RequestVo requestVo) throws ParseException {
        List<IssueVo>issueVoList = new ArrayList<>();
        //处理前端传入的日期，得出前28天，每7天为一周
        Calendar c = Calendar.getInstance();
        c.setTime(requestVo.getEndTime());
        c.add(Calendar.DATE,-7);
        Date fourthWeek = c.getTime();
        c.add(Calendar.DATE,-7);
        Date thirdWeek = c.getTime();
        c.add(Calendar.DATE,-7);
        Date secondWeek = c.getTime();
        c.add(Calendar.DATE,-7);
        Date firstWeek = c.getTime();
        //数据库查询时间的开始时间为第一周的时间
        requestVo.setStartTime(firstWeek);
        //从数据库查询数据
        issueVoList=issueDao.queryProjectEfficiency(requestVo);
        //存每周的总耗费时间，有四周，所以长度为4 0对应第一周，1第二周，2第三周，3第四周数据
        int[]timeArray = new int[4];
        //存每周的总工作量，同上
        int[]storyArray = new int[4];
        //存每周的工作效率
        String[]efficiencyArray = new String[4] ;
        //将数据库查询出来的每条数据中的耗费时间以及工作量
        //先判断每条数据中的创建时间在四周的哪一周范围内，再把累加
        for(int i = 0;i < issueVoList.size(); i++){
            if (issueVoList.get(i).getnewCreated().before(secondWeek)){
                timeArray[0]+=issueVoList.get(i).getAllTimeSpent();
                storyArray[0]+=issueVoList.get(i).getStoryPoint();
            }else if(issueVoList.get(i).getnewCreated().before(thirdWeek)){
                timeArray[1]+=issueVoList.get(i).getAllTimeSpent();
                storyArray[1]+=issueVoList.get(i).getStoryPoint();
            }else if(issueVoList.get(i).getnewCreated().before(fourthWeek)){
                timeArray[2]+=issueVoList.get(i).getAllTimeSpent();
                storyArray[2]+=issueVoList.get(i).getStoryPoint();
            }else if(issueVoList.get(i).getnewCreated().before(requestVo.getEndTime())){
                timeArray[3]+=issueVoList.get(i).getAllTimeSpent();
                storyArray[3]+=issueVoList.get(i).getStoryPoint();
            }
        }
        //计算工作效率
        for(int i =0;i<4;i++){
            efficiencyArray[i]=MathUtil.division(storyArray[i],timeArray[i]);
        }
        //返回数据格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        //new出每周时间的最后一天数据 因为前端返回的数据格式为“2018.01.01-2018.01.06”
        Calendar d = Calendar.getInstance();
        d.setTime(firstWeek);
        d.add(Calendar.DATE,6);
        String firstWeekEnd = sdf.format(d.getTime());
        d.add(Calendar.DATE,7);
        String secondWeekEnd = sdf.format(d.getTime());
        d.add(Calendar.DATE,7);
        String thirdWeekEnd = sdf.format(d.getTime());
        d.add(Calendar.DATE,7);
        String fourthWeekEnd = sdf.format(d.getTime());
        List<String>dateList=new ArrayList<>();
        dateList.add(sdf.format(firstWeek)+"-"+firstWeekEnd);
        dateList.add(sdf.format(secondWeek)+"-"+secondWeekEnd);
        dateList.add(sdf.format(thirdWeek)+"-"+thirdWeekEnd);
        dateList.add(sdf.format(fourthWeek)+"-"+fourthWeekEnd);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("week",dateList);
        resMap.put("efficiency",efficiencyArray);
        resMap.put("averageEfficiency",averageEfficiency);
        return resMap;

    }

    @Override
    public Map<String, Object> queryTimeStory(RequestVo requestVo) {
        List<IssueVo>issueVoList = new ArrayList<>();
        issueVoList = issueDao.queryTimeStory(requestVo);
        //存放每个问题的创建时间
        List<String> createdList = new ArrayList<>();
        //存放每个问题的累加工作量
        List<String> storyList = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        int sumStory = 0;
        for(int i = 0;i < issueVoList.size();i++){
            createdList.add(sdf.format(issueVoList.get(i).getnewCreated()));
            sumStory += issueVoList.get(i).getStoryPoint();
            storyList.add(String.valueOf(sumStory));
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("created",createdList);
        resMap.put("storyPoint",storyList);
        return resMap;
    }

    @Override
    public Map<String, Object> queryUnfixedTime(RequestVo requestVo) {
        List<IssueVo>issueVoList = new ArrayList<>();
        issueVoList = issueDao.queryUnfixedTime(requestVo);
        ArrayList<String>issueKeyList = new ArrayList<>();
        ArrayList<String>originaTimelList = new ArrayList<>();
        ArrayList<String>remainingTimeList = new ArrayList<>();
        int sumRemainingTime = 0;
        for(int i = 0;i < issueVoList.size();i++){
            issueKeyList.add(issueVoList.get(i).getIssueKey());
            originaTimelList.add(String.valueOf(issueVoList.get(i).getAllOriginalEstimate()));
            remainingTimeList.add(String.valueOf(issueVoList.get(i).getAllRemainingEstimate()));
            sumRemainingTime+=issueVoList.get(i).getAllRemainingEstimate();
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("issueKey",issueKeyList);
        resMap.put("allOriginalEstimate",originaTimelList);
        resMap.put("allRemainingEstimate",remainingTimeList);
        resMap.put("allRemainingTime",String.valueOf(sumRemainingTime));
        return resMap;
    }

    @Override
    public Map<String, Object> queryPersonEfficiency(RequestVo requestVo) {
        List<IssueVo>issueVoList = new ArrayList<>();
        issueVoList = issueDao.queryPersonEfficiency(requestVo);
        ArrayList<String>assigneeList = new ArrayList<>();
        ArrayList<String>efficiencyList =new ArrayList<>();
        ArrayList<String>storyList = new ArrayList<>();
        //int sumStory = 0,sumTimeSpent = 0;
        Collections.sort(issueVoList, new Comparator<IssueVo>() {
            @Override
            public int compare(IssueVo o1, IssueVo o2) {
                return MathUtil.division(o2.getStoryPoint(),o2.getAllTimeSpent()).compareTo(MathUtil.division(o1.getStoryPoint(),o1.getAllTimeSpent()));
            }
        });
        for (int i = 0; i < issueVoList.size(); i++) {
            assigneeList.add(issueVoList.get(i).getAssignee());
            efficiencyList.add(MathUtil.division(issueVoList.get(i).getStoryPoint(),issueVoList.get(i).getAllTimeSpent()));
            storyList.add(String.valueOf(issueVoList.get(i).getStoryPoint()));
           // sumStory += issueVoList.get(i).getStoryPoint();
           // sumTimeSpent += issueVoList.get(i).getAllTimeSpent();
        }
        //String averageEfficiency = MathUtil.division(sumStory,sumTimeSpent);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("assignee",assigneeList);
        resMap.put("efficiency",efficiencyList);
        resMap.put("allStoryPoint",storyList);
        resMap.put("averageEfficiency",averageEfficiency);
        return resMap;
    }

    @Override
    public List<Chart2> queryIssueTime(RequestVo requestVo) {
        List<IssueVo>issueVoList = new ArrayList<>();
        issueVoList = issueDao.queryIssueTime(requestVo);
        List<Chart2> chart2List = new ArrayList<>();
        for(int i = 0;i < requestVo.getProjectName().size();i++){
            Chart2 unFixChart2 = new Chart2();
            Chart2 fixChart2 = new Chart2();
            unFixChart2.setProjectName(requestVo.getProjectName().get(i));
            unFixChart2.setResolution(unfixStatue);
            fixChart2.setProjectName(requestVo.getProjectName().get(i));
            fixChart2.setResolution(fixStatue);
            List<String> unissueKeyList = new ArrayList<>();
            List<Integer> unoriginalTimeList = new ArrayList<>();
            List<Integer> unspentTimeList = new ArrayList<>();
            List<String> issueKeyList = new ArrayList<>();
            List<Integer> originalTimeList = new ArrayList<>();
            List<Integer> spentTimeList = new ArrayList<>();
            for(int j = 0; j < issueVoList.size();j++){
                if(issueVoList.get(j).getProjectName().equals(requestVo.getProjectName().get(i))){
                    if(issueVoList.get(j).getResolution()==unfixStatue){
                        unissueKeyList.add(issueVoList.get(j).getIssueKey());
                        unoriginalTimeList.add(issueVoList.get(j).getAllOriginalEstimate());
                        unspentTimeList.add(issueVoList.get(j).getAllTimeSpent());
                    }else if(issueVoList.get(j).getResolution()==fixStatue){
                        issueKeyList.add(issueVoList.get(j).getIssueKey());
                        originalTimeList.add(issueVoList.get(j).getAllOriginalEstimate());
                        spentTimeList.add(issueVoList.get(j).getAllTimeSpent());
                    }
                }
            }
            fixChart2.setIssueKey(issueKeyList);
            fixChart2.setAllOriginalEstimate(originalTimeList);
            fixChart2.setAllTimeSpent(spentTimeList);
            unFixChart2.setIssueKey(unissueKeyList);
            unFixChart2.setAllOriginalEstimate(unoriginalTimeList);
            unFixChart2.setAllTimeSpent(unspentTimeList);
            fixChart2.setSumOriginal(MathUtil.sum(originalTimeList));
            fixChart2.setSumSpent(MathUtil.sum(spentTimeList));
            chart2List.add(fixChart2);
            chart2List.add(unFixChart2);
        }
        return chart2List;

    }

    @Override
    public Map<String, Object> querySprintStoryAndEfficiency(RequestVo requestVo) {
        List<IssueVo>resStoryList = new ArrayList<>();
        resStoryList = issueDao.querySprintStory(requestVo);
        List<IssueVo>resEfficiencyList = new ArrayList<>();
        resEfficiencyList = issueDao.querySprintEfficiency(requestVo);
        ArrayList<String>sprintList = new ArrayList<>();
        ArrayList<String>storyList = new ArrayList<>();
        ArrayList<String>efficiencyList = new ArrayList<>();
        int allStory = 0;
        for (int i = 0;i<resStoryList.size();i++){
            storyList.add(String.valueOf(resStoryList.get(i).getStoryPoint()));
            sprintList.add(resStoryList.get(i).getSprint());
            allStory+=resStoryList.get(i).getStoryPoint();
            efficiencyList.add(MathUtil.division(resEfficiencyList.get(i).getStoryPoint(),resEfficiencyList.get(i).getAllTimeSpent()));
        }
        Map<String,Object>resMap = new HashMap<>();
        resMap.put("sprint",sprintList);
        resMap.put("sprintStoryPoint",storyList);
        resMap.put("efficiency",efficiencyList);
        resMap.put("allStoryPoint",allStory);
        return resMap;
    }


}
