package de.telran.marketapp.web;

import de.telran.marketapp.beans.JwtTokenUtil;
import de.telran.marketapp.dto.JwtRequest;
import de.telran.marketapp.dto.JwtResponse;
import de.telran.marketapp.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    UserService userService;
    JwtTokenUtil jwtTokenUtil;
    AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest jwtRequest) {
        log.info("User trying to log in with login: {}", jwtRequest.getUsername());
        var user = userService.getByUsername(jwtRequest.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(),
                jwtRequest.getPassword()));

        var token = jwtTokenUtil.generateToken(user);
        log.info("Success logged user {} with roles: {}", user.getLogin(), user.getRoles());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
