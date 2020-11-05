package bf.ouaga.stage.sonabel.metier.Impl;

import bf.ouaga.stage.sonabel.dao.gestionUser.UserRepository;
import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import bf.ouaga.stage.sonabel.metier.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserImpl implements IUser {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User findByUsername(String username) {
        User user = null;
        try {
        } catch (Exception e) {
            throw e;
        }
        return user;
    }
}
