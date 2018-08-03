package com.coocaa.demo.service;

import java.util.List;

public interface ProductService {
    List<String > queryProductName();
    List<String> queryProductNameByAssignee(String assignee);
}
