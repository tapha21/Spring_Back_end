package com.ges_abs.data.mock;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public abstract class AbstractEntity {
    @Id
    private String id;
}
