/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.payload.request;

import bf.ouaga.stage.sonabel.entities.gestionScelle.Equipe;
import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Aymard
 */
public class AgentRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String matricule;

    @NotBlank
    @Size(max = 50)
    private String nom;

    
    @NotBlank
    @Size(min = 1, max = 40)
    private String prenom;

    private Collection<Equipe> equipe;

    public Collection<Equipe> getEquipe() {
        return equipe;
    }

    public void setEquipe(Collection<Equipe> equipe) {
        this.equipe = equipe;
    }
    
    
    
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
}
