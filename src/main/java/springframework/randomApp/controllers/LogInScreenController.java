package springframework.randomApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class LogInScreenController {

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping("/default")
    public String defaultAfterLogin() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isUser = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));

        if (isUser) {
            return "redirect:/catGen";
        }
        return "redirect:/h2-console";
    }

}
