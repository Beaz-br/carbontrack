package br.com.fiap.repository;

import br.com.fiap.model.MitigationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MitigationScheduleRepository extends JpaRepository<MitigationSchedule, Long> {
}
