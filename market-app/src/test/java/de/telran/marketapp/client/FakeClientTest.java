package de.telran.marketapp.client;


import de.telran.marketapp.services.FakeClientService;
import de.telran.marketapp.util.FakeHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;

//https://wiremock.org/
@SpringBootTest
public class FakeClientTest {

    @Autowired
    DemoClient demoClient;
    @Autowired
    FakeClientService fakeClientService;

//    @Test
    public void testFakeClientService() {
        stubFor(get("https://google.com/b09fb1df-10af-49b9-ad6c-11341690418e")
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(FakeHolder.FAKE_RESPONCE)));
        var id = UUID.fromString("b09fb1df-10af-49b9-ad6c-11341690418e");
        var result = fakeClientService.getFakeClientDtoById(id);
        assertEquals(BigDecimal.valueOf(14), result.getPrice());
    }
}
