package in.ac.mitmeerut.clearance.controller;

import in.ac.mitmeerut.clearance.entity.Student;
import in.ac.mitmeerut.clearance.entity.User;
import in.ac.mitmeerut.clearance.enums.Role;
import in.ac.mitmeerut.clearance.service.StudentService;
import in.ac.mitmeerut.clearance.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    private final UserService userService;
    private final StudentService studentService;

    public PageController(
            UserService userService,
            StudentService studentService) {

        this.userService = userService;
        this.studentService = studentService;
    }

    // Open login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Process login form
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            User user = userService.getUserByEmail(email);

            // Temporary password comparison for local testing
            if (user.getPassword() == null
                    || !user.getPassword().equals(password)) {

                model.addAttribute("error", "Invalid email or password.");
                return "login";
            }

            if (user.getRole() == Role.STUDENT) {
                Student student =
                        studentService.getStudentByUserId(user.getId());

                redirectAttributes.addAttribute("studentId", student.getId());

                return "redirect:/student-dashboard";
            }

            if (user.getRole() == Role.STAFF) {
                return "redirect:/staff-dashboard";
            }

            if (user.getRole() == Role.ADMIN) {
                return "redirect:/admin-dashboard";
            }

            model.addAttribute("error", "Invalid user role.");
            return "login";

        } catch (RuntimeException exception) {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }

    // Display student dashboard
    @GetMapping("/student-dashboard")
    public String studentDashboard(
        @RequestParam(required = false) Long studentId,
        Model model) {

        if (studentId == null) {
            return "redirect:/login";
        }

        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "student-dashboard";
    }

    @GetMapping("/staff-dashboard")
    public String staffDashboard() {
        return "staff-dashboard";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }
}