package br.com.fiap.repository;

import br.com.fiap.model.Emission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissionRepository extends JpaRepository<Emission, Long> {
}
