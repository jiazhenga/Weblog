package com.oiazheng.weblog.web.model.vo.tag;

import com.oiazheng.weblog.common.model.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindTagArticlePageListReqVO extends BasePageQuery {
    /**
     * 标签 ID
     */
    @NotNull(message = "标签 ID 不能为空")
    private Long id;
}
