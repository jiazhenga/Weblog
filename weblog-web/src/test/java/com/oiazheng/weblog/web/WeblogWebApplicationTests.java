package com.oiazheng.weblog.web;

import com.oiazheng.weblog.common.domain.dos.UserDO;
import com.oiazheng.weblog.common.domain.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class WeblogWebApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertTest() {
        // 构建数据库实体类
        UserDO userDo = UserDO.builder()
                .username("阿政a")
                .password("636395")
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();

        userMapper.insert(userDo);
    }

}
