package com.oiazheng.weblog.web.controller;

import com.oiazheng.weblog.common.aspect.ApiOperationLog;
import com.oiazheng.weblog.common.utils.Response;
import com.oiazheng.weblog.web.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Api(tags = "Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/list")
    @ApiOperation(value = "")
    @ApiOperationLog(description = "")
    public Response finCategoryList() {
        return categoryService.findCategoryList();
    }
}
