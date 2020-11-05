package bf.ouaga.stage.sonabel.entities.gestionMag;

import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Equipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity @Data @NoArgsConstructor  @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Lots implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private Long numLots;
    private String couleur;
    private int quantite;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "codeExpl" ,referencedColumnName="codeExpl",insertable=true,updatable=true,nullable=true)
    private Magasins magasins;
    

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "numEquipe",referencedColumnName="numEquipe",insertable=true,updatable=true,nullable=true)
    private Equipe equipe   ;
    
    @JsonIgnore
    @OneToMany(mappedBy = "lots",targetEntity=Scelles.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Scelles> scelles;

    public Lots(Long numLots, String couleur, Magasins magasins,Equipe equipe) {
        this.numLots = numLots;
        this.couleur = couleur;
        this.magasins = magasins;
        this.equipe = equipe;
     
    }

    public Lots(Long numLots, String couleur, int quantite) {
        this.numLots = numLots;
        this.couleur = couleur;
        this.quantite = quantite;
    }
    public void generate(int qte,String color,int num ){
         for(int i=0;i<=qte;i++){
                       Scelles sc = new Scelles(); 
                       sc.setCouleur(color);
                    int number=num*qte;
                    Long numlot = new Long(number);
                    Long code =new Long(i);
                    Long numsc= Long.sum(numlot, code);
//                       sc.setMagasins(magasinsRepository.findByCodeExpl(100L).get(0));
//                       sc.setNumScelle(numsc);
//                       scellesRespository.save(sc);
                    }
    }

}
