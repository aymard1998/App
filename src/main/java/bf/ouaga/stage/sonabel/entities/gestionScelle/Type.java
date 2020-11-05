package bf.ouaga.stage.sonabel.entities.gestionScelle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Type implements Serializable {
    @Id @GeneratedValue
    private  Long id;
    private String Libelle;
}
