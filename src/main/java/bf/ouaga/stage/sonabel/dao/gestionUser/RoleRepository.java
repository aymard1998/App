package bf.ouaga.stage.sonabel.dao.gestionUser;

import bf.ouaga.stage.sonabel.entities.gestionUser.ERole;
import bf.ouaga.stage.sonabel.entities.gestionUser.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
    
    List<Role>findAll();

    @Query("select idRole from Role where  name like :e")
    public Long findBy(@Param("e") String name);

}
