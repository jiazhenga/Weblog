package com.oiazheng.weblog.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oiazheng.weblog.common.domain.dos.ArticleDO;
import com.oiazheng.weblog.common.domain.dos.ArticleTagRelDO;
import com.oiazheng.weblog.common.domain.dos.TagDO;
import com.oiazheng.weblog.common.domain.mapper.ArticleMapper;
import com.oiazheng.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.oiazheng.weblog.common.domain.mapper.TagMapper;
import com.oiazheng.weblog.common.enums.ResponseCodeEnum;
import com.oiazheng.weblog.common.exception.BizException;
import com.oiazheng.weblog.common.utils.PageResponse;
import com.oiazheng.weblog.common.utils.Response;
import com.oiazheng.weblog.web.convert.ArticleConvert;
import com.oiazheng.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;
import com.oiazheng.weblog.web.model.vo.tag.FindTagArticlePageListRspVO;
import com.oiazheng.weblog.web.model.vo.tag.FindTagListRspVO;
import com.oiazheng.weblog.web.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Response findTagList() {
        // 查询所有标签
        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());

        // DO 转 VO
        List<FindTagListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> FindTagListRspVO.builder()
                            .id(tagDO.getId())
                            .name(tagDO.getName())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }

    /**
     * 获取标签下文章分页列表
     *
     * @param findTagArticlePageListReqVO
     * @return
     */
    @Override
    public Response findTagPageList(FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        Long current = findTagArticlePageListReqVO.getCurrent();
        Long size = findTagArticlePageListReqVO.getSize();
        // 标签 ID
        Long tagId = findTagArticlePageListReqVO.getId();

        // 判断该标签是否存在
        TagDO tagDO = tagMapper.selectById(tagId);
        if (Objects.isNull(tagDO)) {
            log.warn("==> 该标签不存在, tagId: {}", tagId);
            throw new BizException(ResponseCodeEnum.TAG_NOT_EXISTED);
        }

        // 先查询该标签下所有关联的文章 ID
        List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByTagId(tagId);

        // 若该标签下未发布任何文章
        if (CollectionUtils.isEmpty(articleTagRelDOS)) {
            log.info("==> 该标签下还未发布任何文章, tagId: {}", tagId);
            return PageResponse.success(null, null);
        }

        // 提取所有文章 ID
        List<Long> articleIds = articleTagRelDOS.stream().map(ArticleTagRelDO::getArticleId).collect(Collectors.toList());

        // 根据文章 ID 集合查询文章分页数据
        Page<ArticleDO> page = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOS = page.getRecords();

        // DO 转 VO
        List<FindTagArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            vos = articleDOS.stream()
                    .map(ArticleConvert.INSTANCE::convertDO2TagArticleVO)
                    .collect(Collectors.toList());
        }

        return PageResponse.success(page, vos);
    }

}
