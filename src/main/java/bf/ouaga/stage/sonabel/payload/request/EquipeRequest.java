/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.payload.request;

import bf.ouaga.stage.sonabel.entities.Magasins;
import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Aymard
 */
public class EquipeRequest {
      @NotBlank
    @Size(min = 3, max = 20)
    private Long numEquipe;

    @NotBlank
    @Size(max = 50)
    private String nomEquipe;

    
private Magasins magasins;

    public Magasins getMagasins() {
        return magasins;
    }

    public void setMagasins(Magasins magasins) {
        this.magasins = magasins;
    }

   

   
    public Long getNumEquipe() {
        return numEquipe;
    }

    public void setNumEquipe(Long numEquipe) {
        this.numEquipe = numEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

   
  
}
