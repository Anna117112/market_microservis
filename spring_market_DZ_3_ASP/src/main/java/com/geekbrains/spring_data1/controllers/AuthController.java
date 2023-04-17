package com.geekbrains.spring_data1.controllers;

;
import com.geekbrains.spring_data1.dto.JwtRequest;
import com.geekbrains.spring_data1.dto.JwtResponse;
import com.geekbrains.spring_data1.exceptions.AppError;
import com.geekbrains.spring_data1.services.UserService;
import com.geekbrains.spring_data1.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    // это энтпоинт не должен быть защищен иниче никто не сможет зайти в наше приложение
    // получаем логин пароль и отдаем токкен lesson 12 48:34
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        // UserDetails мин инфо о юзере спринговая на основе нашего юзера
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        // отправляем токен в ответ клиенту
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
//    @PostMapping("/!auth")
//    public UserDto saveNewUser(@RequestBody UserDto userDto) {
//        // создаем нового user и передаем ему параметры нового созданного dto
//        User user = userConverter.dtoToEntity(userDto);
//        // преверяем есть ли он в базе если нет то создаем нового
//        user = userService.findByUsername(user.getUsername()).orElse(userService.save(user));
//        // возвращаем dto
//        return userConverter.entityToDto(user);
//    }
}
