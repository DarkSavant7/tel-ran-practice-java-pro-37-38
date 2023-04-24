package de.telran.marketapp.services;

import de.telran.marketapp.client.FakeExternalServiceClient;
import de.telran.marketapp.dto.client.FakeClientDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FakeClientService {

    FakeExternalServiceClient fakeExternalServiceClient;

    public FakeClientDto getFakeClientDtoById(UUID id) {
        //...... some logic
        return fakeExternalServiceClient.getFakeDtoById(id);
    }
}
