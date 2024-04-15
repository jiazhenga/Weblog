package com.oiazheng.weblog.admin.service.impl;

import com.oiazheng.weblog.admin.model.vo.file.UploadFileRspVO;
import com.oiazheng.weblog.admin.service.AdminFileService;
import com.oiazheng.weblog.admin.utils.MinioUtil;
import com.oiazheng.weblog.common.enums.ResponseCodeEnum;
import com.oiazheng.weblog.common.exception.BizException;
import com.oiazheng.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class AdminFileServiceImpl implements AdminFileService {
    @Autowired
    private MinioUtil minioUtil;
    @Override
    public Response uploadFile(MultipartFile file) {
        try {
            String url = minioUtil.uploadFile(file);
            return Response.success(UploadFileRspVO.builder().url(url).build());
        } catch (Exception e) {
            log.error("upload file error", e);
            throw new BizException(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        }
    }
}
