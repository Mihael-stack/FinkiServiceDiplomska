package production.service;

import org.springframework.stereotype.Service;
import production.model.Diplomska;
import production.model.Level;
import production.model.User;
import production.repository.DiplomskaRepository;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Set;


public interface DiplomskaService {

    Diplomska getById(Long id);

    public void addNewDiplomska(String title, String description, String firstName, String lastName, String student_index, String nasoka, User mentor, Set<User> commission);

    List<Diplomska> getAllByLevelSubmitted();
    List<Diplomska> getAllByLevelApprovedByProdekan();
    List<Diplomska> getAllByLevelApprovedByMentor();
    List<Diplomska> getAllByLevelApprovedByCommission();
    List<Diplomska> getAllByLevelScheduled();
    List<Diplomska> getAllByLevelArchived();

    Diplomska getByLevelSubmitted(Long id);
    Diplomska getByLevelApprovedByProdekan(Long id);
    Diplomska getByLevelApprovedByMentor(Long id);
    Diplomska getByLevelApprovedByCommission(Long id);
    Diplomska getByLevelScheduled(Long id);
    Diplomska getByLevelArchived(Long id);


    public String ApprovedByMentor(boolean approve,Long id);
    public String ApprovedByDekan(boolean approve, Long id);
    public String ApprovedByCommission(boolean approve, Long id);
    public String ApprovedSchedule(boolean approve, Long id,int day, int month,int year, String prostorija,String cas);
    public String ApprovedArchived(boolean approve, Long id, int grade);

}