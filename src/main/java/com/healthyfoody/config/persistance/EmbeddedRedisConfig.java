package com.healthyfoody.config.persistance;

import static com.healthyfoody.config.ApplicationProfiles.DEV;
import static com.healthyfoody.config.ApplicationProfiles.TEST;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		if  (available(redisPort)) {
			redisServer = new RedisServer(redisPort);
			redisServer.start();
		}
		else {
			Logger logger = LoggerFactory.getLogger(EmbeddedRedisConfig.class);
			logger.warn("Port " + redisPort + " is already in use");
		}

	}

	private boolean available(int port) {
		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					/* should not be thrown */
				}
			}
		}
		return false;
	}
}
