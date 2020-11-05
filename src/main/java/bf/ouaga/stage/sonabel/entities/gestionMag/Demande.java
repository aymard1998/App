package bf.ouaga.stage.sonabel.entities.gestionMag;

import bf.ouaga.stage.sonabel.entities.Magasins;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author capo21
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Demande implements Serializable{
    @Id @GeneratedValue
    private Long idDM;
    private String dateDM;
    private  int qte;
    private  String couleur;
    private  int qte1;
    private  String couleur1;
    private  int qte2;
    private  String couleur2;
    private  int qte3;
    private  String couleur3;
    private  int qte4;
    private  String couleur4;
    private  int qte5;
    private  String couleur5;
    private  int qte6;
    private  String couleur6;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "magCodeExpl",referencedColumnName="codeExpl",insertable=true,updatable=true,nullable=true)
    private Magasins magasins;
   
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "magCodeExplParent",referencedColumnName="codeExplParent",insertable=true,updatable=true,nullable=true)
    private Magasins magasin;

    public Demande(Long idDM, String dateDM, int qte, String couleur, int qte1, String couleur1, int qte2, String couleur2, int qte3, String couleur3, int qte4, String couleur4, int qte5, String couleur5, int qte6, String couleur6, Magasins magasins, Magasins magasin) {
        this.idDM = idDM;
        this.dateDM = dateDM;
        this.qte = qte;
        this.couleur = couleur;
        this.qte1 = qte1;
        this.couleur1 = couleur1;
        this.qte2 = qte2;
        this.couleur2 = couleur2;
        this.qte3 = qte3;
        this.couleur3 = couleur3;
        this.qte4 = qte4;
        this.couleur4 = couleur4;
        this.qte5 = qte5;
        this.couleur5 = couleur5;
        this.qte6 = qte6;
        this.couleur6 = couleur6;
        this.magasins = magasins;
        this.magasin = magasin;
    }
    
    

   
    
}
