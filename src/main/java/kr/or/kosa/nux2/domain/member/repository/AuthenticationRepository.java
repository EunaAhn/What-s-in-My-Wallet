package kr.or.kosa.nux2.domain.member.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class AuthenticationRepository {
    private final RedisTemplate<String, String> redisTemplate;


    public void delete(String memberId) {
        redisTemplate.delete(memberId);
    }

    public void save(String memberId, String authenticationNumber) {
        redisTemplate.opsForValue().set(memberId, authenticationNumber);
        redisTemplate.expire(memberId, 5, TimeUnit.MINUTES);
    }

    public String findById(String memberId) {
        String authenticationNumber = String.valueOf(redisTemplate.opsForValue().get(memberId));

        if (authenticationNumber == null) {
            return null;
        }
        return authenticationNumber;
    }
}
