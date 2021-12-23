package fr.uge.jee.servlet.rectanglesession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Controller
@SessionAttributes("history")
public class RectanglrController {

    @ModelAttribute("history")
    public History history(){
        return new History();
    }

    @GetMapping("/rectangle")
    public String getRectangle(Model model) {
        model.addAttribute("rectangle", new Rectangle());
        //model.addAttribute("rectangleList", rectanglesList);
        return "Rectangle-memory/rectangle";
    }

    @PostMapping("/rectangle")
    public String postRectangle(@Valid @ModelAttribute("rectangle") Rectangle rectangle,
                                BindingResult bindingResult,
                                Model model,
                                @ModelAttribute("history") History history) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("rectangle", rectangle);
            return "Rectangle-memory/rectangle.html";
        }
        history.addComputation(rectangle.toString());
        model.addAttribute("area", rectangle.area());
        return "Rectangle-memory/rectangle-result.html";
    }
}
