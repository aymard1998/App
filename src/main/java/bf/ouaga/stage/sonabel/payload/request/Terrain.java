/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Aymard
 */

@AllArgsConstructor @Data
public class Terrain {
    
   private Long numScelle;
    String couleur;
    String dateDepose;
    private String section;
    private int lot;
    private String parcelle;
    private String tableau;
    private String matricule;
    private String type;
    private String equipe;

    
}
