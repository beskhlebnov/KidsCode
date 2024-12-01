package com.example.kidscode.Controllers;


import com.example.kidscode.Models.Course;
import com.example.kidscode.Models.PrefKids;
import com.example.kidscode.Models.Users;
import com.example.kidscode.Repository.CourseRepository;
import com.example.kidscode.Repository.ModulesRepository;
import com.example.kidscode.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class CourseController {

    private final UsersRepository usersRepository;
    private final CourseRepository courseRepository;
    private final ModulesRepository modulesRepository;

    @Autowired
    public CourseController(UsersRepository usersRepository, CourseRepository courseRepository, ModulesRepository modulesRepository) {
        this.usersRepository = usersRepository;
        this.courseRepository = courseRepository;
        this.modulesRepository = modulesRepository;

    }

    @GetMapping("/CourseList")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Users user = usersRepository.findByUsername(userDetails.getUsername());
        model.addAttribute("user",user);
        return "CourseList";
    }


    @PostMapping("/Course")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model,  @ModelAttribute(value = "var") Course course) {
            Users user = usersRepository.findByUsername(userDetails.getUsername());
            model.addAttribute("user",user);
            model.addAttribute("course", course);
            model.addAttribute("modules", modulesRepository.findByCourse(course));
            if (user.getRole().equals("child")){
                return "Course_Child";
            }
            else {
                return "Course";
            }
    }
}
