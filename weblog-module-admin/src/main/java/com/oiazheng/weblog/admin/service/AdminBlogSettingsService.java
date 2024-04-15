package com.oiazheng.weblog.admin.service;

import com.oiazheng.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.oiazheng.weblog.common.utils.Response;
import org.springframework.stereotype.Service;


public interface AdminBlogSettingsService {
    /**
     * 更新博客设置信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);
}
