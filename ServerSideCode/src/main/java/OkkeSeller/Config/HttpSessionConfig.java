package OkkeSeller.Config;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@EnableRedisHttpSession

public class HttpSessionConfig {
	
	
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory();
	}

}