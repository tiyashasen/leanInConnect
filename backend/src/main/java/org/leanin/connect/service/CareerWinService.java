package org.leanin.connect.service;

import org.leanin.connect.domain.CareerWin;
import org.leanin.connect.repository.CareerWinRepository;
import org.leanin.connect.web.dto.CreateCareerWinRequest;
import org.springframework.stereotype.Service;
import java.util.List;

//Authentication and member identity are mocked. The prototype uses a fixed signed-in member to keep the assignment focused on the career-win workflow.

@Service
public class CareerWinService {
    private final CareerWinRepository repository;
    public CareerWinService(CareerWinRepository repository) { this.repository = repository; }
    public List<CareerWin> list() { return repository.findAllByOrderByCreatedAtDesc(); }
    public CareerWin create(CreateCareerWinRequest request) {
        return repository.save(new CareerWin("Tiyasha", request.message().trim()));
    }
}
