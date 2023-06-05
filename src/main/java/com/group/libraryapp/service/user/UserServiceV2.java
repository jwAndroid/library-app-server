package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest dto) {
        userRepository.save(new User(dto.getName() , dto.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest dto) {
        User user = userRepository.findById(dto.getId()).orElseThrow(IllegalAccessError::new);
        user.updateName(dto.getName());
//        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }

    @Transactional
    public void deleteUserHistory() {
        User user = userRepository.findByName("최지웅").orElseThrow(IllegalArgumentException::new);
        user.removeOneHistory();
    }

}
