package bf.ouaga.stage.sonabel;
import bf.ouaga.stage.sonabel.dao.gestionUser.UserRepository;
import bf.ouaga.stage.sonabel.entities.gestionUser.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    
    @Autowired
 UserRepository userRepository;
    	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		User user =new User();
//		user.setMatricule("34433K");
//		String md=encoder.encode("humility");
//		user.setUsername("admin");
//		user.setPassword(md);
//		user= userRepository.save(user);
	}
	
}
