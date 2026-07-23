package org.leanin.connect.repository;

import org.leanin.connect.domain.CareerWin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/** Data-access boundary for career wins. */
public interface CareerWinRepository extends JpaRepository<CareerWin, Long> {
    List<CareerWin> findAllByOrderByCreatedAtDesc();
}
