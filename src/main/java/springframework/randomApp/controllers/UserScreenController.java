package springframework.randomApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springframework.randomApp.domain.User;
import springframework.randomApp.services.CatApiService;
import springframework.randomApp.services.UserService;
import springframework.randomApp.services.forms.CatForm;

@Controller
@AllArgsConstructor
public class UserScreenController {

    private final CatApiService catApiService;
    private final UserService userService;

    @GetMapping("/user")
    public String getUser(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByUsername(auth.getName());

        model.addAttribute("cats", loggedInUser.getCats());

        return "userScreen";
    }
    @PostMapping(value = "/user{id}")
    public String deleteUserCat(Model model, @PathVariable("id") String id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByUsername(auth.getName());
        catApiService.deleteUsersCat(loggedInUser, id);

        model.addAttribute("cats", loggedInUser.getCats());

        return "redirect:/user";
    }

    @GetMapping(value = "/user/search")
    public String searchUserCat(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {

        CatForm cat = catApiService.findCat(id);

        redirectAttributes.addFlashAttribute("message", "Error");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if(cat == null)
            return "redirect:/user";

        redirectAttributes.addFlashAttribute("message", "");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        redirectAttributes.addFlashAttribute("id", cat.getId());
        redirectAttributes.addFlashAttribute("url", cat.getImage().getUrl());
        return "redirect:/user";
    }

}
