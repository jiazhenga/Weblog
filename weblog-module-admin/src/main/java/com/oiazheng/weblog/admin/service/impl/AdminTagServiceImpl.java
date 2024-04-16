package com.oiazheng.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oiazheng.weblog.admin.model.vo.tag.*;
import com.oiazheng.weblog.admin.service.AdminTagService;
import com.oiazheng.weblog.common.domain.dos.ArticleTagRelDO;
import com.oiazheng.weblog.common.domain.dos.TagDO;
import com.oiazheng.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.oiazheng.weblog.common.domain.mapper.TagMapper;
import com.oiazheng.weblog.common.enums.ResponseCodeEnum;
import com.oiazheng.weblog.common.exception.BizException;
import com.oiazheng.weblog.common.model.vo.SelectRspVO;
import com.oiazheng.weblog.common.utils.PageResponse;
import com.oiazheng.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements AdminTagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    /**
     * 添加标签集合
     *
     * @param addTagReqVO
     * @return
     */
    @Override
    public Response addTag(AddTagReqVO addTagReqVO) {
        //vo 转 do
        List<TagDO> tagDOS = addTagReqVO.getTags().stream()
               .map(tagName -> TagDO.builder()
                       .name(tagName.trim())
                       .createTime(LocalDateTime.now())
                       .updateTime(LocalDateTime.now())
                       .build())
               .collect(Collectors.toList());

        // 批量插入
        try {
            saveBatch(tagDOS);
        }catch (Exception e) {
            log.warn("该标签已存在",e);
        }

        return Response.success();
    }

    /**
     * 标签分页数据查询
     * @param findTagPageListReqVO
     * @return
     */
    @Override
    public PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO) {

        // 分页参数 及 查询条件参数
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();
        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();




        // 分页对象(查询第几页、每页多少数据)
        Page<TagDO> page = tagMapper.selectPageList(current, size, name, startDate, endDate);

        List<TagDO> records = page.getRecords();



        // DO 转 VO
        List<FindTagPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(records)) {
            vos = records.stream()
                    .map(tagDo -> FindTagPageListRspVO.builder()
                            .id(tagDo.getId())
                            .name(tagDo.getName())
                            .createTime(tagDo.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }

        return PageResponse.success(page, vos);
    }

    @Override
    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
        // 标签 ID
        Long tagId = deleteTagReqVO.getId();

        // 校验该标签下是否有关联的文章，若有，则不允许删除，提示用户需要先删除标签下的文章
        ArticleTagRelDO articleTagRelDO = articleTagRelMapper.selectOneByTagId(tagId);

        if (Objects.nonNull(articleTagRelDO)) {
            log.warn("==> 此标签下包含文章，无法删除，tagId: {}", tagId);
            throw new BizException(ResponseCodeEnum.TAG_CAN_NOT_DELETE);
        }

        // 根据标签 ID 删除
        int count = tagMapper.deleteById(tagId);

        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.TAG_NOT_EXISTED);
    }

    @Override
    public Response searchTag(SearchTagReqVO searchTagReqVO) {
        String key = searchTagReqVO.getKey();

        // 执行查询
        List<TagDO> tagDOS = tagMapper.selectByKey(key);

        // DO 转 VO
        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }
//
//    @Override
//    public Response finTagSelectList() {
//        // 查询所有标签
//        List<TagDO> tagDOS = tagMapper.selectList(null);
//
//        // DO转VO
//        List<SelectRspVO> selectRspVOS = null;
//        // 如果标签数据不为空
//        if (!CollectionUtils.isEmpty(tagDOS)) {
//            // 将标签 ID 作为Value 值，将标签名称作为label 展示
//            selectRspVOS = tagDOS.stream()
//                    .map(tagDO -> SelectRspVO.builder()
//                            .label(tagDO.getName())
//                            .value(tagDO.getId())
//                            .build())
//                    .collect(Collectors.toList());
//        }
//        return Response.success(selectRspVOS);
//    }


}
