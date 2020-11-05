package bf.ouaga.stage.sonabel.dao.gestionUser;



import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByMatricule(String matricule);

    @Query("select password from User where username like :r")
    String chercher(@Param("r") String mdp);
    
    
}

