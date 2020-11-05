package bf.ouaga.stage.sonabel.entities.gestionScelle;

import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionMag.Operations;
import bf.ouaga.stage.sonabel.entities.gestionMag.Lots;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Equipe implements Serializable {
    @Id
    private Long numEquipe;
    private String nomEquipe;
    
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "magCodeExpl",referencedColumnName="codeExpl",insertable=true,updatable=true,nullable=true)
    private Magasins magasins;
    

    @JsonIgnore
    @OneToMany(mappedBy = "equipe",targetEntity=Lots.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Lots> scelles;
   

    @OneToMany(mappedBy = "equipe",targetEntity=Depose.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private Collection<Depose> deposes;
    

    @OneToMany(mappedBy = "equipe",targetEntity=Pose.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private Collection<Pose> poses;
    
     @JsonIgnore
    @OneToMany(mappedBy = "equipe",targetEntity=Operations.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Operations> operations;

    public Equipe(Long numEquipe, String nomEquipe) {
        this.numEquipe = numEquipe;
        this.nomEquipe = nomEquipe;
    }

   
    
    
}
