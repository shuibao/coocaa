package com.coocaa.demo.service.impl;

import com.coocaa.demo.dao.ProductDao;
import com.coocaa.demo.entity.Product;
import com.coocaa.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<String> queryProductName() {
        return productDao.queryProductName();
    }

    @Override
    public List<String> queryProductNameByAssignee(String assignee) {
        if(assignee!=null||assignee!=""){
            return productDao.queryProductByAssignee(assignee);
        }else{
            return productDao.queryProductName();
        }
    }


}
