package de.telran.marketapp.web;

import de.telran.marketapp.beans.JwtTokenUtil;
import de.telran.marketapp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest jwtRequest) {
        log.info("User trying to log in with login: {}", jwtRequest.getUsername());
        User user = userService.findByUsernameOrEmailOrPhone(jwtRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                jwtRequest.getPassword()));

        var token = jwtTokenUtil.generateTokenFromUser(user);
        log.info("Success logged user {} with roles: {}", user.getUsername(), user.getRoles());
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
