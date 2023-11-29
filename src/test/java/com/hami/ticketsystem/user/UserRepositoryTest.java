package com.hami.ticketsystem.user;


import com.hami.ticketsystem.entity.Role;
import com.hami.ticketsystem.entity.User;
import com.hami.ticketsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "12345";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        User newUser = new User("hamed", "zarrabi", "hamed.zarrabi@gmail.com", encodedPassword);

        User savedUser = userRepository.save(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAssignRolesToUser() {
        Long userId = 1l;
        Integer roleId = 1;

        User user = userRepository.findById(userId).get();
        user.addRole(new Role(roleId));

        User updateUser = userRepository.save(user);
        assertThat(updateUser.getRoles()).hasSize(1);
    }
}
