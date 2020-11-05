package bf.ouaga.stage.sonabel.entities.gestionUser;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue
    public int idRole;

    @Enumerated(EnumType.STRING)
    private ERole name;
}
