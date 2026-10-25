package pharmacy.example.pharmacy.repository;

import pharmacy.example.pharmacy.entity.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

}
