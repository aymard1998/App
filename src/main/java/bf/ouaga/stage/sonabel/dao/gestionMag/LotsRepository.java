package bf.ouaga.stage.sonabel.dao.gestionMag;


import bf.ouaga.stage.sonabel.entities.gestionMag.Lots;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LotsRepository extends JpaRepository<Lots,Long> {
    Page<Lots> findAllByCouleurContaining(String color,Pageable pageable);

    Page<Lots> findAllByMagasinsCodeExpl(Long code,Pageable pageable);
    
    List<Lots> findByMagasinsCodeExpl(Long codeExpl);

    public boolean existsByNumLots(Long aLong);

    public Lots findAllByNumLots(Long aLong);
    
}
