package bf.ouaga.stage.sonabel.dao.gestionScelle;




import bf.ouaga.stage.sonabel.entities.gestionScelle.Equipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquipeRepository extends JpaRepository<Equipe,Long> {
    Boolean existsByNumEquipe(Long numEquipe);

    Boolean existsByNomEquipe(String nomEquipe);
    
   List<Equipe> findByMagasinsCodeExpl(Long codeExpl);
    
        @Query("Select numEquipe From Equipe Where nomEquipe =:e")
    public Long findBy(@Param("e") String nomEquipe);

    public Equipe findByNumEquipe(Long equ);
    
}
