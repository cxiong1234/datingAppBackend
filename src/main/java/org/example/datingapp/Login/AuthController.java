package org.example.datingapp.Login;

import org.example.datingapp.Friendship.entities.UserEntity;
import org.example.datingapp.Friendship.repositories.UserRepository;
import org.example.datingapp.Login.dto.LoginDto;
import org.example.datingapp.Login.dto.RegisterDto;
import org.example.datingapp.Profile.ProfileResponse;
import org.example.datingapp.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }

        UserEntity user = new UserEntity();
        user.setEmail(registerDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        // 明文方便测试
        user.setPassword(registerDto.getPassword());
        user.setNickname(registerDto.getNickname());
        // hard code url
        user.setUrl("https://png.pngtree.com/png-clipart/20211216/ourmid/pngtree-green-valentine-s-day-than-heart-confession-boy-cartoon-wechat-avatar-png-image_4068106.png");
        userRepository.save(user);

        return ResponseEntity.ok(new LoginResponse(
                convertToResponse(user),
                jwtTokenUtil.generateToken(user)
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Optional<UserEntity> existingUser = userRepository.findByEmail(loginDto.getEmail());
        if (existingUser.isEmpty()) {
            return ResponseEntity.ok(
                    new ErrorResponse("User not found with email: " + loginDto.getEmail())
            );
        } else {
            UserEntity user = existingUser.get();
            if (!loginDto.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok(
                        new ErrorResponse("Invalid password")
                );
            } else {
                return ResponseEntity.ok(new LoginResponse(
                        convertToResponse(user),
                        jwtTokenUtil.generateToken(user)
                ));
            }
        }
    }

    private ProfileResponse convertToResponse(UserEntity user) {
        return new ProfileResponse(user.getEmail(), user.getNickname(), user.getUrl());
    }
}
