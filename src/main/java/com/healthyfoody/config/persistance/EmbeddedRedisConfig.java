package com.healthyfoody.config.persistance;

import static com.healthyfoody.config.ApplicationProfiles.DEV;
import static com.healthyfoody.config.ApplicationProfiles.TEST;

import java.util.Optional;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import redis.embedded.RedisServer;

@Configuration
@Profile({ DEV, TEST })
public class EmbeddedRedisConfig implements InitializingBean, DisposableBean {

	@Value("${spring.redis.port}")
	private int redisPort;

	private RedisServer redisServer;

	@Override
	public void destroy() throws Exception {
		Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		redisServer = new RedisServer(redisPort);
		redisServer.start();
	}

}
