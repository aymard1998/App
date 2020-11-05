/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.dao.gestionScelle;

import bf.ouaga.stage.sonabel.entities.gestionScelle.Typo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aymard
 */
public interface TypoRepository  extends JpaRepository<Typo,Long> {
    
}
