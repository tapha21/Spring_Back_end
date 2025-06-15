package com.ges_abs.data.models.entity;

import com.ges_abs.data.models.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "admin")
public class Admin {
@DBRef(lazy = true)
    private User user;

}
