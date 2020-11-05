package bf.ouaga.stage.sonabel.entities.gestionUser;

import bf.ouaga.stage.sonabel.entities.Magasins;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity @Data @NoArgsConstructor  @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "matricule")
        })
public class User implements Serializable {
    @Id @GeneratedValue
    private Long id;
    

    private String matricule;

    private String username;

    private String password;
    
    
    private String statut;
    

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Role> roles = new HashSet<>();
    
    
 //   @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "magCodeExpl",referencedColumnName="codeExpl",insertable=true,updatable=true,nullable=true)
    private Magasins magasin;
    
    
    public User(String username, String  matricule, String password, String statut,Magasins magasin) {
       this.username = username;
        this.matricule = matricule;
        this.password = password;
        this.statut = statut;
       this.magasin = magasin;
    }
     public User(String username, String  matricule, String password) {
       this.username = username;
        this.matricule = matricule;
        this.password = password;
    }

 
   
}
