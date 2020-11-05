package bf.ouaga.stage.sonabel.entities.gestionScelle;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Depose implements Serializable {
    @Id @GeneratedValue
    private int id;
    private Long numScel;
    String couleur;
    String dateDepose;
    private String section;
    private int lot;
    private String parcelle;
   private String tableau;
   private String matricule;
   private String type;
   
   @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "numEquipe",referencedColumnName = "numEquipe",insertable = true,updatable = true,nullable = false)
    private Equipe equipe;
}
