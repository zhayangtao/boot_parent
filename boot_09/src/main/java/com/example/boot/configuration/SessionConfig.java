package com.example.boot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2017/12/29
 */
@Configuration
// maxInactiveIntervalInSeconds: 设置 Session 失效时间，
// 使用 Redis Session 之后，原 Boot 的 server.session.timeout 属性不再生效。
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class SessionConfig {
}
