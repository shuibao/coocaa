package com.coocaa.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void queryProductKeyTest(){
        List<String> res = productDao.queryProductName();
        System.out.println(res.size());
    }

    @Test
    public void queryAssigneeProjectTest(){
        List<String> res = productDao.queryProductByAssignee("zhaolei01");
        System.out.println(res.size());
    }
}
