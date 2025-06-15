package com.ges_abs.data.models.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ges_abs.data.models.enumeration.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(collection = "vigile")
public class Vigile extends AbstractEntity{
    @DBRef(lazy = true)
    private User user;
    @DBRef(lazy = true)
    private List<Pointage> pointageList = new ArrayList<>();

    public Vigile(Optional<User> user1) {
        this.user = user1.orElse(null);
    }
}
