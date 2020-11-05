package bf.ouaga.stage.sonabel.entities.gestionMag;

import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Equipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Data @NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Operations implements Serializable {
    @Id @GeneratedValue
    private Long idOpt;
    private  String couleur;
    private String dateOperation;

   @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "magCodeExpl",referencedColumnName="codeExpl",insertable=true,updatable=true,nullable=true)
    private Magasins magasins;
   
   @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "magCodeExplParent",referencedColumnName="codeExplParent",insertable=true,updatable=true,nullable=true)
    private Magasins magasin;
   
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "numEquipe",referencedColumnName="numEquipe",insertable=true,updatable=true,nullable=true)
    private Equipe equipe;
    

    private  String numScelle;

    public Operations(Long idOpt, String couleur, String dateOperation, Magasins magasins, Magasins magasin, Equipe equipe, String numScelle) {
        this.idOpt = idOpt;
        this.couleur = couleur;
        this.dateOperation = dateOperation;
        this.magasins = magasins;
        this.magasin = magasin;
        this.equipe = equipe;
        this.numScelle = numScelle;
    }

    
}
