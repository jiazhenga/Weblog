package com.oiazheng.weblog.web.controller;

import com.oiazheng.weblog.web.model.User;
import com.oiazheng.weblog.common.aspect.ApiOperationLog;
import com.oiazheng.weblog.common.utils.JsonUtil;
import com.oiazheng.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@RestController
@Slf4j
@Api(tags = "首页模块")
public class TestController {

    @PostMapping("/admin/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口")
    public Response test(@RequestBody @Validated User user) {
        // 打印入参
        log.info(JsonUtil.toJsonString(user));

        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());

        return Response.success(user);
//        return Response.success();
    }

//    public Response test(@RequestBody @Validated User user, BindingResult bindingResult) {
//        // 是否存在参数校验错误
////        if (bindingResult.hasErrors()) {
////            // 获取校验不通过字段的提示信息
////            String errorMsg = bindingResult.getFieldErrors()
////                    .stream()
////                    .map(FieldError::getDefaultMessage)
////                    .collect(Collectors.joining(", "));
////
////            return Response.fail(errorMsg);
////        }
////
////        // 返参
////        return Response.success();
////        throw new RuntimeException("测试异常");
//
//        // 主动定义一个运行时异常，分母不能为零
//        int i = 1 / 0;
//        return Response.success();
//    }

    @PostMapping("/admin/update")
    @ApiOperationLog(description = "测试更新接口")
    @ApiOperation(value = "测试更新接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response testUpdate() {
        log.info("更新成功...");
        return Response.success();
    }
}
