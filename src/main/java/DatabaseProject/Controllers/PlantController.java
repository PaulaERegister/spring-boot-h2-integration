package DatabaseProject.Controllers;

import DatabaseProject.Models.PlantModel;
import DatabaseProject.Repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/plants")
public class PlantController {
    @Autowired
    private PlantRepository plantRepository;

    @GetMapping
    public String plantPage(Model model){
        model.addAttribute("plants", plantRepository.findAll());
        return "plants";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute PlantModel plantModel, Model model) {
        plantRepository.save(plantModel);
        model.addAttribute("plants", plantRepository.findAll());
        return "plants";
    }
}
