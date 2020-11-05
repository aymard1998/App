package bf.ouaga.stage.sonabel.entities.gestionScelle;

import bf.ouaga.stage.sonabel.entities.Magasins;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent implements Serializable {
    @Id @GeneratedValue
    private Long idAgent;
    private String matricule;
    private  String nom;
    private  String prenom;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "agent_equipe",
            joinColumns = @JoinColumn(name = "agent_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_numEquipe"))
    private Collection<Equipe> equipe;
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "magCodeExpl",referencedColumnName="codeExpl",insertable=true,updatable=true,nullable=true)
    private Magasins magasins;
    
    public Agent(String matricule, String nom, String prenom) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    
}


