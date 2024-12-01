package com.example.kidscode.Controllers;

import com.example.kidscode.Models.Course;
import com.example.kidscode.Models.Kids;
import com.example.kidscode.Models.PrefKids;
import com.example.kidscode.Models.Users;
import com.example.kidscode.Repository.CourseRepository;
import com.example.kidscode.Repository.KidsRepository;
import com.example.kidscode.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class ProfileController {
    PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final KidsRepository kidsRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ProfileController(UsersRepository usersRepository, KidsRepository kidsRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.kidsRepository = kidsRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/Profile")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Users user = usersRepository.findByUsername(userDetails.getUsername());
        if (user.getRole().equals("parent")){
            model.addAttribute("user", user);
            model.addAttribute("child", new PrefKids());
            model.addAttribute("kids",kidsRepository.findByParent(user));
            return "Profile_Parent";
        }
        else if (user.getRole().equals("child")){
            model.addAttribute("courses", courseRepository.findAll());
            model.addAttribute("user", user);
            return "Profile_Child";
        }
        else if (user.getRole().equals("teacher")){
            model.addAttribute("user", user);
            model.addAttribute("course", new Course());
            model.addAttribute("courses", courseRepository.findByTeacher(user));
            return "Profile_Teacher";
        }
        else return "Profile_Child";

    }

    @PostMapping("/Profile/addChild")
    public String addChild(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute(value = "var") PrefKids prefKids) {
        Users kid = usersRepository.save(new Users(prefKids.getLogin(), passwordEncoder.encode(prefKids.getPassword()), prefKids.getSurname(), prefKids.getName(), prefKids.getPatronymic(), "child"));
        kidsRepository.save(new Kids(prefKids.getAge(), kid, usersRepository.findByUsername(prefKids.getParent())));
        return "redirect:/Profile";
    }

    @PostMapping("/Profile/child_delete")
    public String deleteChild(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute(value = "var") Kids kids) {
        Kids kid = kidsRepository.findById(kids.getId()).get();
        kidsRepository.delete(kid);
        usersRepository.delete(kid.getUser());
        return "redirect:/Profile";
    }

    @PostMapping("/Profile/addCourse")
    public String addCourse(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute(value = "var") Course course) {
        course.setTeacher(usersRepository.findByUsername(userDetails.getUsername()));
        courseRepository.save(course);
        return "redirect:/Profile";
    }

//    @PostMapping("/Profile/child_update")
//    public String updateChild(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute(value = "var") Kids kids) {
//        Kids kid = kidsRepository.findById(kids.getId()).get();
//        return "redirect:/Profile";
//    }

}
