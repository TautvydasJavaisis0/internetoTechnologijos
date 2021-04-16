package springframework.randomApp.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import springframework.randomApp.domain.User;
import springframework.randomApp.exceptions.CatNotFoundException;
import springframework.randomApp.repositories.CatRepository;
import springframework.randomApp.services.forms.CatForm;
import springframework.randomApp.services.forms.GeneratedCatForm;
import springframework.randomApp.services.forms.SaveSuccessForm;

import static springframework.randomApp.constants.CatApiConstants.*;

@Service
@AllArgsConstructor
public class CatApiService {

    @Autowired
    private RestTemplate restTemplate;
    private CatRepository catRepository;

    public String saveCatToApi(int userId, String catId){
        String requestJson = "{\"image_id\":\"" + catId + "\", " + "\"sub_id\":\"" + userId + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(requestJson, headers);
        SaveSuccessForm form = restTemplate.postForObject(SAVE_CAT_URL+"?api_key="+API_KEY, request, SaveSuccessForm.class);
        System.out.println(form.getId());
        return form.getId();
    }

    public GeneratedCatForm getRandomCatFromApi(){
        GeneratedCatForm[] generatedCatForm = restTemplate.getForObject(GET_RANDOM_CAT_URL, GeneratedCatForm[].class);
        return generatedCatForm[0];
    }

    public void deleteUsersCat(User user, String id){
        restTemplate.delete(SAVE_CAT_URL+ id +"?api_key="+API_KEY);
        catRepository.deleteById(id);
    }

    public CatForm findCat(String id){
        try {
            CatForm catForm = restTemplate.getForObject(SAVE_CAT_URL+ id +"?api_key="+API_KEY, CatForm.class);
            return catForm;
        }
        catch (CatNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cat Not Found", exc);
        }
    }
    public CatForm[] getAllCats(){
        CatForm[] form = restTemplate.getForObject(SAVE_CAT_URL + "?api_key=" + API_KEY, CatForm[].class);
        return  form;
    }
}
