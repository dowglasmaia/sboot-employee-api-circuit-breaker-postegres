package com.dmaia.software.employeeapi.integrations;

import com.dmaia.software.provider.model.AddressResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AddressApiIntegration {

    @Value("${api.base.url.address}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;


    public AddressResponseVO findByZipCode(String zipCode){
        try {
        String path = "api/v1/addresses/find-by-zipcode?zipCode=";

        String url = baseUrl.concat(path + zipCode);

        return restTemplate.getForObject(url, AddressResponseVO.class);

        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

    }

}
