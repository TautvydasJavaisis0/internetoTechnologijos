package springframework.randomApp.bootstrap;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.randomApp.services.RoleService;
import springframework.randomApp.services.UserService;

@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {

    UserService userService;
    RoleService roleService;

    @Override
    public void run(String... args){
        roleService.createRole("USER");
        roleService.createRole("ADMIN");
        userService.addUser("user1", "123", "USER");
        userService.addUser("user2", "123", "USER");
        userService.addUser("admin", "123", "ADMIN");
    }
}
