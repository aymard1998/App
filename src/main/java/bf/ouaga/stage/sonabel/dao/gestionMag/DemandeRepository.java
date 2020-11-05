package bf.ouaga.stage.sonabel.dao.gestionMag;


import bf.ouaga.stage.sonabel.entities.gestionMag.Demande;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DemandeRepository extends JpaRepository<Demande,Long> {
    
       List<Demande> findAllByMagasinsCodeExpl(Long codeExpl);
  

}
