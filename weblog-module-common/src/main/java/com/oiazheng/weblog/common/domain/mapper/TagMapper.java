package com.oiazheng.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oiazheng.weblog.common.domain.dos.TagDO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public interface TagMapper extends BaseMapper<TagDO> {

    /**
     * 根据用户名查询
     * @param
     * @return
     */
    default Page<TagDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate) {
        // 分页对象
        Page<TagDO> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(Objects.nonNull(name), TagDO::getName, name)                // 名称模糊查询条件
                .ge(Objects.nonNull(startDate), TagDO::getCreateTime, startDate) // 大于等于开始日期的条件拼接
                .le(Objects.nonNull(endDate), TagDO::getCreateTime, endDate)    // 小于等于结束日期的条件拼接
                .orderByDesc(TagDO::getCreateTime);                            // 执行查询
        return this.selectPage(page, wrapper);
    }

    default List<TagDO> selectByKey(String key) {
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();

        // 构建查询条件
        wrapper.like(TagDO::getName, key).orderByDesc(TagDO::getCreateTime);
        return selectList(wrapper);
    }

    /**
     * 根据标签 ID 批量查询
     * @param tagIds
     * @return
     */
    default List<TagDO> selectByIds(List<Long> tagIds) {
        return selectList(Wrappers.<TagDO>lambdaQuery().in(TagDO::getId, tagIds));
    }


}
