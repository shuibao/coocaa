package com.coocaa.demo.service.impl;

import com.coocaa.demo.dao.IssueDao;
import com.coocaa.demo.service.IssueService;
import com.coocaa.demo.util.MathUtil;
import com.coocaa.demo.util.NameUtil;
import com.coocaa.demo.util.TimeUtil;
import com.coocaa.demo.vo.Chart2Vo;
import com.coocaa.demo.vo.IssueVo;
import com.coocaa.demo.vo.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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
    public List<String> getIssueAssignee(String projectName) {
        List<String> EName = issueDao.queryIssueAssignee(projectName);
        List<String> CName =new ArrayList<>();

        for (int i = 0; i < EName.size(); i++) {
            if(NameUtil.nameMap.containsKey(EName.get(i))){
                CName.add(NameUtil.nameMap.get(EName.get(i)));
            }else{
                CName.add(EName.get(i));
            }
        }
        return CName;
    }
    @Override
    public List<String> getProductNameByAssignee(String assignee) {
        if(NameUtil.nameMap.containsValue(assignee)){
            for (String getKey:NameUtil.nameMap.keySet()){
                if(NameUtil.nameMap.get(getKey).equals(assignee)){
                    return  issueDao.queryProductByAssignee(getKey);
                }
            }
        }
        return issueDao.queryProductByAssignee(assignee);
    }
    @Override
    public Map<String, Object> getIssueAssigneeEfficiency(RequestVo requestVo) throws ParseException {
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
        issueVoList=issueDao.queryWeekEfficiency(new NameUtil().CtoEName(requestVo));
        //存每周的总耗费时间，有四周，所以长度为4 0对应第一周，1第二周，2第三周，3第四周数据
        int[]timeArray = new int[4];
        //存每周的总工作量，同上
        int[]storyArray = new int[4];
        //存每周的工作效率
        String[]efficiencyArray = new String[4] ;
        //将数据库查询出来的每条数据中的耗费时间以及工作量
        //先判断每条数据中的创建时间在四周的哪一周范围内，再把累加
        for(int i = 0;i < issueVoList.size(); i++){
            if (issueVoList.get(i).getNewCreated().before(secondWeek)){
                timeArray[0]+=issueVoList.get(i).getTimeSpent();
                storyArray[0]+=issueVoList.get(i).getStoryPoint();
            }else if(issueVoList.get(i).getNewCreated().before(thirdWeek)){
                timeArray[1]+=issueVoList.get(i).getTimeSpent();
                storyArray[1]+=issueVoList.get(i).getStoryPoint();
            }else if(issueVoList.get(i).getNewCreated().before(fourthWeek)){
                timeArray[2]+=issueVoList.get(i).getTimeSpent();
                storyArray[2]+=issueVoList.get(i).getStoryPoint();
            }else if(issueVoList.get(i).getNewCreated().before(requestVo.getEndTime())){
                timeArray[3]+=issueVoList.get(i).getTimeSpent();
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
    public Map<String, Object> getTimeStory(RequestVo requestVo) {
        List<IssueVo>issueVoList = new ArrayList<>();
        issueVoList = issueDao.queryTimeStory(new NameUtil().CtoEName(requestVo));
        //存放每个问题的创建时间
        List<String> createdList = new ArrayList<>();
        //存放每个问题的累加工作量
        List<String> storyList = new ArrayList<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        int sumStory = 0;
        for(int i = 0;i < issueVoList.size();i++){
            //将时间转换为yyyy-MM-dd格式的字符串
            createdList.add(sdf.format(issueVoList.get(i).getNewCreated()));
            //工作总量
            sumStory += issueVoList.get(i).getStoryPoint();
            storyList.add(String.valueOf(sumStory));
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("created",createdList);
        resMap.put("storyPoint",storyList);
        return resMap;
    }

    @Override
    public Map<String, Object> getUnfixedTime(RequestVo requestVo) {
        List<IssueVo>issueVoList = new ArrayList<>();
        issueVoList = issueDao.queryUnfixedTime(new NameUtil().CtoEName(requestVo));
        //存问题的key
        ArrayList<String>issueKeyList = new ArrayList<>();
        //存预估时间
        //ArrayList<String>originalTimelList = new ArrayList<>();
        //存预估时间 eg:1week2hour
        ArrayList<String>originalDayTimeList = new ArrayList<>();
        //存剩余时间
        //ArrayList<String>remainingTimeList = new ArrayList<>();
        //存剩余时间 eg:1week2hour
        ArrayList<String>remainingDayTimeList = new ArrayList<>();
        int sumRemainingTime = 0;
        for(int i = 0;i < issueVoList.size();i++){
            issueKeyList.add(issueVoList.get(i).getIssueKey());
            //originalTimelList.add(String.valueOf(issueVoList.get(i).getOriginalEstimate()));
            //remainingTimeList.add(String.valueOf(issueVoList.get(i).getRemainingEstimate()));
            originalDayTimeList.add(TimeUtil.WeekDayHour(issueVoList.get(i).getOriginalEstimate()));
            remainingDayTimeList.add(TimeUtil.WeekDayHour(issueVoList.get(i).getRemainingEstimate()));
            sumRemainingTime+=issueVoList.get(i).getRemainingEstimate();
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("issueKey",issueKeyList);
        resMap.put("allOriginalEstimate",originalDayTimeList);
        resMap.put("allRemainingEstimate",remainingDayTimeList);
        resMap.put("allRemainingTime",TimeUtil.WeekDayHour(sumRemainingTime));
        return resMap;
    }

    @Override
    public Map<String, Object> getPersonEfficiency(RequestVo requestVo) {
        List<IssueVo>issueVoList = new ArrayList<>();
        issueVoList = issueDao.queryPersonEfficiency(new NameUtil().CtoEName(requestVo));
        ArrayList<String>assigneeList = new ArrayList<>();
        ArrayList<String>efficiencyList =new ArrayList<>();
        ArrayList<String>storyList = new ArrayList<>();
        //int sumStory = 0,sumTimeSpent = 0;
        //按效率从高到低排序
        Collections.sort(issueVoList, new Comparator<IssueVo>() {
            @Override
            public int compare(IssueVo o1, IssueVo o2) {
                return MathUtil.division(o2.getStoryPoint(),o2.getTimeSpent()).compareTo(MathUtil.division(o1.getStoryPoint(),o1.getTimeSpent()));
            }
        });
        for (int i = 0; i < issueVoList.size(); i++) {
            assigneeList.add(issueVoList.get(i).getAssignee());
            //计算效率
            efficiencyList.add(MathUtil.division(issueVoList.get(i).getStoryPoint(),issueVoList.get(i).getTimeSpent()));
            storyList.add(String.valueOf(issueVoList.get(i).getStoryPoint()));
           // sumStory += issueVoList.get(i).getStoryPoint();
           // sumTimeSpent += issueVoList.get(i).getTimeSpent();
        }
        //String averageEfficiency = MathUtil.division(sumStory,sumTimeSpent);
        Map<String,Object> resMap = new HashMap<>();
        List<String>CNameList = new ArrayList<>();
        CNameList = new NameUtil().EtoCName(assigneeList);
        resMap.put("assignee",CNameList);
        resMap.put("efficiency",efficiencyList);
        resMap.put("allStoryPoint",storyList);
        resMap.put("averageEfficiency",averageEfficiency);
        return resMap;
    }

    //该方法原本是多项目的选择，后面需求改为单项目，所以该方法实现逻辑是按多项目经行的
    //单项目的数据处理不需要写最外层的for循环，也不需要判断对projectName做等值判断
    @Override
    public List<Chart2Vo> getIssueTime(RequestVo requestVo) {
        List<IssueVo>issueVoList = new ArrayList<>();
        issueVoList = issueDao.queryIssueTime(new NameUtil().CtoEName(requestVo));
        List<Chart2Vo> chart2List = new ArrayList<>();
        //按项目来取数据，每一个项目封装一个类
        for(int i = 0;i < requestVo.getProjectName().size();i++){
            //未完成的项目（封装类）
            Chart2Vo unFixChart2 = new Chart2Vo();
            //完成的项目
            Chart2Vo fixChart2 = new Chart2Vo();
            unFixChart2.setProjectName(requestVo.getProjectName().get(i));
            unFixChart2.setStatus(unfixStatue);
            fixChart2.setProjectName(requestVo.getProjectName().get(i));
            fixChart2.setStatus(fixStatue);
            List<String> unissueKeyList = new ArrayList<>();
            List<Integer> unoriginalTimeList = new ArrayList<>();
            List<Integer> unspentTimeList = new ArrayList<>();
            List<String> issueKeyList = new ArrayList<>();
            List<Integer> originalTimeList = new ArrayList<>();
            List<Integer> spentTimeList = new ArrayList<>();
            for(int j = 0; j < issueVoList.size();j++){
                //对projectName做等值判断 将同一个项目的数据放在一块儿
                if(issueVoList.get(j).getProjectName().equals(requestVo.getProjectName().get(i))){
                    if(issueVoList.get(j).getStatus()==unfixStatue){
                        unissueKeyList.add(issueVoList.get(j).getIssueKey());
                        unoriginalTimeList.add(issueVoList.get(j).getOriginalEstimate());
                        unspentTimeList.add(issueVoList.get(j).getTimeSpent());
                    }else if(issueVoList.get(j).getStatus()==fixStatue){
                        issueKeyList.add(issueVoList.get(j).getIssueKey());
                        originalTimeList.add(issueVoList.get(j).getOriginalEstimate());
                        spentTimeList.add(issueVoList.get(j).getTimeSpent());
                    }
                }
            }
            fixChart2.setIssueKey(issueKeyList);
            fixChart2.setOriginalEstimate(originalTimeList);
            fixChart2.setTimeSpent(spentTimeList);
            unFixChart2.setIssueKey(unissueKeyList);
            unFixChart2.setOriginalEstimate(unoriginalTimeList);
            unFixChart2.setTimeSpent(unspentTimeList);
            fixChart2.setSumOriginal(MathUtil.sum(originalTimeList));
            fixChart2.setSumSpent(MathUtil.sum(spentTimeList));
            chart2List.add(fixChart2);
            chart2List.add(unFixChart2);
        }
        return chart2List;

    }

    @Override
    public Map<String, Object> getPersonStory(RequestVo requestVo) {
        List<IssueVo>fixIssueVoList = new ArrayList<>();
        List<IssueVo>unFixIssueVoList = new ArrayList<>();
        List<String> assigneeList = new ArrayList<>();
        assigneeList = issueDao.queryIssueAssignee(requestVo.getProjectName().get(0));//根据项目名获取该项目的所有人员
        fixIssueVoList = issueDao.queryPersonStory(requestVo,fixStatue); //完成工作量集合（没有已完成的人员不包含在内）
        unFixIssueVoList = issueDao.queryPersonStory(requestVo,unfixStatue);//未完成工作量集合（没有未完成工作的人员不包含在内）
        ArrayList<Integer>allStoryPointList=new ArrayList<>(); //每个人的总工作量
        ArrayList<Integer>unFixStoryPointList=new ArrayList<>();//每个人的未完成工作量
        ArrayList<Integer>fixStoryPointList=new ArrayList<>();//每个人的完成工作量
        ArrayList<String>proportionList=new ArrayList<>();//工作量占比
        for(int i = 0; i<assigneeList.size();i++){
            boolean temp1=false;
            boolean temp2=false;
            //按返回的经办人list顺序获取每个人的完成工作量
            for (int j = 0; j <fixIssueVoList.size() ; j++) {
                if(assigneeList.get(i).equals(fixIssueVoList.get(j).getAssignee())){
                    fixStoryPointList.add(fixIssueVoList.get(j).getStoryPoint());
                    temp1 =true;
                    break;
                }
            }
            //如果该人员没有完成的工作则为0
            if(!temp1)fixStoryPointList.add(0);
            //按返回的经办人list顺序获取每个人的未完成工作量
            for (int x = 0; x <unFixIssueVoList.size() ; x++) {
                if(assigneeList.get(i).equals(unFixIssueVoList.get(x).getAssignee())){
                    unFixStoryPointList.add(unFixIssueVoList.get(x).getStoryPoint());
                    temp2=true;
                    break;
                }
            }
            //如果该人员没有未完成的工作则为0
            if(!temp2)unFixStoryPointList.add(0);
            //获取该人员的总工作量
            allStoryPointList.add(fixStoryPointList.get(i)+unFixStoryPointList.get(i));
        }
        //求每个人的工作量占比
        DecimalFormat df=new DecimalFormat("0.00");
        int allStoryPoint = MathUtil.sum(allStoryPointList); //所有人的总工作量
        for (int i = 0; i <allStoryPointList.size() ; i++) {
            String res = df.format((float)allStoryPointList.get(i)/allStoryPoint*100);
            res+='%';
            proportionList.add(res);
        }
        Map<String,Object>resMap = new HashMap<>();
        List<String>CNameList = new ArrayList<>();
        CNameList = new NameUtil().EtoCName(assigneeList);
        resMap.put("assignee",CNameList);
        resMap.put("allStoryPoint",allStoryPointList);
        resMap.put("fixStoryPoint",fixStoryPointList);
        resMap.put("unFixStoryPoint",unFixStoryPointList);
        resMap.put("proportion",proportionList);
        return resMap;
    }

    @Override
    public Map<String, Object> getProjectEfficiency(RequestVo requestVo) {
        List<IssueVo> issueVoList = issueDao.queryProjectEfficiency(requestVo);
        List<String> projectList =new ArrayList<>();
        List<String> efficiencyList =new ArrayList<>();
        for (int i = 0; i < issueVoList.size(); i++) {
            String projectName = issueVoList.get(i).getProjectName();
            projectList.add(projectName);
           // int personNum = issueDao.queryIssueAssignee(projectName).size();
            int storyPoint = issueVoList.get(i).getStoryPoint();
            int spentTime =issueVoList.get(i).getTimeSpent();
            String efficiency = MathUtil.division(storyPoint,spentTime);
            efficiencyList.add(efficiency);
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("projectName",projectList);
        resMap.put("efficiency",efficiencyList);
        return resMap;
    }

    @Override
    public Map<String, Object> getProjectStoryPoint(RequestVo requestVo) {
        List<IssueVo> issueVoList = issueDao.queryProjectEfficiency(requestVo);
        List<String> projectList =new ArrayList<>();
        List<Integer> storyPointList = new ArrayList<>();
        List<Integer> spentTimeList =new ArrayList<>();
        for (int i = 0; i < issueVoList.size(); i++) {
            String projectName = issueVoList.get(i).getProjectName();
            projectList.add(projectName);
            int storyPoint = issueVoList.get(i).getStoryPoint();
            int spentTime =issueVoList.get(i).getTimeSpent();
            storyPointList.add(storyPoint);
            spentTimeList.add(spentTime);
        }
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("projectName",projectList);
        resMap.put("storyPoint",storyPointList);
        resMap.put("spentTime",spentTimeList);
        return resMap;
    }
    @Override
    public Map<String, Object> getMemberEfficiency(RequestVo requestVo) {
        List<IssueVo>issueVoList = new ArrayList<>();
        NameUtil nameUtil = new NameUtil();
        requestVo = nameUtil.CtoEName(requestVo);
        issueVoList = issueDao.queryMemberEfficiency(requestVo);
        ArrayList<String>assigneeList = new ArrayList<>();
        ArrayList<String>efficiencyList =new ArrayList<>();
        //int sumStory = 0,sumTimeSpent = 0;
        Collections.sort(issueVoList, new Comparator<IssueVo>() {
            @Override
            public int compare(IssueVo o1, IssueVo o2) {
                return MathUtil.division(o2.getStoryPoint(),o2.getTimeSpent()).compareTo(MathUtil.division(o1.getStoryPoint(),o1.getTimeSpent()));
            }
        });
        for (int i = 0; i < issueVoList.size(); i++) {
            assigneeList.add(issueVoList.get(i).getAssignee());
            efficiencyList.add(MathUtil.division(issueVoList.get(i).getStoryPoint(),issueVoList.get(i).getTimeSpent()));
            // sumStory += issueVoList.get(i).getStoryPoint();
            // sumTimeSpent += issueVoList.get(i).getTimeSpent();
        }
        //String averageEfficiency = MathUtil.division(sumStory,sumTimeSpent);
        Map<String,Object> resMap = new HashMap<>();
        List<String>CNameList = new ArrayList<>();
        CNameList = new NameUtil().EtoCName(assigneeList);
        resMap.put("assignee",CNameList);
        resMap.put("efficiency",efficiencyList);
        resMap.put("averageEfficiency",averageEfficiency);
        return resMap;
    }
//  砍掉
//    @Override
//    public Map<String, Object> querySprintStoryAndEfficiency(RequestVo requestVo) {
//        List<IssueVo>resStoryList = new ArrayList<>();
//        resStoryList = issueDao.querySprintStory(requestVo);
//        List<IssueVo>resEfficiencyList = new ArrayList<>();
//        resEfficiencyList = issueDao.querySprintEfficiency(requestVo);
//        ArrayList<String>sprintList = new ArrayList<>();
//        ArrayList<String>storyList = new ArrayList<>();
//        ArrayList<String>efficiencyList = new ArrayList<>();
//        int allStory = 0;
//        for (int i = 0;i<resStoryList.size();i++){
//            storyList.add(String.valueOf(resStoryList.get(i).getStoryPoint()));
//            sprintList.add(resStoryList.get(i).getSprint());
//            allStory+=resStoryList.get(i).getStoryPoint();
//            efficiencyList.add(MathUtil.division(resEfficiencyList.get(i).getStoryPoint(),resEfficiencyList.get(i).getTimeSpent()));
//        }
//        Map<String,Object>resMap = new HashMap<>();
//        resMap.put("sprint",sprintList);
//        resMap.put("sprintStoryPoint",storyList);
//        resMap.put("efficiency",efficiencyList);
//        resMap.put("allStoryPoint",allStory);
//        return resMap;
//    }


}
