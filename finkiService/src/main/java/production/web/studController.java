package production.web;

import io.swagger.models.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import production.model.Student;
import production.service.studService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class studController {

    private studService StudService;

    public studController(studService StudService){
        this.StudService = StudService;
    }

    @GetMapping("/studentsList")
    public List<Student> students(Model model){
        return StudService.getAll();
    }


}
