package bf.ouaga.stage.sonabel.dao.gestionScelle;



import bf.ouaga.stage.sonabel.entities.gestionScelle.Agent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgentRepository extends JpaRepository<Agent,Long> {
    
        Boolean existsByMatricule(String matricule);
     
        List<Agent> findByMagasinsCodeExpl(Long codeExpl);
     

}
