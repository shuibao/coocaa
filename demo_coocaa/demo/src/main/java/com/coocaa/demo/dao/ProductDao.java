package com.coocaa.demo.dao;

import com.coocaa.demo.vo.IssueVo;
import com.coocaa.demo.vo.RequestVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * 选择经办人，返回改经办人负责的项目名称
     * @param assignee
     * @return
     */
    List<String> queryProductByAssignee(@Param("assignee")String assignee);
    List<String> queryProductName();

}
