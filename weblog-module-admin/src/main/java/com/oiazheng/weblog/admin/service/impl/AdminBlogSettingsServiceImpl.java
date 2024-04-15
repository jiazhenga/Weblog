package com.oiazheng.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oiazheng.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.oiazheng.weblog.admin.service.AdminBlogSettingsService;
import com.oiazheng.weblog.common.domain.dos.BlogSettingsDO;
import com.oiazheng.weblog.common.domain.mapper.BlogSettingsMapper;
import com.oiazheng.weblog.common.utils.Response;
import org.springframework.stereotype.Service;

@Service
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {
    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        // VO -> DO
        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
                .id(1L)
                .logo(updateBlogSettingsReqVO.getLogo())
                .name(updateBlogSettingsReqVO.getName())
                .author(updateBlogSettingsReqVO.getAuthor())
                .introduction(updateBlogSettingsReqVO.getIntroduction())
                .avatar(updateBlogSettingsReqVO.getAvatar())
                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
                .giteeHomepage(updateBlogSettingsReqVO.getGiteeHomepage())
                .csdnHomepage(updateBlogSettingsReqVO.getCsdnHomepage())
                .bilibiliHomepage(updateBlogSettingsReqVO.getBilibiliHomepage())
                .build();

        // 保存或更新（当数据库中存在 ID 为 1 的记录时，则执行更新操作，否则执行插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }
}
