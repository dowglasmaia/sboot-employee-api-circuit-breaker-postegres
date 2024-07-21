package com.dmaia.software.employeeapi.services.redis;


import com.dmaia.software.provider.model.AddressResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressRedisService {

    private static final String KEY = "address";

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(AddressResponseVO addresse, String zipCode){
        log.info("Start save cache Address. {}", addresse);
        redisTemplate.opsForHash().put(KEY, zipCode, addresse);
    }

    public AddressResponseVO findByZipCode(String zipCode){
        log.info("Start findByZipCode - cache Address");
        return (AddressResponseVO) redisTemplate.opsForHash().get(KEY, zipCode);
    }
}
