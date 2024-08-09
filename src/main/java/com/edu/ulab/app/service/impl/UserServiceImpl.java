package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository repository;
    UserMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        var user = repository.save(mapper.userDtoToUser(userDto));
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        var userToUpdate = repository.findById(userDto.getId())
                .orElseThrow(() -> new NotFoundException("User with id " + userDto.getId() + " not found"));
        userToUpdate.setFullName(userDto.getFullName());
        userToUpdate.setTitle(userDto.getTitle());
        userToUpdate.setAge(userDto.getAge());
        return mapper.userToUserDto(repository.save(userToUpdate));
    }

    @Override
    public UserDto getUserById(Long id) {
        return repository.findById(id)
                .map(mapper::userToUserDto)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }
}
