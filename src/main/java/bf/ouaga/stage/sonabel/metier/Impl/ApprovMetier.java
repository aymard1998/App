package bf.ouaga.stage.sonabel.metier.Impl;


import bf.ouaga.stage.sonabel.dao.MagasinsRepository;
import bf.ouaga.stage.sonabel.dao.gestionMag.OperationRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.EquipeRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.RoleRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.UserRepository;
import bf.ouaga.stage.sonabel.metier.IApprov;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bf.ouaga.stage.sonabel.dao.gestionMag.LotsRepository;


@Service
@Transactional
public class ApprovMetier implements IApprov {

    @Autowired
    OperationRepository operationRepository;
    @Autowired
    private MagasinsRepository magasinsRepository;
     @Autowired
    private RoleRepository roleRepository;
   @Autowired
    private EquipeRepository equipeRepository;
     @Autowired
    UserRepository userRepository;

    @Override
    public Long getCeodeMage(String nom) {

        return magasinsRepository.findBy(nom);
    }

    @Override
    public Long findByCodeExplParent(String codeExplParent) {
       return  magasinsRepository.findByparent(codeExplParent);
    }

    @Override
    public Long getNumEquipe(String nom) {
       return equipeRepository.findBy(nom);
    }

  
}
