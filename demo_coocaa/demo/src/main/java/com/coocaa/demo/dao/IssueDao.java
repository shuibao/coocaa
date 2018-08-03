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
    List<String> queryIssueAssignee();
//    List<IssueVo> queryIssue(@Param("RequestVo")RequestVo requestVo);
    /*
    使用：图表一
    描述：选择经办人根据项目分类返回经办人，工作量，和时间 group by project_key
     */
    List<IssueVo> queryProjectEfficiency(@Param("RequestVo")RequestVo requestVo);

    /**
     *使用：图表三
     *描述：选择经办人 返回该经办人的所有问题的创办时间以及累加工作量
     * @param requestVo
     * @return
     */
    List<IssueVo> queryTimeStory(@Param("RequestVo")RequestVo requestVo);

    /**
     * 使用：图表四
     * 描述：选择经办人，选择该经办人为解决问题的总预估时间
     * @param requestVo
     * @return
     */
    List<IssueVo> queryUnfixedTime(@Param("RequestVo")RequestVo requestVo);

    /**
     * 使用：图表五
     * 描述：选择多个经办人，展示出每个经办人所有项目的工作效率
     * @param requestVo
     * @return
     */
    List<IssueVo> queryPersonEfficiency(@Param("RequestVo")RequestVo requestVo);

    /**
     *使用：图表二
     *描述：选择经办人，选择项目，展示出该项目的所有问题的时间
     * @param requestVo
     * @return
     */
    List<IssueVo> queryIssueTime(@Param("RequestVo")RequestVo requestVo);

    /**
     * 使用：图表六
     * 描述：选择项目，返回该项目根据sprint分类显示story
     * @param requestVo
     * @return
     */
    List<IssueVo> querySprintStory(@Param("RequestVo")RequestVo requestVo);
    /**
     * 使用：图表六
     * 描述：选择项目，返回该项目根据sprint分类显示Efficiency
     * @param requestVo
     * @return
     */
    List<IssueVo> querySprintEfficiency(@Param("RequestVo")RequestVo requestVo);
}
