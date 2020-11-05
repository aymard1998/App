package bf.ouaga.stage.sonabel.web;

import bf.ouaga.stage.sonabel.dao.MagasinsRepository;
import bf.ouaga.stage.sonabel.dao.gestionMag.DemandeRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.UserRepository;
import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionMag.Demande;
import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import bf.ouaga.stage.sonabel.metier.Impl.ApprovMetier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Aymard
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/demande")
public class NotificationsController {
     @Autowired
    MagasinsRepository magasinsRepository;
        @Autowired
   UserRepository userRepository;
          @Autowired
	DemandeRepository demandeRepository;
           @Autowired
	ApprovMetier approvMetier;
    
        @PutMapping("/dmsave/{id}")
	public ResponseEntity<Demande> maj(@Valid @PathVariable Long id, @RequestBody Demande scelles){  
                User user= userRepository.findById(id).get();
                Magasins codeExpl=user.getMagasin();
                Long codeExplParent=codeExpl.getCodeExpl();
                Magasins parent =magasinsRepository.findByCodeExpl(codeExplParent).get(0);
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date= simpleDateFormat.format(new Date());            
		try {
			Demande scelles1 = new Demande();
			scelles1.setMagasin(parent);
                        scelles1.setMagasins(codeExpl);
                        scelles1.setCouleur(scelles.getCouleur());
                         scelles1.setQte(scelles.getQte());
                         scelles1.setCouleur1(scelles.getCouleur1());
                         scelles1.setQte1(scelles.getQte1());
                         scelles1.setCouleur2(scelles.getCouleur2());
                         scelles1.setQte2(scelles.getQte2());
                         scelles1.setCouleur3(scelles.getCouleur3());
                         scelles1.setQte3(scelles.getQte3());
                         scelles1.setCouleur4(scelles.getCouleur4());
                         scelles1.setQte4(scelles.getQte4());
                         scelles1.setCouleur5(scelles.getCouleur5());
                         scelles1.setQte5(scelles.getQte5());
                         scelles1.setCouleur6(scelles.getCouleur6());
                         scelles1.setQte6(scelles.getQte6());
			scelles1.setDateDM(date);
                        
                       
			return new ResponseEntity<>(demandeRepository.save(scelles1), HttpStatus.OK);
                       
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
       @GetMapping("/demandelist/{id}")
	public ResponseEntity<List<Demande>> demandeGet(@Valid @PathVariable Long id) {
               User user= userRepository.findById(id).get();
                Long userid=user.getMagasin().getCodeExpl();
		List<Demande> opet= new ArrayList<>();
		try {
		        demandeRepository.findAllByMagasinsCodeExpl(userid).forEach(opet::add);
			
			if (opet.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(opet, HttpStatus.OK);
		} 
                catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
        @GetMapping("/load/{id}")
	public ResponseEntity<List<Magasins>> demanGet(@Valid @PathVariable Long id) {
                User user= userRepository.findById(id).get();
                Magasins codeExpl=user.getMagasin();
                Long codeExplParent=codeExpl.getCodeExplParent();
		List<Magasins> opet= new ArrayList<>();
		try {
		        magasinsRepository.findByCodeExpl(codeExplParent).forEach(opet::add);
			
			if (opet.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(opet, HttpStatus.OK);
		} 
                catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
         @GetMapping("/fils/{id}")
	public ResponseEntity<List<Magasins>> filsGet(@Valid @PathVariable Long id) {
                User user= userRepository.findById(id).get();
                Magasins codeExpl=user.getMagasin();
                Long codeExplParent=codeExpl.getCodeExpl();
		List<Magasins> opet= new ArrayList<>();
		try {
		        magasinsRepository.findByCodeExplParent(codeExplParent).forEach(opet::add);
			
			if (opet.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(opet, HttpStatus.OK);
		} 
                catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}