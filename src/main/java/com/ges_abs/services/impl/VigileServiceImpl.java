package com.ges_abs.services.impl;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.entity.Vigile;
import com.ges_abs.data.repository.VigileRepository;
import com.ges_abs.services.inter.VigileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VigileServiceImpl implements VigileService {
    private final VigileRepository vigileRepository;

    public VigileServiceImpl(VigileRepository vigileRepository) {
        this.vigileRepository = vigileRepository;
    }

    @Override
    public Page<Vigile> findAllPaginate(Pageable pageable) {
        return vigileRepository.findAll(pageable);
    }
}


