package com.ges_abs.services.inter;

import com.ges_abs.data.models.entity.User;
import com.ges_abs.data.models.entity.Vigile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VigileService {
    Page<Vigile> findAllPaginate(Pageable pageable);
}
