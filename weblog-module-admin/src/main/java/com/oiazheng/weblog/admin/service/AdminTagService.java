package com.oiazheng.weblog.admin.service;

import com.oiazheng.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.oiazheng.weblog.admin.model.vo.tag.AddTagReqVO;
import com.oiazheng.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.oiazheng.weblog.admin.model.vo.tag.SearchTagReqVO;
import com.oiazheng.weblog.common.utils.PageResponse;
import com.oiazheng.weblog.common.utils.Response;

public interface AdminTagService {
    /**
     * 添加标签集合
     * @param addTagReqVO
     * @return
     */
    Response addTag(AddTagReqVO addTagReqVO);

    /**
     * 标签分页数据查询
     * @param findTagPageListReqVO
     * @return
     */
    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    /**
     * 删除标签
     * @param deleteTagReqVO
     * @return
     */
    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    Response searchTag(SearchTagReqVO searchTagReqVO);


//
//    /**
//     * 获取文章标签的 Select 列表数据
//     * @return
//     */
//    Response finTagSelectList();
}
