package bf.ouaga.stage.sonabel.metier;


import bf.ouaga.stage.sonabel.entities.gestionUser.User;

public interface IUser {
    User findByUsername(String username);

}
