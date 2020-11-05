package bf.ouaga.stage.sonabel.dao.gestionScelle;



import bf.ouaga.stage.sonabel.entities.gestionScelle.Pose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PoseRepository extends JpaRepository<Pose,Long> {
    Page<Pose> findAll(Pageable pageable);
    Page<Pose> findAllByCouleurContaining(String color,Pageable pageable);

    public Pose findAllByNumScelle(Long aL);
}
