package de.telran.marketapp.beans;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class DateTimeProvider {
    public OffsetDateTime currentDateTime() {
        return OffsetDateTime.now();
    }
}
