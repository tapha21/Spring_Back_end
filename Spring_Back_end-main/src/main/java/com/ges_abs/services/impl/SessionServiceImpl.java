package com.ges_abs.services.impl;

import com.ges_abs.data.repository.SessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ges_abs.data.models.entity.Session;
import com.ges_abs.services.inter.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    SessionRepository sessionRepository;
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
    @Override
    public Page<Session> findAllPaginate(Pageable pageable) {
        return sessionRepository.findAll(pageable);
    }

    @Override
    public Session findById(String id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }
}
