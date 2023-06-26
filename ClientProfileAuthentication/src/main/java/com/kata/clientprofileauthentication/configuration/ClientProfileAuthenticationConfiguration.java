package com.kata.clientprofileauthentication.configuration;

import com.kata.clientprofileauthentication.models.ProfileToken;
import com.kata.clientprofileauthentication.repository.ProfileTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableRedisRepositories
@Slf4j
public class ClientProfileAuthenticationConfiguration {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll();
        return http.build();
    }
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setPort(6379);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
    @Bean
    public CommandLineRunner commandLineRunner(ProfileTokenRepository repo) {
        return args -> {
            ProfileToken token1 = new ProfileToken("f188bc1e-439c-467a-8b87-e25b646ba87f",
                    "JwtBearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlIjoiQ1JFRElUIiwiaWF0IjoxNjg3NTE1Nzk2LCJleHAiOjE2ODc1MTYwOTZ9.-0hz7JRx9PekRtUcuUx0_h4JcHnpaQB9gE52tpHbJlRcQ2CiZy8-2drQDkOsNrCp",
                    "eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE2ODc1MTU3OTYsImV4cCI6MTY4NzYwMjE5Nn0.9GYkYVhFBcQFWiPtdC6lQQfclVqGzZwae9Fdm520Or5jn7-59JTQ1Pak_-MeeCyq",
                    "Bearer N2PmDuURGR2r766p0Q3epRUOBUqNZwZYLzNQ7XK5sNV1GW0URsfDBOdleNLXJZBG");
            ProfileToken token2 = new ProfileToken("d8e56e5e-f7f7-4fa0-9bfa-5c06b6e3b831",
                    "JwtBearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlIjoiQ1JFRElUIiwiaWF0IjoxNjg3NTE1Nzk0LCJleHAiOjE2ODc1MTYwOTR9.UTM6pblNkysPVYKgFpxGR35jMGUTe7DD092AIZ_NtPwhh2S2HhL0nA_04lYvRGGJ",
                    "eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE2ODc1MTU3OTQsImV4cCI6MTY4NzYwMjE5NH0.w0FSRrZdesMJCVeTmYildOdM42z_gj7thXO2xy5cUK2u7uXTec9vHCCEiwUAcaIG",
                    "Bearer VjNe4crMNiNnuYpPMGVjNfPT3ixtwgpPwhH9XkMfxDejaUVc5mOmjaIzxH30blIf");
            repo.deleteAll();
            repo.save(token1);
            repo.save(token2);
        };
    }
}
