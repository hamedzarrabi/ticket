package com.hami.ticketsystem.role;

import com.hami.ticketsystem.entity.Role;
import com.hami.ticketsystem.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired private RoleRepository roleRepository;

    @Test
    public void testCreateRoles() {
        Role admin = new Role("ROLE_ADMIN");
        Role customer = new Role("ROLE_CUSTOMER");
        Role support = new Role("ROLE_SUPPORT");

        roleRepository.saveAll(List.of(
                admin,
                customer,
                support
        ));

        long numberOfRole = roleRepository.count();
        assertEquals(3, numberOfRole);
    }

    @Test
    public void testListRoles() {
        List<Role> roleList = roleRepository.findAll();
        assertThat(roleList.size()).isGreaterThan(0);

        roleList.forEach(System.out::println);
    }


}
