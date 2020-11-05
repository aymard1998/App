/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.web;

import bf.ouaga.stage.sonabel.dao.MagasinsRepository;
import bf.ouaga.stage.sonabel.dao.gestionMag.OperationRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.AgentRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.EquipeRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.UserRepository;
import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionMag.Approvisionnement;
import bf.ouaga.stage.sonabel.entities.gestionMag.Lots;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Agent;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Equipe;
import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import bf.ouaga.stage.sonabel.metier.Impl.ApprovMetier;
import bf.ouaga.stage.sonabel.payload.request.AgentRequest;
import bf.ouaga.stage.sonabel.payload.request.EquipeRequest;
import bf.ouaga.stage.sonabel.payload.request.SaveRequest;
import bf.ouaga.stage.sonabel.payload.response.MessageResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bf.ouaga.stage.sonabel.dao.gestionMag.LotsRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.RoleRepository;
import bf.ouaga.stage.sonabel.entities.gestionUser.ERole;
import bf.ouaga.stage.sonabel.entities.gestionUser.Role;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Aymard
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/equipe")
public class EquipeController {
     @Autowired
    EquipeRepository equipeRepository;
      @Autowired
    AgentRepository agentRepository;
      
            @Autowired
   RoleRepository roleRepository;
            
      @Autowired
MagasinsRepository magasinsRepository;
       @Autowired
LotsRepository lotsRepository;
        @Autowired
OperationRepository operationRepository;
          @Autowired
UserRepository userRepository;
      @Autowired
ApprovMetier approvMetier;
      	@Autowired
	PasswordEncoder encoder;
     
       @PutMapping("/equipesave/{id}")
        public ResponseEntity registerequipe(@Valid @PathVariable Long id, @RequestBody Equipe equipe) {         
               if (equipeRepository.existsByNumEquipe(equipe.getNumEquipe())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: NumEquipe is already taken!"));
		}

		if (equipeRepository.existsByNomEquipe(equipe.getNomEquipe())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: the name is already in use!"));
		}
	User user= userRepository.findById(id).get();
        Magasins codeExpl=user.getMagasin();
                Equipe mag = new Equipe();
                mag.setNumEquipe(equipe.getNumEquipe());
                mag.setNomEquipe(equipe.getNomEquipe());
                mag.setMagasins(codeExpl);
		equipeRepository.save(mag);
                               
         return ResponseEntity.ok(new MessageResponse("Equipe saved successfully!"));        
}
        
          
    @GetMapping("/equipelist/{id}")
	public ResponseEntity<List<Equipe>> equipeGet(@PathVariable Long id,Pageable pageable) {
	User user= userRepository.findById(id).get();
        Long userid=user.getMagasin().getCodeExpl();
            List<Equipe> magasins= new ArrayList<>();
		try {
				equipeRepository.findByMagasinsCodeExpl(userid).forEach(magasins::add);
			if (magasins.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(magasins, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
        
          @PutMapping("/agentsave/{id}")
        public ResponseEntity registeragent(@Valid  @PathVariable Long id, @RequestBody Agent agent) {
            	User user= userRepository.findById(id).get();
                Magasins codeExpl=user.getMagasin();
               if (agentRepository.existsByMatricule(agent.getMatricule())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Matricule is already taken!"));
		}

		
                Agent mag = new Agent();
                mag.setMatricule(agent.getMatricule());
                mag.setNom(agent.getNom());
                mag.setPrenom(agent.getPrenom());
                mag.setMagasins(codeExpl);
                
		agentRepository.save(mag);
                
                User use = new User();
                use.setMatricule(agent.getMatricule());
                use.setUsername(agent.getNom());
                use.setPassword(encoder.encode(agent.getNom() + agent.getPrenom()));
                use.setMagasin(codeExpl);
                Set<Role> roles = new HashSet<>();
                Set<String> strRoles = null;
               if (strRoles == null) {
			Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(agentRole);
		}
                use.setRoles(roles);
                
		userRepository.save(use);
                               
         return ResponseEntity.ok(new MessageResponse("Agent saved successfully!"));        
}
        
          
    @GetMapping("/agentlist/{id}")
	public ResponseEntity<List<Agent>> agentGet(@Valid @PathVariable Long id) {
                User user= userRepository.findById(id).get();
                Long userid=user.getMagasin().getCodeExpl();
		List<Agent> magasins= new ArrayList<>();
		try {
		        agentRepository.findByMagasinsCodeExpl(userid).forEach(magasins::add);
			if (magasins.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(magasins, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
            @GetMapping("/formequipe/{numEquipe}")
	public ResponseEntity<Equipe> recupererequipe(@PathVariable long numEquipe) {
		Optional<Equipe> sc= equipeRepository.findById(numEquipe);
			if (sc.isPresent()){

				return new ResponseEntity<>(sc.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

	}
 @PutMapping("/equipe/{numEquipe}/{id}")
	public ResponseEntity<Equipe> equipe(@PathVariable Long numEquipe,@PathVariable Long id,@RequestBody EquipeRequest saveRequest){
		Equipe sc= equipeRepository.findById(numEquipe).get();
              User user= userRepository.findById(id).get();
               Magasins userid=user.getMagasin();
                
		try {
			
                    Equipe use = sc;
                    use.setNomEquipe(saveRequest.getNomEquipe());
                    use.setNumEquipe(saveRequest.getNumEquipe());
                       use.setMagasins(userid);
                       magasinsRepository.save(userid);
			return new ResponseEntity<>( equipeRepository.save(use),HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
   @PutMapping("/agent/{id}/{mag}")
	public ResponseEntity<Agent> agent(@PathVariable Long id,@PathVariable String mag,@RequestBody  AgentRequest saveRequest){
		Agent sc= agentRepository.findById(id).get();
                Long mg=approvMetier.getNumEquipe(mag);
		Equipe magasins = equipeRepository.findById(mg).get();
		Collection<Equipe> magasin = new HashSet<>();
                magasin.add(magasins);
                
		try {
			
                    Agent use = sc;
                       use.setEquipe(magasin);
                       equipeRepository.save(magasins);
			return new ResponseEntity<>( agentRepository.save(use),HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
        @GetMapping("/affecteragent/{id}")
	public ResponseEntity<Agent> recup(@PathVariable Long id) {
		Optional<Agent> sc= agentRepository.findById(id);
			if (sc.isPresent()){

				return new ResponseEntity<>(sc.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

	}
        
        	@PutMapping("/getequipe/{id}/{mag}")
	public ResponseEntity<Lots> getequipe(@PathVariable long id, @PathVariable String mag,@RequestBody Lots scelles){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date= simpleDateFormat.format(new Date());
		Optional<Lots> sc= lotsRepository.findById(id);          
                User user= userRepository.findById(id).get();
                Magasins userid=user.getMagasin();
                Long mo=approvMetier.getNumEquipe(mag);
                Equipe equipes = equipeRepository.findById(mo).get();
                String sc1=sc.get().getNumLots().toString();

		if (sc.isPresent()) {
			Lots scelles1 = sc.get();
			scelles1.setNumLots(scelles.getNumLots());
			scelles1.setCouleur(scelles.getCouleur());
			scelles1.setEquipe(equipes);
                        Approvisionnement apro =new Approvisionnement();
                        apro.setMagasins(userid);
                        apro.setEquipe(equipes);
                        apro.setCouleur(scelles.getCouleur());
                        apro.setType("affectation" );
                        apro.setDateOperation(date);
                        apro.setNumScelle(sc1);
                        operationRepository.save(apro);
			return new ResponseEntity<>(lotsRepository.save(scelles1), HttpStatus.OK);
                       
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
