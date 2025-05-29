package com.ges_abs.services.inter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Session;

public interface SessionService {
    Page<Session> findAllPaginate(Pageable pageable);
    Session findById(String id);
}
