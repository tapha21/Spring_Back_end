package com.ges_abs.mobile.controller.inter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@RequestMapping("/api/mobile/users")
public interface UserController {

    @GetMapping("")
    ResponseEntity<Map<String, Object>> getAll(  @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size);

    @GetMapping("/role/{role}")
    ResponseEntity<Map<String, Object>> getByRole(@PathVariable String role);

}
