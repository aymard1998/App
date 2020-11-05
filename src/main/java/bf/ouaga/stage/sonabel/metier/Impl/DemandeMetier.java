package bf.ouaga.stage.sonabel.metier.Impl;





import bf.ouaga.stage.sonabel.dao.MagasinsRepository;
import bf.ouaga.stage.sonabel.dao.gestionMag.DemandeRepository;
import bf.ouaga.stage.sonabel.entities.gestionMag.Demande;
import bf.ouaga.stage.sonabel.metier.IDemande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DemandeMetier implements IDemande {
    @Autowired
    DemandeRepository demandeRepository;
    @Autowired
    MagasinsRepository magasinsRepository;


    @Override
    public void send(Long codeExpl, Long codeExplParent, String color, int qte) {
        Demande dm=new Demande();
//        Magasins magas = magasinsRepository.findById(codeExpl).get();
//        Magasins mg=magasinsRepository.findById(codeExplParent).get();
//        Message mdg=new Message(mg, magas,new Date(),qte,color);
//        demandeRepository.save(mdg);
    }
}
