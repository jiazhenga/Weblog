package com.oiazheng.weblog.web.service;

import com.oiazheng.weblog.common.utils.Response;
import com.oiazheng.weblog.web.model.vo.archive.FindArchiveArticlePageListReqVO;

public interface ArchiveService {
    /**
     * 获取文章归档分页数据
     * @param findArchiveArticlePageListReqVO
     * @return
     */
    Response findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO);
}
