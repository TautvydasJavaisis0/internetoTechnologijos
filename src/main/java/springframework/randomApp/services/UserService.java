package springframework.randomApp.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.randomApp.domain.Cat;
import springframework.randomApp.domain.Role;
import springframework.randomApp.domain.User;
import springframework.randomApp.repositories.UserRepository;
import springframework.randomApp.services.forms.CatForm;

import java.util.Arrays;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private CatApiService catApiService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User addUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setActive(true);
        Role userRole = roleService.findByRoleName(role);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public void setUserCatList(User user) {
        CatForm[] forms = catApiService.getAllCats();

        user.getCats().clear();
        for (int i = 0; i < forms.length; i++) {
            if (forms[i].getSub_id().equals(user.idToString())) {
                user.getCats().add(new Cat(forms[i].getId(), forms[i].getImage().getUrl(), user));
            }
        }
        userRepository.save(user);
    }
}

