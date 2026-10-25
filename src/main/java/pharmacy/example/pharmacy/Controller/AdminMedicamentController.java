package pharmacy.example.pharmacy.Controller;



import pharmacy.example.pharmacy.entity.Medicament;
import pharmacy.example.pharmacy.repository.MedicamentRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin/medicaments")
public class AdminMedicamentController {


    private final MedicamentRepository medicamentRepository;


    public AdminMedicamentController(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }



    // afficher liste
    @GetMapping
    public String listMedicaments(Model model){

        model.addAttribute("medicaments",
                medicamentRepository.findAll());

        return "admin/medicaments";
    }



    // page ajout
    @GetMapping("/add")
    public String addMedicament(Model model){

        model.addAttribute("medicament",
                new Medicament());

        return "admin/add-medicament";
    }



    // sauvegarder
    @PostMapping("/save")
    public String saveMedicament(
            @ModelAttribute Medicament medicament){

        medicamentRepository.save(medicament);

        return "redirect:/admin/medicaments";
    }



    // supprimer
    @GetMapping("/delete/{id}")
    public String deleteMedicament(
            @PathVariable Long id){

        medicamentRepository.deleteById(id);

        return "redirect:/admin/medicaments";
    }

}