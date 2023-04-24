package de.telran.marketapp.client;


import com.github.tomakehurst.wiremock.client.WireMock;
import de.telran.marketapp.services.FakeClientService;
import de.telran.marketapp.util.FakeHolder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.junit.jupiter.api.Assertions.assertEquals;

//https://wiremock.org/
@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class FakeClientTest {

    @Autowired
    FakeExternalServiceClient fakeExternalServiceClient;
    @Autowired
    FakeClientService fakeClientService;
    @Autowired
    RestTemplate restTemplate;

//    @BeforeEach
//    public void init() {
//        this.fakeClientService = new FakeClientService(fakeExternalServiceClient);
//    }
    @Test
    public void testFakeClientService() {
        WireMock.any(urlMatching("/fake/client/*"))
//        stubFor(get("https://google.com/b09fb1df-10af-49b9-ad6c-11341690418e")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(FakeHolder.FAKE_RESPONCE));
        var id = UUID.fromString("b09fb1df-10af-49b9-ad6c-11341690418e");
        var result = fakeClientService.getFakeClientDtoById(id);
        assertEquals(BigDecimal.valueOf(14), result.getPrice());
    }
}
