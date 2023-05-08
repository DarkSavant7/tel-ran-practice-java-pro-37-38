package de.telran.marketapp.web;

import de.telran.marketapp.dto.UserRegisterDto;
import de.telran.marketapp.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegistrationController {

    UserService userService;

    @PostMapping
    public ResponseEntity<Void> register(UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return ResponseEntity.status(HttpURLConnection.HTTP_CREATED).build();
    }

}
