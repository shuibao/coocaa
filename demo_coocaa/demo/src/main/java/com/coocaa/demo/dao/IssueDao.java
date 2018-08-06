package com.coocaa.demo.dao;

import com.coocaa.demo.entity.Issue;
import com.coocaa.demo.vo.IssueVo;
import com.coocaa.demo.vo.RequestVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IssueDao {
    /*
    使用：所有图表的经办人选择框
    描述：返回所有的项目经办人
     */
    List<String> queryIssueAssignee(@Param("projectName")String projectName);
    /**
     * 使用：所有图表的项目选择框
     * 选择经办人，返回改经办人负责的项目名称
     * @param assignee
     * @return
     */
    List<String> queryProductByAssignee(@Param("assignee")String assignee);
//    List<IssueVo> queryIssue(@Param("RequestVo")RequestVo requestVo);
    /*
    使用：图表一（工作效率-单人）
    描述：选择经办人根据返回经办人每周的工作量，和时间 group by project_key
     */
    List<IssueVo> queryWeekEfficiency(@Param("RequestVo")RequestVo requestVo);

    /**
     *使用：图表三（任务图-单人）
     *描述：选择经办人 返回该经办人的所有问题的创办时间以及累加工作量
     * @param requestVo
     * @return
     */
    List<IssueVo> queryTimeStory(@Param("RequestVo")RequestVo requestVo);

    /**
     * 使用：图表四（剩余工作报告-单人）
     * 描述：选择经办人，选择该经办人为解决问题的总预估时间
     * @param requestVo
     * @return
     */
    List<IssueVo> queryUnfixedTime(@Param("RequestVo")RequestVo requestVo);

    /**
     * 使用：图表五（效率比较图-多人）
     * 描述：选择多个经办人，展示出每个经办人所有项目的工作效率
     * @param requestVo
     * @return
     */
    List<IssueVo> queryPersonEfficiency(@Param("RequestVo")RequestVo requestVo);

    /**
     *使用：图表二（细分工作量-单人）
     *描述：选择经办人，选择项目，展示出该项目的所有问题的时间
     * @param requestVo
     * @return
     */
    List<IssueVo> queryIssueTime(@Param("RequestVo")RequestVo requestVo);
//砍掉
//    /**
//     * 使用：图表六（sprint报告）
//     * 描述：选择项目，返回该项目根据sprint分类显示story
//     * @param requestVo
//     * @return
//     */
//    List<IssueVo> querySprintStory(@Param("RequestVo")RequestVo requestVo);
//    /**
//     * 使用：图表六
//     * 描述：选择项目，返回该项目根据sprint分类显示Efficiency
//     * @param requestVo
//     * @return
//     */
//    List<IssueVo> querySprintEfficiency(@Param("RequestVo")RequestVo requestVo);

    /**
     * 使用：图表七（人员工作量-单项目）
     * 描述：根据选择的项目名称，返回该项目所有人员各自的工作量情况
     * @param requestVo
     * @param resolution 1：已解决 0：未解决
     * @return
     */
    List<IssueVo> queryPersonStory(@Param("RequestVo")RequestVo requestVo,@Param("resolution") int resolution);

    /**
     * 使用：图表九（团队效率比较-多项目）
     * 描述：返回选择的每个项目的总工作量及总耗费时间
     * @param requestVo
     * @return
     */
    List<IssueVo> queryProjectEfficiency(@Param("RequestVo")RequestVo requestVo);

    /**
     *
     */
    List<IssueVo> queryMemberEfficiency(@Param("RequestVo")RequestVo requestVo);
}
