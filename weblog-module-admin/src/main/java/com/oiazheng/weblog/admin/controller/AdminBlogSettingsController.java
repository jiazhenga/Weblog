package com.oiazheng.weblog.admin.controller;

import com.oiazheng.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.oiazheng.weblog.admin.service.AdminBlogSettingsService;
import com.oiazheng.weblog.common.aspect.ApiOperationLog;
import com.oiazheng.weblog.common.domain.mapper.BlogSettingsMapper;
import com.oiazheng.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/blog/settings")
@Api(tags = "Admin Blog Settings")
public class AdminBlogSettingsController {
    @Autowired
    private AdminBlogSettingsService blogSettingsService;

    @PostMapping("/update")
    @ApiOperation(value = "博客基础信息修改")
    @ApiOperationLog(description = "博客基础信息修改")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response updateBlogSettings(@RequestBody @Validated UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        return blogSettingsService.updateBlogSettings(updateBlogSettingsReqVO);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "博客基础信息详情")
    @ApiOperationLog(description = "获取博客基础信息详情")
    public Response findDetail() {
        return blogSettingsService.findDetail();
    }
}
