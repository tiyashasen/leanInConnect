package org.leanin.connect.web;

import jakarta.validation.Valid;
import org.leanin.connect.domain.CareerWin;
import org.leanin.connect.service.CareerWinService;
import org.leanin.connect.web.dto.CreateCareerWinRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** HTTP contract consumed by the Momentum Feed. */
@RestController
@RequestMapping("/api/wins")
public class CareerWinController {

    private final CareerWinService service;

    public CareerWinController(CareerWinService service) {
        this.service = service;
    }

    @GetMapping
    public List<CareerWin> list() {
        return service.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CareerWin create(
            @Valid @RequestBody CreateCareerWinRequest request) {
        return service.create(request);
    }
}