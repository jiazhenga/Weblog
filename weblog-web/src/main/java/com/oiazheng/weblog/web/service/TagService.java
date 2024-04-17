package com.oiazheng.weblog.web.service;

import com.oiazheng.weblog.common.utils.Response;

public interface TagService {
    /**
     * 获取标签列表
     * @return
     */
    Response findTagList();
}
