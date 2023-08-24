package com.example.gasstations.services;

import com.example.gasstations.domain.dtos.binding.UserRegisterFormDto;
import com.example.gasstations.domain.entities.UserEntity;
import com.example.gasstations.domain.entities.enums.RoleNameEnum;
import com.example.gasstations.repositories.RoleRepository;
import com.example.gasstations.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements DataBaseInitService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean isDbInit() {
        return this.userRepository.count() == 0;
    }

    @Override
    public void dbInit() {
        UserEntity admin = new UserEntity().
                setFirstName("Admin").
                setLastName("Adminov").
                setEmail("admin@example.com").
                setUsername("admin").
                setPassword(passwordEncoder.encode("secret")).
                setRoles(this.roleRepository.findByRole(RoleNameEnum.valueOf("ADMIN")));

        saveUser(admin);
    }


    public void registerUser(UserRegisterFormDto userRegisterFormDto) {
        if (isDbInit()) {
            dbInit();
        }

        UserEntity userEntity = new UserEntity()
                .setFirstName(userRegisterFormDto.getFirstName())
                .setLastName(userRegisterFormDto.getLastName())
                .setEmail(userRegisterFormDto.getEmail())
                .setRoles(this.roleRepository.findByRole(RoleNameEnum.USER))
                .setUsername(userRegisterFormDto.getUsername())
                .setPassword(passwordEncoder.encode(userRegisterFormDto.getPassword()));

        saveUser(userEntity);

    }

    private void saveUser(UserEntity admin) {
        this.userRepository.saveAndFlush(admin);
    }
}
