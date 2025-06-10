package com.ges_abs.data.repository;
import com.ges_abs.data.models.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {

}
