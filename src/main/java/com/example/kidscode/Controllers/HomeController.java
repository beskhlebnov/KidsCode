package com.example.kidscode.Controllers;

import com.example.kidscode.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {
    private final UsersRepository usersRepository;

    @Autowired
    public HomeController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails==null){
            model.addAttribute("auth",false);
        }
        else{
            model.addAttribute("auth",true);
            model.addAttribute("user",usersRepository.findByUsername(userDetails.getUsername()));
        }


        return "Home";
    }
}
