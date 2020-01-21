package production.web;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import production.model.Diplomska;
import production.model.Level;
import production.model.User;
import production.service.DiplomskaService;
import production.service.UserService;

import java.util.List;
import java.util.Set;


@Controller
@Api(value = "MerchantControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiplomskaController {

    @Autowired
    UserService userService;

    @Autowired
    DiplomskaService diplomskaService;

    // Site diplomski koi se kreirani, level = SUBMITTED
    @GetMapping("/diplomska/Submitted")
    public List<Diplomska> getAllDiplomskiLevel1(){
        return diplomskaService.getAllByLevelSubmitted();
    }

    @GetMapping("/diplomska/Submitted/{id}")
    public Diplomska getSubmittedDiplomska(@PathVariable("id") String diplomskaId){
        Long id = Long.valueOf(diplomskaId);

        return diplomskaService.getByLevelSubmitted(id);
    }

    @PostMapping("diplomska/add")
    @PreAuthorize("isAuthenticated() && hasRole('ROLE_TEACHER')")
    public void addNewDiplomska(@RequestParam String title, @RequestParam String description, @RequestParam String firstName, @RequestParam  String lastName,
                                @RequestParam String student_index, @RequestParam String nasoka, @RequestParam String commission1_email, @RequestParam String commission2_email){

        User mentor = userService.currentUser();
        Set<User> commission = userService.addCommission(commission1_email, commission2_email);

        diplomskaService.addNewDiplomska(title,description,firstName,lastName,student_index,nasoka,mentor,commission);


    }

    @PostMapping("/diplomska/Submitted/{id}/ApproveByDekan")
    @PreAuthorize("isAuthenticated() && hasRole('ROLE_PRODEKAN')")
    public String Approve(@PathVariable("id") String diplomskaId,@RequestParam boolean approve){
        Long id = Long.valueOf(diplomskaId);
        return diplomskaService.ApprovedByDekan(approve, id);
    }

    @GetMapping("/diplomska/ApprovedByProDekan")
    public List<Diplomska> getAllDiplomskiLevel2(){
        return diplomskaService.getAllByLevelApprovedByMentor();
    }

    @GetMapping("/diplomska/ApprovedByProDekan/{id}")
    @PreAuthorize("isAuthenticated() && hasRole('ROLE_TEACHER')")
    public Diplomska getApprovedByProDekanDiplomska(@PathVariable("id") String diplomskaId){
        Long id = Long.valueOf(diplomskaId);

        return diplomskaService.getByLevelApprovedByProdekan(id);
    }

    @PostMapping("/diplomska/ApprovedByProDekan/{id}/ApprovedByMentor")
    @PreAuthorize("isAuthenticated()")
    public String ApproveMentor(@PathVariable("id") String diplomskaId,@RequestParam boolean approve){
        Long id = Long.valueOf(diplomskaId);
        User mentor = userService.currentUser();
        Diplomska diplomska = diplomskaService.getById(id);
        User check = diplomska.getMentor();
        if(mentor.getEmail().equals(check.getEmail())) {
            return diplomskaService.ApprovedByMentor(approve, id);
        }
        else{
            return "Access Denied";
        }
    }


    @GetMapping("/diplomska/ApprovedByMentor")
    public List<Diplomska> getAllDiplomskiLevel3(){
        return diplomskaService.getAllByLevelApprovedByMentor();
    }

    @GetMapping("/diplomska/ApprovedByMentor/{id}")
    @PreAuthorize("isAuthenticated()")
    public Diplomska getApprovedByMentorDiplomska(@PathVariable("id") String diplomskaId){
        Long id = Long.valueOf(diplomskaId);

        return diplomskaService.getByLevelApprovedByMentor(id);
    }

    @PostMapping("/diplomska/ApprovedByMentor/{id}/ApprovedByCommission")
    @PreAuthorize("isAuthenticated()")
    public String ApproveCommission(@PathVariable("id") String diplomskaId,@RequestParam boolean approve){
        Long id = Long.valueOf(diplomskaId);
        User user = userService.currentUser();
        Diplomska diplomska = diplomskaService.getById(id);
        Set<User> commission = diplomska.getCommission();
        if(commission.contains(user)) {
            return diplomskaService.ApprovedByCommission(approve, id);
        }
        else{
            return "Access Denied";
        }
    }

    @GetMapping("/diplomska/ApprovedByCommission")
    public List<Diplomska> getAllDiplomskiLevel4(){
        return diplomskaService.getAllByLevelApprovedByCommission();
    }

    @GetMapping("/diplomska/ApprovedByCommission/{id}")
    @PreAuthorize("isAuthenticated()")
    public Diplomska getApprovedByCommissionDiplomska(@PathVariable("id") String diplomskaId){
        Long id = Long.valueOf(diplomskaId);

        return diplomskaService.getByLevelApprovedByCommission(id);
    }

    @PostMapping("/diplomska/ApprovedByCommission/{id}/Scheduled")
    @PreAuthorize("isAuthenticated()")
    public String ApproveScheduled(@PathVariable("id") String diplomskaId,@RequestParam boolean approve, @RequestParam int day,@RequestParam int month,@RequestParam int year, @RequestParam String prostorija, @RequestParam String cas ){
        Long id = Long.valueOf(diplomskaId);
        User mentor = userService.currentUser();
        Diplomska diplomska = diplomskaService.getById(id);
        User check = diplomska.getMentor();
        if(mentor.getEmail().equals(check.getEmail())) {
            return diplomskaService.ApprovedSchedule(approve, id, day, month,year,prostorija,cas);
        }
        else{
            return "Access Denied";
        }
    }
    @GetMapping("/diplomska/Scheduled")
    public List<Diplomska> getAllDiplomskiLevel5(){
        return diplomskaService.getAllByLevelScheduled();
    }

    @GetMapping("/diplomska/Scheduled/{id}")
    @PreAuthorize("isAuthenticated()")
    public Diplomska getScheduledDiplomska(@PathVariable("id") String diplomskaId){
        Long id = Long.valueOf(diplomskaId);

        return diplomskaService.getByLevelScheduled(id);
    }

    @PostMapping("/diplomska/Scheduled/{id}/Archived")
    @PreAuthorize("isAuthenticated() && hasRole('ROLE_PRODEKAN')")
    public String ApproveArchived(@PathVariable("id") String diplomskaId,@RequestParam boolean approve, @RequestParam int grade){
        Long id = Long.valueOf(diplomskaId);


        return diplomskaService.ApprovedArchived(approve,id, grade);

    }
    @GetMapping("/diplomska/Archived")
    public List<Diplomska> getAllDiplomskiLevel6(){
        return diplomskaService.getAllByLevelArchived();
    }

    @GetMapping("/diplomska/Archived/{id}")
    @PreAuthorize("isAuthenticated()")
    public Diplomska getArchivedDiplomska(@PathVariable("id") String diplomskaId){
        Long id = Long.valueOf(diplomskaId);

        return diplomskaService.getByLevelArchived(id);
    }



}
