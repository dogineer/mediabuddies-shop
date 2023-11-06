package com.point.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserRepository userRepository;

    @Transactional
    @PostMapping("/join")
    public void joinUser(@RequestBody String name){
        User insertUser = userRepository.save(User.builder()
            .id(UUID.randomUUID())
            .name(name)
            .build());

        userRepository.save(insertUser);
    }
}
