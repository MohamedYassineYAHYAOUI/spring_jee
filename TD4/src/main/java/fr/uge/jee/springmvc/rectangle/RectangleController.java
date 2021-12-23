package fr.uge.jee.springmvc.rectangle;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Controller
public class RectangleController{

    class Rectangle {

        @Min(0)
        private int width;

        @Min(0)
        private int height;

        public int area(){return width*height;}

        public int getHeight() {
            return height;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }
    }

    @GetMapping("/rectangle")
    public String getRectangle(Rectangle rectangle) {
        return "rectangle";
    }

    @PostMapping("/rectangle")
    public String postRectangle(@Valid @ModelAttribute("rectangle") Rectangle rectangle,
                                BindingResult bindingResult,
                                Model model) { // pourquoi l'ordre est important ??

        System.out.println("has error "+bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            model.addAttribute("rectangle", rectangle);
            return "rectangle.html";
        }
        model.addAttribute("area", rectangle.area());
        return "rectangle-result.html";
    }

}
