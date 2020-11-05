package bf.ouaga.stage.sonabel.dao.gestionMag;



import bf.ouaga.stage.sonabel.entities.gestionMag.Operations;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OperationRepository extends JpaRepository<Operations,Long> {
    
     List<Operations> findByMagasinsCodeExpl(Long codeExpl);
    
}
