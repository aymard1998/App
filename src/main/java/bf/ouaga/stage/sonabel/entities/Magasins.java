package bf.ouaga.stage.sonabel.entities;

import bf.ouaga.stage.sonabel.entities.gestionMag.Demande;
import bf.ouaga.stage.sonabel.entities.gestionMag.Lots;
import bf.ouaga.stage.sonabel.entities.gestionMag.Operations;
import bf.ouaga.stage.sonabel.entities.gestionMag.Scelles;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Agent;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Equipe;
import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity @Data @NoArgsConstructor
@AllArgsConstructor

public class Magasins implements Serializable {
    @Id
    private Long codeExpl;

    private String nomMag;
    
    private Long codeExplParent;

    @JsonIgnore
    @OneToMany(mappedBy = "magasins",targetEntity=Lots.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Lots> lots;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "magasins",targetEntity=Lots.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Scelles> scelles;
   
    @JsonIgnore
    @OneToMany(mappedBy = "magasins",targetEntity=Agent.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Agent> agent;
    
    @JsonIgnore
    @OneToMany(mappedBy = "magasins",targetEntity=Demande.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Demande> demande;
    
    @JsonIgnore
    @OneToMany(mappedBy = "magasin",targetEntity=Demande.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Demande> demandes;
    
    @JsonIgnore
    @OneToMany(mappedBy = "magasins",targetEntity=Equipe.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Equipe> equipes;
    
     @JsonIgnore
    @OneToMany(mappedBy = "magasins",targetEntity=Operations.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Operations> operations;
     
    @JsonIgnore
    @OneToMany(mappedBy = "magasin",targetEntity=Operations.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Operations> operation;
    
    @JsonIgnore
    @OneToMany(mappedBy="magasin",targetEntity=User.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<User >user;
    


    public Magasins(Long codeExpl, String nomMag, Long codeExplParent) {
        this.codeExpl = codeExpl;
        this.nomMag = nomMag;
        this.codeExplParent = codeExplParent;
    }
}
