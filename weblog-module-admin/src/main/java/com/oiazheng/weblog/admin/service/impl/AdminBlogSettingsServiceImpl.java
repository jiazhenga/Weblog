package com.oiazheng.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oiazheng.weblog.admin.convert.BlogSettingsConvert;
import com.oiazheng.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.oiazheng.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.oiazheng.weblog.admin.service.AdminBlogSettingsService;
import com.oiazheng.weblog.common.domain.dos.BlogSettingsDO;
import com.oiazheng.weblog.common.domain.mapper.BlogSettingsMapper;
import com.oiazheng.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {

    @Autowired
    private BlogSettingsMapper blogSettingsMapper;
    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        // VO -> DO
//        BlogSettingsDO blogSettingsDO = BlogSettingsDO.builder()
//                .id(1L)
//                .logo(updateBlogSettingsReqVO.getLogo())
//                .name(updateBlogSettingsReqVO.getName())
//                .author(updateBlogSettingsReqVO.getAuthor())
//                .introduction(updateBlogSettingsReqVO.getIntroduction())
//                .avatar(updateBlogSettingsReqVO.getAvatar())
//                .githubHomepage(updateBlogSettingsReqVO.getGithubHomepage())
//                .giteeHomepage(updateBlogSettingsReqVO.getGiteeHomepage())
//                .csdnHomepage(updateBlogSettingsReqVO.getCsdnHomepage())
//                .bilibiliHomepage(updateBlogSettingsReqVO.getBilibiliHomepage())
//                .build();
        BlogSettingsDO blogSettingsDO = BlogSettingsConvert.INSTANCE.convertVO2DO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1L);
        // 保存或更新（当数据库中存在 ID 为 1 的记录时，则执行更新操作，否则执行插入操作）
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }

    @Override
    public Response findDetail() {
        // 查询数据库中 ID 为 1 的记录
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);


        // DO -> VO
        FindBlogSettingsRspVO vo = BlogSettingsConvert.INSTANCE.convertDao2vo(blogSettingsDO);

        return Response.success(vo);
    }
}
