package felipefortu.Stores.infra.repository;

import felipefortu.Stores.domain.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, String> {


}
