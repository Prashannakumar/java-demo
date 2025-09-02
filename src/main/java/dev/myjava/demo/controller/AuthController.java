package dev.myjava.demo.controller;

import dev.myjava.demo.models.User;
import dev.myjava.demo.repository.UserRepository;
import dev.myjava.demo.service.UserService;
import dev.myjava.demo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body){
        String email = body.get("email");
        String password = body.get("password");
        System.out.println(email + ' ' + password);

        password = passwordEncoder.encode(password);

        if(userRepository.findByEmail(email).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exits");
        }
        userService.createUser(User.builder().email(email).password(password).build());
        return new ResponseEntity<>("Successfully Registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body){
        String email = body.get("email");
        String password = body.get("password");
        System.out.println(email + "email password" + password);

        var userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            System.out.println("userOptional.isEmpty()");
            return new ResponseEntity<>("User not registered", HttpStatus.UNAUTHORIZED);
        }
        User user = userOptional.get();
        if(!passwordEncoder.matches(password, user.getPassword())){
            System.out.println("passwordEncoder.matches");
            return new ResponseEntity<>("Invalid user", HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(email);
        System.out.println("token" + token);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
