package com.coocaa.demo.service;

import com.coocaa.demo.vo.Chart2Vo;
import com.coocaa.demo.vo.RequestVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IssueService {

     List<String> getIssueAssignee(String projectName);
     List<String> getProductNameByAssignee(String assignee);
     /**
      * 图表一（工作效率-单人）
      * 根据选择的经办人返回项目名称及该项目的效率
      * @param requestVo
      * @return
      * @throws ParseException
      */
     Map<String, Object> getIssueAssigneeEfficiency(RequestVo requestVo) throws ParseException;

     /**
      * 使用：图表三（任务图-单人）
      * 根据选择的经办人返回该经办人每个问题创建时间的累计工作总量
      * @param requestVo
      * @return
      */
     Map<String, Object> getTimeStory(RequestVo requestVo);

     /**
      * 使用：图表四（剩余工作报告-单人）
      * 根据选择的经办人返回该经办人未解决问题的总估计时间和总剩余时间及所有的问题的总剩余时间
      * @param requestVo
      * @return
      */
     Map<String, Object> getUnfixedTime(RequestVo requestVo);
     /**
      * 使用：图表五（效率比较图-多人）
      * 根据选择的经办人列表，返回每个经办的的工作效率
      */
     Map<String, Object> getPersonEfficiency(RequestVo requestVo) ;

     /**
      * 使用：图表二（细分工作量-单人）
      * 根据选择的经办人及项目名称，返回已解决和未解决问题的时间
      * @param requestVo
      * @return
      */
     List<Chart2Vo> getIssueTime(RequestVo requestVo) ;
//      砍掉
//     /**
//      * 使用：图表六（sprint报告-单人)
//      * 根据选择的项目，返回该项目的每个sprint的总工作量和工作效率
//      * @param requestVo
//      * @return
//      */
//     Map<String,Object> querySprintStoryAndEfficiency(RequestVo requestVo) ;

     /**
      * 使用：图表七（人员工作量-单项目）
      * 根据选择的项目，返回该项目所有人员的完成的工作量和未完成的工作量及总工作量和工作量占比
      * @param requestVo
      * @return
      */
     Map<String,Object> getPersonStory(RequestVo requestVo);

     /**
      * 使用：图表九（团队效率比较-多项目）
      * 根据选择的多个项目，返回该每个项目的人均效率
      * @param requestVo
      * @return
      */
     Map<String,Object> getProjectEfficiency(RequestVo requestVo);

     /**
      * 使用：图表十（团队产能比较-多项目)
      * 根据选择的多个项目，返回每个项目的工作量及完成时间
      * @param requestVo
      * @return
      */
     Map<String,Object> getProjectStoryPoint(RequestVo requestVo);

     /**
      * 使用：图表八（人员效率比较-单项目）
      * 选择单个项目，比较项目里的成员(多选)每个人的工作量及完成时间
      * @param requestVo
      * @return
      */
     Map<String,Object> getMemberEfficiency(RequestVo requestVo);
}
