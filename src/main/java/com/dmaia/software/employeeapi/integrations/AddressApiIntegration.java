package com.dmaia.software.employeeapi.integrations;

import com.dmaia.software.employeeapi.exeptions.ObjectNotFoundExeption;
import com.dmaia.software.employeeapi.services.redis.AddressRedisService;
import com.dmaia.software.provider.model.AddressResponseVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AddressApiIntegration {

    @Value("${api.base.url.address}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AddressRedisService redisService;


    @CircuitBreaker(name = "address", fallbackMethod = "getAddressFallback")
    public AddressResponseVO findByZipCode(String zipCode){
        log.info("Start findByZipCode for AddressApiIntegration");
        try {
            String path = "api/v1/addresses/find-by-zipcode?zipCode=";

            String url = baseUrl.concat(path + zipCode);

            var addressResponse = restTemplate.getForObject(url, AddressResponseVO.class);

            redisService.save(addressResponse, zipCode);
            return addressResponse;

        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
            throw new ObjectNotFoundExeption("Address not found.", HttpStatus.FOUND);
        }
    }

    private AddressResponseVO getAddressFallback(String zipCode, Throwable cause){
        log.warn("[WARN] Fallback with id {}", zipCode);
        return redisService.findByZipCode(zipCode);
    }
}
