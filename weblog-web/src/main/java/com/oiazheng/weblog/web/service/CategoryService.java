package com.oiazheng.weblog.web.service;

import com.oiazheng.weblog.common.utils.Response;

public interface CategoryService {
    /**
     * 获取分类列表
     * @return
     */
    Response findCategoryList();
}
