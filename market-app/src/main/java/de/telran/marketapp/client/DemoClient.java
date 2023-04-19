package de.telran.marketapp.client;

import de.telran.marketapp.dto.client.FakeClientDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RequiredArgsConstructor
public class DemoClient {

    @Value("${rest.client.fake-url}")
    @NonFinal
    String url;

    RestTemplate restTemplate;

    public FakeClientDto getFakeDtoById(UUID id) {
        return restTemplate.getForObject(url + "/" + id.toString(), FakeClientDto.class);
    }
}
