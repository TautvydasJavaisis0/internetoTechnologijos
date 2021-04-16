package springframework.randomApp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springframework.randomApp.services.forms.GeneratedCatForm;
import springframework.randomApp.domain.User;
import springframework.randomApp.services.CatApiService;
import springframework.randomApp.services.UserService;

@Controller
@AllArgsConstructor
public class CatGenScreenController {

    private final CatApiService catApiService;
    private final UserService userService;

    @RequestMapping("/catGen")
    public String catGenScreen(){
        return "catGenScreen";
    }

    @GetMapping(value = "/catGen/save")
    public String saveCat(@RequestParam("response") String response, RedirectAttributes redirectAttributes) {

        GeneratedCatForm generatedCatForm = catApiService.getRandomCatFromApi();

        redirectAttributes.addFlashAttribute("message", "Visit limit has been reached");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        if(generatedCatForm == null)
            return "redirect:/catGen";

        redirectAttributes.addFlashAttribute("message", "");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        redirectAttributes.addFlashAttribute("id", generatedCatForm.getId());
        redirectAttributes.addFlashAttribute("url", generatedCatForm.getUrl());
        redirectAttributes.addFlashAttribute("saveCat", true);
        return "redirect:/catGen";
    }
    @PostMapping(value = "/catGen/save")
    public String saveCat(@RequestParam("id") String id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByUsername(auth.getName());

        catApiService.saveCatToApi(loggedInUser.getId(), id);
        userService.setUserCatList(loggedInUser);

        return "redirect:/catGen";
    }
}
