/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.entities.gestionMag;

import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Equipe;
import javax.persistence.Entity;

/**
 *
 * @author Aymard
 */
@Entity
public class Reaffectation extends Operations{
public String type;
    public Reaffectation() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Reaffectation(Long idOpt, String couleur, String dateOperation, Magasins magasins, Magasins magasin, Equipe equipe, String numScelle) {
        super(idOpt, couleur, dateOperation, magasins, magasin, equipe, numScelle);
    }

   

  
    
}
