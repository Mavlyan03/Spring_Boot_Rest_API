package com.example.spring_boot_rest_api.api;

import com.example.spring_boot_rest_api.dto.Login;
import com.example.spring_boot_rest_api.dto.LoginResponse;
import com.example.spring_boot_rest_api.dto.UserRequest;
import com.example.spring_boot_rest_api.dto.UserResponse;
import com.example.spring_boot_rest_api.entity.User;
import com.example.spring_boot_rest_api.repository.UserRepository;
import com.example.spring_boot_rest_api.security.jwt.JwtTokenUtil;
import com.example.spring_boot_rest_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/jwt")
public class AuthController {

    private final UserService userService;
    private final UserRepository repository;
    private final JwtTokenUtil jwtTokenUtil;
    private final Login login;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
            authenticationManager.authenticate(token);
            User user = repository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(login.toLoginView(jwtTokenUtil.generateToken(user),"Successfully",user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(login.toLoginView("","Login_failed",null));
        }
    }

    @PostMapping("/registration")
    public UserResponse registration(@RequestBody UserRequest request) {
        return userService.create(request);
    }
}
