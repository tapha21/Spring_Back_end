package com.ges_abs.data.models.entity;

import com.ges_abs.data.models.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "admin")
public class Admin extends User {

    public Admin(String login, String password, String nom, String prenom, String telephone, Role role) {
        super(login, password, nom, prenom, telephone, role);
    }

}
