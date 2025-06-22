package br.com.amitta.real_state.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.amitta.real_state.entities.RealState;
import java.util.UUID;

/*public interface RealStateRepository extends JpaRepository<RealState, Long> {
}*/

public interface RealStateRepository extends JpaRepository<RealState, UUID> {
}

