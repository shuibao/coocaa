package com.coocaa.demo.service;

import com.coocaa.demo.vo.Chart2;
import com.coocaa.demo.vo.RequestVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IssueService {

     List<String> queryIssueAssignee();

     /**
      * 使用：图表一
      * 根据选择的经办人返回项目名称及该项目的效率
      * @param requestVo
      * @return
      * @throws ParseException
      */
     Map<String, Object> queryIssueAssigneeEfficiency(RequestVo requestVo) throws ParseException;

     /**
      * 使用：图表三
      * 根据选择的经办人返回该经办人每个问题创建时间的累计工作总量
      * @param requestVo
      * @return
      */
     Map<String, Object> queryTimeStory(RequestVo requestVo);

     /**
      * 使用：图表四
      * 根据选择的经办人返回该经办人未解决问题的总估计时间和总剩余时间及所有的问题的总剩余时间
      * @param requestVo
      * @return
      */
     Map<String, Object> queryUnfixedTime(RequestVo requestVo);
     /**
      * 使用：图表五
      * 根据选择的经办人列表，返回每个经办的的工作效率
      */
     Map<String, Object> queryPersonEfficiency(RequestVo requestVo) ;

     /**
      * 使用：图表二
      * 根据选择的经办人及项目名称，返回已解决和未解决问题的时间
      * @param requestVo
      * @return
      */
     List<Chart2> queryIssueTime(RequestVo requestVo) ;

     /**
      * 使用：图表五
      * 根据选择的项目，返回该项目的每个sprint的总工作量和工作效率
      * @param requestVo
      * @return
      */
     Map<String,Object> querySprintStoryAndEfficiency(RequestVo requestVo) ;
}
