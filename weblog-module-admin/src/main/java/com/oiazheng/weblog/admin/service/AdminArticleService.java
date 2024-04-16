package com.oiazheng.weblog.admin.service;

import com.oiazheng.weblog.admin.model.vo.article.*;
import com.oiazheng.weblog.common.utils.Response;

public interface AdminArticleService {
    /**
     * 发布文章
     * @param publishArticleReqVO
     * @return
     */
    Response publishArticle(PublishArticleReqVO publishArticleReqVO);
    /**
     * 删除文章
     * @param deleteArticleReqVO
     * @return
     */
    Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO);
    /**
     * 查询文章分页数据
     * @param findArticlePageListReqVO
     * @return
     */
    Response findArticlePageList(FindArticlePageListReqVO findArticlePageListReqVO);
    /**
     * 查询文章详情
     * @param findArticleDetailReqVO
     * @return
     */
    Response findArticleDetail(FindArticleDetailReqVO findArticleDetailReqVO);
    /**
     * 更新文章
     * @param updateArticleReqVO
     * @return
     */
    Response updateArticle(UpdateArticleReqVO updateArticleReqVO);

    /**
     * 查询标签 Select 列表数据
     * @return
     */
    Response findTagSelectList();
}
