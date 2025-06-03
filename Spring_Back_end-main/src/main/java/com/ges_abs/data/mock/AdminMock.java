package com.ges_abs.data.mock;

import com.ges_abs.data.models.entity.Admin;
import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.enumeration.Role;
import com.ges_abs.data.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
//@Component
public class AdminMock implements CommandLineRunner {

    private final AdminRepository adminRepository;

    public AdminMock(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.count() == 0) {
            List<Admin> admins = Arrays.asList(
                    new Admin(new User("admin1", "pass1", "Attolode", "Victorin", "779952955", Role.ADMIN)),
                    new Admin(new User("admin2", "pass2", "Diop", "Papa Mbaye", "773646282", Role.ADMIN)),
                    new Admin(new User("admin3", "pass3", "Tchassakou", "Yao", "770000003", Role.ADMIN)),
                    new Admin(new User("admin4", "pass4", "Dossou", "Cyrille", "770000004", Role.ADMIN)),
                    new Admin(new User("admin5", "pass5", "Amegbo", "Julien", "770000005", Role.ADMIN))
            );

            adminRepository.saveAll(admins);
            log.info("5 administrateurs ont été créés.");
        }
    }
}
