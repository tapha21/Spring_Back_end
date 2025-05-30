package com.ges_abs.data.models.entity;

import com.ges_abs.data.mock.AbstractEntity;
import com.ges_abs.data.models.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "user")
public class User extends AbstractEntity {
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String telephone;
    private Role role;
}
