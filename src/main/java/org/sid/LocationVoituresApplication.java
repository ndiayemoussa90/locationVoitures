package org.sid;


import org.sid.files.StorageProperties;
import org.sid.auth.AuthenticateService;
import org.sid.entities.User;
import org.sid.entities.Authenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({ StorageProperties.class })
@EnableAutoConfiguration
public class LocationVoituresApplication implements CommandLineRunner  {

	
    @Autowired
    private AuthenticateService authenticateService;

    public static void main(String[] args) {
        SpringApplication.run(LocationVoituresApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting moussa...");

        try {
            //
            if (authenticateService.count() == 0) {
                Authenticate admin = authenticateService.save(
                    "admin@test.com", "admin",
                    "ROLE_ADMIN", new User("Admin", "Admin")
                );
            }
        } catch (Exception e){
            throw new RuntimeException("Echec execution CommandLineRunner",e);
        }
        
        System.out.println("Ready moussa");
    }

}
