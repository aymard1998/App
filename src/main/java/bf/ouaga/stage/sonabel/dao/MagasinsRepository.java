package bf.ouaga.stage.sonabel.dao;


import bf.ouaga.stage.sonabel.entities.Magasins;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MagasinsRepository extends JpaRepository<Magasins,Long> {

    //List Mag filles
   
    @Query("select codeExpl from Magasins where  nomMag like :e")
    public Long findBy(@Param("e") String nomMag);
    
     @Query("select o from Magasins o where o.nomMag like :e")
     public Long findByparent(@Param("e") String nomMag);
    

    
    Boolean existsByCodeExpl(Long codeExpl);

    Boolean existsByNomMag(String nomMag);
    
    List<Magasins> findByCodeExpl(Long codeExpl);
   
    List<Magasins> findByCodeExplParent(Long codeExpl);
   
       
}
