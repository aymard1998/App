/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.web;

/**
 *
 * @author Aymard
 */


import bf.ouaga.stage.sonabel.dao.MagasinsRepository;
import bf.ouaga.stage.sonabel.dao.gestionMag.OperationRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.UserRepository;
import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionMag.Reaffectation;
import bf.ouaga.stage.sonabel.entities.gestionMag.Reintegration;
import bf.ouaga.stage.sonabel.entities.gestionMag.Lots;
import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import bf.ouaga.stage.sonabel.metier.Impl.ApprovMetier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bf.ouaga.stage.sonabel.dao.gestionMag.LotsRepository;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/operation")
public class OperationController {
    @Autowired
	LotsRepository lotsRepository;
	@Autowired
	ApprovMetier approvMetier;
	@Autowired
	OperationRepository operationRepository;
	@Autowired
	MagasinsRepository magasinsRepository;
       	@Autowired
	UserRepository userRepository;
    

	@PutMapping("/reintegration/{id}/{uid}")
	public ResponseEntity<Lots> reintegration(@PathVariable long id,@PathVariable long uid,@RequestBody Lots scelles){
	        Optional<Lots> sc= lotsRepository.findById(id);          
		User user= userRepository.findById(uid).get();
                Magasins codeExpl=user.getMagasin();
                Long codeExplParent=codeExpl.getCodeExpl();
                Magasins parent =magasinsRepository.findByCodeExpl(codeExplParent).get(0);
                String sc1=sc.get().getNumLots().toString();
                Long appo=parent.getCodeExplParent();
                Magasins mag=magasinsRepository.findByCodeExpl(appo).get(0);
                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date= simpleDateFormat.format(new Date());

		if (sc.isPresent()) {
			Lots scelles1 = sc.get();
			scelles1.setNumLots(scelles.getNumLots());
			scelles1.setCouleur(scelles.getCouleur());
			scelles1.setMagasins(mag);
                        Reintegration apro =new Reintegration();
                        apro.setMagasins(codeExpl);
                        apro.setMagasin(parent);
                        apro.setCouleur(scelles.getCouleur());
                        apro.setDateOperation(date);
                        apro.setType("reintegration" );
                        apro.setNumScelle(sc1);
                        operationRepository.save(apro);
			return new ResponseEntity<>(lotsRepository.save(scelles1), HttpStatus.OK);
                       
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
      
	@PutMapping("/reaffectation/{id}/{mag}")
	public ResponseEntity<Lots> reaffectation(@PathVariable long id,@PathVariable String mag,@RequestBody Lots scelles){
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date= simpleDateFormat.format(new Date());
		Optional<Lots> sc= lotsRepository.findById(id);          
                String sc1=sc.get().getNumLots().toString();
                Long mg=approvMetier.getCeodeMage(mag);
		Magasins magasins = magasinsRepository.findById(mg).get();
                
                
                Long appo=magasins.getCodeExplParent();
                Magasins parent=magasinsRepository.findByCodeExpl(appo).get(0);

		if (sc.isPresent()) {
			Lots scelles1 = sc.get();
			scelles1.setNumLots(scelles.getNumLots());
			scelles1.setCouleur(scelles.getCouleur());
			scelles1.setMagasins(magasins);
                        Reaffectation apro =new Reaffectation();
                        apro.setMagasins(magasins);
                        apro.setMagasin(parent);
                        apro.setCouleur(scelles.getCouleur());
                        apro.setDateOperation(date);
                        apro.setType("reaffectation" );
                        apro.setNumScelle(sc1);
                        operationRepository.save(apro);
			return new ResponseEntity<>(lotsRepository.save(scelles1), HttpStatus.OK);
                       
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
/**
 *
 * @author Aymard
 */