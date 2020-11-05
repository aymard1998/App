/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.web;

import bf.ouaga.stage.sonabel.dao.MagasinsRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.RoleRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.UserRepository;
import bf.ouaga.stage.sonabel.entities.Magasins;
import bf.ouaga.stage.sonabel.entities.gestionUser.ERole;
import bf.ouaga.stage.sonabel.entities.gestionUser.Role;
import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import bf.ouaga.stage.sonabel.metier.Impl.ApprovMetier;
import bf.ouaga.stage.sonabel.payload.request.SaveRequest;
import bf.ouaga.stage.sonabel.payload.request.SignupRequest;
import bf.ouaga.stage.sonabel.payload.response.MessageResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *@param
 * @return
 * @author Aymard
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mag")
public class MagController {
     @Autowired
    MagasinsRepository magasinsRepository;
        @Autowired
   UserRepository userRepository;
      @Autowired
    RoleRepository roleRepository;
          @Autowired
	ApprovMetier approvMetier;
     
    @GetMapping("/maglist")
	public ResponseEntity<List<Magasins>> magsGet() {
		List<Magasins> magasins= new ArrayList<>();
		try {
				magasinsRepository.findAll().forEach(magasins::add);
			if (magasins.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(magasins, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
        @PostMapping("/magsave")
        public ResponseEntity registerMag(@Valid @RequestBody SaveRequest saveRequest) {         
               if (magasinsRepository.existsByCodeExpl(saveRequest.getCodeExpl())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: codeExpl is already taken!"));
		}

		if (magasinsRepository.existsByNomMag(saveRequest.getNomMag())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: the name is already in use!"));
		}
                Magasins mag = new Magasins(saveRequest.getCodeExpl(),
				saveRequest.getNomMag(),
				saveRequest.getCodeExplParent());
		magasinsRepository.save(mag);
                               
         return ResponseEntity.ok(new MessageResponse("Magasin saved successfully!"));        
}
 @PutMapping("/updateMag")
    public ResponseEntity<Magasins> updateMag(@RequestBody SaveRequest saveRequest ) {
     
        return new ResponseEntity<Magasins>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/deleteMag/{codeExpl}")
    public ResponseEntity<Magasins> deleteCustomer(@PathVariable Long codeExpl) {
       Optional<Magasins> sc= magasinsRepository.findById(codeExpl);
			if (sc.isPresent()){
                                magasinsRepository.deleteById(codeExpl);
				return new ResponseEntity<>(sc.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}       

    }
    @PutMapping("/user/{id}/{mag}")
	public ResponseEntity<User> maj(@PathVariable Long id,@PathVariable String mag,@RequestBody  SignupRequest saveRequest){
		User sc= userRepository.findById(id).get();
                Long mg=approvMetier.getCeodeMage(mag);
		Magasins magasins = magasinsRepository.findById(mg).get();
                Magasins strUid = saveRequest.getMagasins();
		Collection<Magasins> magasin = new HashSet<>();
                magasin.add(magasins);
		String strRoles = saveRequest.getRole();
		Set<Role> roles = new HashSet<>();
               Role userRole = roleRepository.findByName(ERole.valueOf(strRoles)).get();
               roles.add(userRole);
//		if (strRoles == null) {
//                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                    roles.add(userRole);
//                } else {
//                    strRoles.forEach(role -> {
//                        switch (role) {
//                            case "admin":
//                                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                                roles.add(adminRole);
//                                
//                                break;
//                            case "mod":
//                                Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                                roles.add(modRole);
//                                
//                                break;
//                            case "msd":
//                                Role msdRole = roleRepository.findByName(ERole.ROLE_MSD)
//                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                                roles.add(msdRole);
//                                
//                                break;
//                                
//                            case "agent":
//                                Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
//                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                                roles.add(agentRole);
//                                
//                                break;
//                                
//                                
//                            case "mgd":
//                                Role mgdRole = roleRepository.findByName(ERole.ROLE_MGD)
//                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                                roles.add(mgdRole);
//                                
//                                break;
//                           
//                        }
//                    });
//                }
                
		try {
			
                    User use = sc;
                       use.setRoles(roles);
                       use.setMagasin(magasins);
                       magasinsRepository.save(magasins);
                       userRepository.save(use);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
