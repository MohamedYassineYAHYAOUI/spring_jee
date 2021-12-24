package fr.uge.jee.bugged.controller;

import fr.uge.jee.bugged.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DirectoryController {

        @Autowired
        UserService userService;

        @GetMapping("/directory")
        public String rectangleForm(){
            return "form";
        }

        @PostMapping("/directoryAction")
        public String rectangleFormProcess(@ModelAttribute("search") Search search, Model model){
            var answers = userService.searchByLastName(search.getLastName());
            model.addAttribute("answers", answers );
            return "results";
        }

    private static class Search {
        private String lastName;

        public Search() {
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }


}



