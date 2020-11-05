/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.payload.request;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Aymard
 */
public class SaveRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String nomMag;

    @NotBlank
    @Size(max = 50)
    private Long codeExpl;

    
    @NotBlank
    @Size(min = 1, max = 40)
    private Long codeExplParent;

     private Set<Long> user_id;
     
    
    public String getNomMag() {
        return nomMag;
    }

    public Set<Long> getUser_id() {
        return user_id;
    }

    public void setUser_id(Set<Long> user_id) {
        this.user_id = user_id;
    }

    
    public void setNomMag(String nomMag) {
        this.nomMag = nomMag;
    }

    public Long getCodeExpl() {
        return codeExpl;
    }

    public void setCodeExpl(Long codeExpl) {
        this.codeExpl = codeExpl;
    }

    public Long getCodeExplParent() {
        return codeExplParent;
    }

    public void setCodeExplParent(Long codeExplParent) {
        this.codeExplParent = codeExplParent;
    }
    
}
