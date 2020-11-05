/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.dao.gestionMag;

import bf.ouaga.stage.sonabel.entities.gestionMag.Scelles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aymard
 */
public interface ScellesRepository extends JpaRepository<Scelles,Long>{

    public boolean existsByNumScelle(Long aLong);

    public Scelles findAllByNumScelle(Long aLong);
    
    
    
}
