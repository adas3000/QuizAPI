package com.quiz.api.service;

import com.google.common.collect.Lists;
import com.quiz.api.enums.Category;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {



    public List<Category> findAll(){
        return Lists.newArrayList(Category.values());
    }





}
