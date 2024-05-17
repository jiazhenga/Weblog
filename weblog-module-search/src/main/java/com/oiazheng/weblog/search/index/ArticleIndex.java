package com.oiazheng.weblog.search.index;

/**
 * @author: 阿政a
 * @url: www.oiazheng.com
 * @date: 2023/11/27 13:57
 * @description: 文章索引
 **/
public interface ArticleIndex {
    /**
     * 索引名称
     */
    String NAME = "article";

    // --------------------- 文档字段 ---------------------
    String COLUMN_ID = "id";

    String COLUMN_TITLE = "title";

    String COLUMN_COVER = "cover";

    String COLUMN_SUMMARY = "summary";

    String COLUMN_CONTENT = "content";

    String COLUMN_CREATE_TIME = "createTime";
}
