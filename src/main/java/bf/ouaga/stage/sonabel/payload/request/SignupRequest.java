package bf.ouaga.stage.sonabel.payload.request;


import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionUser.Role;
import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {
    
     @NotBlank
    @Size(min = 3, max = 20)
    private long id;
    
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String matricule;

   private String role;
    
     private Magasins magasins;

  
   

     
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @NotBlank
    @Size(min = 6, max = 40)
    private String statut;

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Magasins getMagasins() {
        return magasins;
    }

    public void setMagasins(Magasins magasins) {
        this.magasins = magasins;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    
   
}
