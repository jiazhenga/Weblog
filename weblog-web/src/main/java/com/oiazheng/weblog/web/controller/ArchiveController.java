package com.oiazheng.weblog.web.controller;

import com.oiazheng.weblog.common.aspect.ApiOperationLog;
import com.oiazheng.weblog.common.utils.Response;
import com.oiazheng.weblog.web.model.vo.archive.FindArchiveArticlePageListReqVO;
import com.oiazheng.weblog.web.service.ArchiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Archive")
public class ArchiveController {
    @Autowired
    private ArchiveService archiveService;

    @PostMapping("/archive/list")
    @ApiOperation(value = "获取文章归档分页数据")
    @ApiOperationLog(description = "获取文章归档分页数据")
    public Response findArchivePageList(@RequestBody FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO) {
        return archiveService.findArchivePageList(findArchiveArticlePageListReqVO);
    }
}
