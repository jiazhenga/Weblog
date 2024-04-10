package com.oiazheng.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oiazheng.weblog.admin.model.FindCategoryPageListReqVO;
import com.oiazheng.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.oiazheng.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.oiazheng.weblog.admin.model.vo.category.FindCategoryPageListRspVO;
import com.oiazheng.weblog.admin.service.AdminCategoryService;
import com.oiazheng.weblog.common.domain.dos.CategoryDO;
import com.oiazheng.weblog.common.domain.mapper.CategoryMapper;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     *
     * @param addCategoryReqVO
     * @return
     */
    @Override
    public Response addCategory(AddCategoryReqVO addCategoryReqVO) {
        String categoryName = addCategoryReqVO.getName();

        // 先判断该分类是否已经存在
        CategoryDO categoryDO = categoryMapper.selectByName(categoryName);

        if (Objects.nonNull(categoryDO)) {
            log.warn("分类命：{}，此分类已经存在.",categoryName);
            throw new BizException(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }

        // 构建 DO 类
        CategoryDO insertCategoryDo = CategoryDO.builder()
                .name(addCategoryReqVO.getName().trim())
                .build();

        // 执行insert
        categoryMapper.insert(insertCategoryDo);
        return Response.success();
    }

    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    @Override
    public PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {

        // 获取当前页、以及每页需要展示的数据数量
        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();

        // 分页对象(查询第几页、每页多少数据)
        Page<CategoryDO> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();

        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStartDate();
        LocalDate endDate = findCategoryPageListReqVO.getEndDate();

        wrapper
                .like(StringUtils.isNotBlank(name), CategoryDO::getName,name.trim()) // like 模块查询
                .ge(Objects.nonNull(startDate),CategoryDO::getCreateTime,endDate)   // 大于等于 startDate
                .le(Objects.nonNull(endDate),CategoryDO::getCreateTime,endDate)    // 小于等于 endDate
                .orderByDesc(CategoryDO::getCreateTime);                           // 按创建时间倒叙

        // 执行分页查询
        Page<CategoryDO> categoryDOPage = categoryMapper.selectPage(page,wrapper);

        List<CategoryDO> categoryDOS = categoryDOPage.getRecords();

        // DO 转 VO
        List<FindCategoryPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream()
                    .map(categoryDo -> FindCategoryPageListRspVO.builder()
                            .id(categoryDo.getId())
                            .name(categoryDo.getName())
                            .createTime(categoryDo.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }

        return PageResponse.success(categoryDOPage, vos);
    }

    @Override
    public Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO) {
        // 分类ID
        Long categoryId = deleteCategoryReqVO.getId();

        // 删除分类
        categoryMapper.deleteById(categoryId);

        return Response.success();
    }

    @Override
    public Response finCategorySelectList() {
        // 查询所有分类
        List<CategoryDO> categoryDOS = categoryMapper.selectList(null);

        // DO转VO
        List<SelectRspVO> selectRspVOS = null;
        // 如果分类数据不为空
        if (!CollectionUtils.isEmpty(categoryDOS)) {
            // 将分类 ID 作为Value 值，将分类名称作为label 展示
            selectRspVOS = categoryDOS.stream()
                    .map(categoryDO -> SelectRspVO.builder()
                            .label(categoryDO.getName())
                            .value(categoryDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(selectRspVOS);
    }


}
