package bf.ouaga.stage.sonabel.web;

import bf.ouaga.stage.sonabel.dao.gestionScelle.AgentRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.DeposeRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.EquipeRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.PoseRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.TypeRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.TypoRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.VilleRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.RoleRepository;
import bf.ouaga.stage.sonabel.dao.gestionUser.UserRepository;
import bf.ouaga.stage.sonabel.entities.gestionMag.Lots;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Agent;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Depose;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Equipe;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Pose;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Type;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Typo;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Ville;
import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import bf.ouaga.stage.sonabel.payload.request.LoginRequest;
import bf.ouaga.stage.sonabel.payload.request.SignupRequest;
import bf.ouaga.stage.sonabel.payload.request.Terrain;
import bf.ouaga.stage.sonabel.payload.response.JwtResponse;
import bf.ouaga.stage.sonabel.payload.response.MessageResponse;
import bf.ouaga.stage.sonabel.security.jwt.JwtUtils;
import bf.ouaga.stage.sonabel.security.service.UserDetailsImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import bf.ouaga.stage.sonabel.dao.gestionMag.LotsRepository;
import bf.ouaga.stage.sonabel.dao.gestionMag.ScellesRepository;
import bf.ouaga.stage.sonabel.entities.gestionMag.Scelles;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
        
         @Autowired
	LotsRepository lotsRepository;
          @Autowired
	ScellesRepository scelleRepository;
                
                
	@Autowired
	EquipeRepository equipeRepository;
        
        
	@Autowired
	PoseRepository poseRepository;
        
        @Autowired
	DeposeRepository deposeRepository;
        
        @Autowired
	TypeRepository typeRepository;
         
        @Autowired
	TypoRepository typoRepository;
           
        @Autowired
	VilleRepository villeRepository;
               
        @Autowired
	AgentRepository agentRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getMatricule(),
				roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByMatricule(signUpRequest.getMatricule())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Matricule is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getMatricule(),
				encoder.encode(signUpRequest.getPassword()));
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
        
        
        
        
	@PostMapping("/insert")
	public ResponseEntity<?> pose(@Valid @RequestBody Terrain equipeRequest) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date= simpleDateFormat.format(new Date());

		Long aLong =Long.valueOf(equipeRequest.getNumScelle());
		Long equipo= Long.valueOf(equipeRequest.getEquipe());

		if (scelleRepository.existsByNumScelle(aLong)){
			Equipe equip=equipeRepository.findByNumEquipe(equipo);
			Pose mag = new Pose();
			mag.setMatricule(equipeRequest.getMatricule());
			mag.setNumScelle(aLong);
			mag.setLot(equipeRequest.getLot());
			mag.setSection(equipeRequest.getSection());
			mag.setTableau(equipeRequest.getTableau());
			mag.setParcelle(equipeRequest.getParcelle());
			mag.setType(equipeRequest.getType());
			mag.setCouleur(equipeRequest.getCouleur());
			mag.setDateDepose(date);
			mag.setEquipe(equip);
			poseRepository.save(mag);
			Scelles sce =scelleRepository.findAllByNumScelle(aLong);
			scelleRepository.delete(sce);
			System.out.println(equipeRequest.getType());
			return ResponseEntity.ok(new MessageResponse("successfully!"));
		}else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));

		}



	}



	@PostMapping("/depose")
	public ResponseEntity<?> depose(@Valid @RequestBody Terrain equipeRequest) {

		Long aL =Long.valueOf(equipeRequest.getNumScelle());
		Long equ= Long.valueOf(equipeRequest.getEquipe());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date= simpleDateFormat.format(new Date());

			Equipe equip=equipeRepository.findByNumEquipe(equ);
		 	Pose pose =poseRepository.findAllByNumScelle(aL);

			Depose mag = new Depose();
			mag.setMatricule(equipeRequest.getMatricule());
			mag.setNumScel(equipeRequest.getNumScelle());
			mag.setSection(pose.getSection());
			mag.setTableau(pose.getTableau());
			mag.setLot(pose.getLot());
			mag.setParcelle(pose.getParcelle());
			mag.setType(equipeRequest.getType());
			mag.setCouleur(pose.getCouleur());
			mag.setDateDepose(date);
			mag.setEquipe(equip);
			deposeRepository.save(mag);
			poseRepository.delete(pose);
			return ResponseEntity.ok(new MessageResponse("successfully!"));
	}


	@GetMapping("/data")
	public ResponseEntity<List<Type>> typeGet() {
		List<Type> types= new ArrayList<>();
		try {
			typeRepository.findAll().forEach(types::add);
			if (types.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(types, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/data1")
	public ResponseEntity<List<Typo>> typoGet() {
		List<Typo> type= new ArrayList<>();
		try {
			typoRepository.findAll().forEach(type::add);
			if (type.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(type, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/ville")
	public ResponseEntity<List<Ville>> villeGet() {
		List<Ville> villes= new ArrayList<>();
		try {
			villeRepository.findAll().forEach(villes::add);
			if (villes.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(villes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping("/verifier")
	public ResponseEntity<?> IdUserPost(@RequestBody Agent agent) {
		if (agentRepository.existsByMatricule(agent.getMatricule())) {
			return ResponseEntity.ok(new MessageResponse("Non"));
		}

		return ResponseEntity.badRequest()
				.body(new MessageResponse("Ok "));

	}
}
