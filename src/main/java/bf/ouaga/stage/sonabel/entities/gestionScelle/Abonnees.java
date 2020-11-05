package bf.ouaga.stage.sonabel.entities.gestionScelle;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Abonnees implements Serializable {
    @Id
    private int numPolice;
    private String section;
    private int lot;
    private int parcelle;
    private int rang;

}
