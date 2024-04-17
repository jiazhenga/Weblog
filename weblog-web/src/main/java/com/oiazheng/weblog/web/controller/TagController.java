package com.oiazheng.weblog.web.controller;

import com.oiazheng.weblog.common.aspect.ApiOperationLog;
import com.oiazheng.weblog.common.utils.Response;
import com.oiazheng.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;
import com.oiazheng.weblog.web.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@Api(tags = "TagController")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/list")
    @ApiOperation(value = "前台获取标签列表")
    @ApiOperationLog(description = "前台获取标签列表")
    public Response findTagList() {
        return tagService.findTagList();
    }

    @PostMapping("/article/list")
    @ApiOperation("前台获取标签文章列表")
    @ApiOperationLog(description = "前台获取标签文章列表")
    public Response findTagPageList(@RequestBody @Validated FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        return tagService.findTagPageList(findTagArticlePageListReqVO);
    }
}
