package pharmacy.example.pharmacy.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicamentController {

    @GetMapping("/medicaments")
    public String medicaments() {
        return "medicaments";
    }

}
