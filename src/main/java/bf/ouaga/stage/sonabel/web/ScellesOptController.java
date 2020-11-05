package bf.ouaga.stage.sonabel.web;

import bf.ouaga.stage.sonabel.dao.MagasinsRepository;
import bf.ouaga.stage.sonabel.dao.gestionMag.OperationRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.UserRepository;
import  bf.ouaga.stage.sonabel.dao.gestionMag.ScellesRepository;
import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionMag.Approvisionnement;
import bf.ouaga.stage.sonabel.entities.gestionMag.Operations;
import bf.ouaga.stage.sonabel.entities.gestionMag.Lots;
import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import bf.ouaga.stage.sonabel.metier.Impl.ApprovMetier;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import bf.ouaga.stage.sonabel.dao.gestionMag.LotsRepository;
import bf.ouaga.stage.sonabel.entities.gestionMag.Scelles;
import bf.ouaga.stage.sonabel.payload.response.MessageResponse;
import java.util.stream.LongStream;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/scelles")
public class ScellesOptController {
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
        @Autowired
	PasswordEncoder encoder;
        @Autowired
	ScellesRepository scellesRespository;

          
	@GetMapping("/stock")
	public ResponseEntity<List<Lots>> scellesGet(@RequestParam(required = false) String color,Pageable pageable) {
		List<Lots> scelles= new ArrayList<>();
		try {
				lotsRepository.findAllByCouleurContaining(color,pageable).forEach(scelles::add);
			if (scelles.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(scelles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
      
       

	@GetMapping("/affecter/{id}")
	public ResponseEntity<Lots> recup(@PathVariable long id) {
		Optional<Lots> sc= lotsRepository.findById(id);
			if (sc.isPresent()){

				return new ResponseEntity<>(sc.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

	}
        @GetMapping("/formuser/{id}")
	public ResponseEntity<User> recuperer(@PathVariable long id) {
		Optional<User> sc= userRepository.findById(id);
			if (sc.isPresent()){

				return new ResponseEntity<>(sc.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

	}
         @GetMapping("/formmag/{codeExpl}")
	public ResponseEntity<Magasins> recuperation(@PathVariable long codeExpl) {
		Optional<Magasins> sc= magasinsRepository.findById(codeExpl);
			if (sc.isPresent()){

				return new ResponseEntity<>(sc.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

	}

	@GetMapping("/parMag/{id}")
	public ResponseEntity<List<Lots>> parMag(@PathVariable Long id,Pageable pageable ){     
                User user= userRepository.findById(id).get();
                Long userid=user.getMagasin().getCodeExpl();
		List<Lots> scelles = new ArrayList<Lots>();
		try {
			lotsRepository.findByMagasinsCodeExpl(userid).forEach(scelles::add);
		if (scelles.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(scelles, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	@PutMapping("/maj/{id}/{mag}")
	public ResponseEntity<Lots> maj(@PathVariable long id, @PathVariable String mag,@RequestBody Lots scelles){
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
                        Approvisionnement apro =new Approvisionnement();
                        apro.setMagasins(parent);
                        apro.setMagasin(magasins);
                        apro.setCouleur(scelles.getCouleur());
                        apro.setType("approvisionnement" );
                        apro.setDateOperation(date);
                        apro.setNumScelle(sc1);
                        operationRepository.save(apro);
			return new ResponseEntity<>(lotsRepository.save(scelles1), HttpStatus.OK);
                       
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
        	@PutMapping("/mag/{codeExpl}")
	public ResponseEntity<Magasins> maje(@PathVariable long codeExpl,@RequestBody Magasins scelles){
		Optional<Magasins> sc= magasinsRepository.findById(codeExpl);

		if (sc.isPresent()) {
			Magasins scelles1 = sc.get();
			scelles1.setCodeExpl(scelles.getCodeExpl());
			scelles1.setNomMag(scelles.getNomMag());
			scelles1.setCodeExplParent(scelles.getCodeExplParent());
                        magasinsRepository.save(scelles1);
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
        @PutMapping("/user/{id}")
	public ResponseEntity<User> usermod(@PathVariable long id,@RequestBody User scelles){
		Optional<User> sc= userRepository.findById(id);


		if (sc.isPresent()) {

                        User scelles1 = sc.get();
			scelles1.setUsername(scelles.getUsername());
			scelles1.setMatricule(scelles.getMatricule());
			scelles1.setPassword(encoder.encode(scelles.getPassword()));
                        scelles1.setStatut(scelles.getStatut());
                        userRepository.save(scelles1);
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
         @PutMapping("/disableuser/{id}")
	public ResponseEntity<User> disableuser(@PathVariable long id){
		Optional<User> sc= userRepository.findById(id);


		if (sc.isPresent()) {

                        User scelles1 = sc.get();
			scelles1.setStatut("inactif");
                        userRepository.save(scelles1);
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
         @PutMapping("/enableuser/{id}")
	public ResponseEntity<User> enableuser(@PathVariable long id){
		Optional<User> sc= userRepository.findById(id);


		if (sc.isPresent()) {

                        User scelles1 = sc.get();
			scelles1.setStatut("actif");
                        userRepository.save(scelles1);
			return new ResponseEntity<>( HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/operation/{id}")
	public ResponseEntity<List<Operations>> operationGet(@Valid @PathVariable Long id,Pageable pageable) {        
                User user= userRepository.findById(id).get();
                Long userid=user.getMagasin().getCodeExpl();
		List<Operations> scelles = new ArrayList<Operations>();
		
		try {
	               operationRepository.findByMagasinsCodeExpl(userid).forEach(scelles::add);
		if (scelles.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(scelles, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
        
           @PostMapping("/inserer")
	public ResponseEntity<?> insererLots(@Valid @RequestBody Lots lots){

		try{
                    Lots lot= new Lots();
                    lot.setNumLots(lots.getNumLots());
                    lot.setCouleur(lots.getCouleur());
                    lot.setQuantite(lots.getQuantite());
                    lot.setMagasins(magasinsRepository.findByCodeExpl(100L).get(0));
                    lotsRepository.save(lot);
                    int num=lots.getNumLots().intValue();
                    int qte = lots.getQuantite();
                    for(int i=0;i<=qte;i++){
                       Scelles sc = new Scelles(); 
                       sc.setCouleur(lots.getCouleur());
                    int number=num*qte;
                    Long numlot = new Long(number);
                    Long code =new Long(i);
                    Long numsc= Long.sum(numlot, code);
                    sc.setLots(lot);
                       sc.setMagasins(magasinsRepository.findByCodeExpl(100L).get(0));
                       sc.setNumScelle(numsc);
                       scellesRespository.save(sc);
                    }
                       
			return ResponseEntity.ok(new MessageResponse("successfully!"));
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}      
        
}
        
