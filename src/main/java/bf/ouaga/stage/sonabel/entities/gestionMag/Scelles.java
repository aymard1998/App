/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.entities.gestionMag;

import bf.ouaga.stage.sonabel.entities.Magasins;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Aymard
 */

@Entity @Data @NoArgsConstructor  @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Scelles implements Serializable{
    
        @Id @GeneratedValue
    private Long id;
    private Long numScelle;
    private String couleur;
    
   
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "numLots",referencedColumnName="numLots",insertable=true,updatable=true,nullable=true)
    private Lots lots   ;
    
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "codeExpl" ,referencedColumnName="codeExpl",insertable=true,updatable=true,nullable=true)
    private Magasins magasins;
}
