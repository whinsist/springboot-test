package com.zimug.bootlaunch.service.test.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Hong.Wu
 * @date: 14:19 2021/02/13
 */
@Service
@Slf4j
public class TestLogService {

    public void testlogLevel(){
        System.out.println("isDebugEnabled=" + log.isDebugEnabled() + " isInfoEnabled=" + log.isInfoEnabled() + " isErrorEnabled=" + log.isErrorEnabled());
        log.debug("-----debug-----");
        log.info("-----info-----");
        log.warn("-----warn-----");
        log.error("-----error-----");

        try {
            System.out.println(12/0);
        } catch (Exception e) {
            log.error("操作失败：", e);
        }

    }
}
