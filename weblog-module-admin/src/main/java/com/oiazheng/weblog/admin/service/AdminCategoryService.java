package com.oiazheng.weblog.admin.service;

import com.oiazheng.weblog.admin.model.FindCategoryPageListReqVO;
import com.oiazheng.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.oiazheng.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.oiazheng.weblog.common.utils.PageResponse;
import com.oiazheng.weblog.common.utils.Response;

public interface AdminCategoryService {
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);

    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);

    /**
     * 删除分类
     * @param deleteCategoryReqVO
     * @return
     */
    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);

    /**
     * 获取文章分类的 Select 列表数据
     * @return
     */
    Response finCategorySelectList();
}
