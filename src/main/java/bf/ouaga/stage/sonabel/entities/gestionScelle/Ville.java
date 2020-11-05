/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.entities.gestionScelle;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Aymard
 */
@Entity @NoArgsConstructor @AllArgsConstructor @Data
public class Ville implements Serializable {
    @Id @GeneratedValue
    private  Long id;
    private  String nomVille;
}
