package production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import production.model.Diplomska;
import production.model.Level;
import production.model.Termin;
import production.model.User;
import production.repository.DiplomskaRepository;
import production.repository.StudentRepository;
import production.repository.TerminRepository;

import java.util.List;
import java.util.Set;

@Service
public class DiplomskaServiceImpl implements DiplomskaService {
    @Autowired
    private DiplomskaRepository diplomskaRepository;
    @Autowired
    private TerminRepository terminRepository;
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public Diplomska getById(Long id) {
       return diplomskaRepository.getById(id);
    }


    public void addNewDiplomska(String title, String description, String firstName, String lastName, String student_index,
                                String nasoka, User mentor, Set<User> commission) {
        Diplomska diplomska = new Diplomska(title,description,firstName,lastName,student_index,nasoka,mentor,commission);
        diplomskaRepository.save(diplomska);

    }

    @Override
    public String ApprovedByDekan(boolean approve, Long id) {

        Diplomska diplomska = diplomskaRepository.getById(id);

        if(approve == false){
            diplomskaRepository.deleteById(id);
            return "delete";
        }
        else{

            diplomska.setLevel(Level.APPROVED_BY_PRODEKAN.getValue());
            diplomskaRepository.save(diplomska);
            return "update";
        }
    }

    @Override
    public String ApprovedByCommission(boolean approve, Long id) {
        Diplomska diplomska = diplomskaRepository.getById(id);

        if(approve == false){
            diplomskaRepository.deleteById(id);
            return "delete";
        }
        else{

            int check = diplomska.getCheckCommission();
            check++;
            diplomska.setCheckCommission(check);
            diplomskaRepository.save(diplomska);

            if(diplomska.getCheckCommission() == 2){

                diplomska.setLevel(Level.APPROVED_BY_COMMISSION.getValue());
                diplomskaRepository.save(diplomska);
                return "update";
            }
            return "update";

        }
    }

    @Override
    public String ApprovedSchedule(boolean approve, Long id, int day, int month, int year, String prostorija, String cas) {
        Diplomska diplomska = diplomskaRepository.getById(id);

        if(approve == false){
            diplomskaRepository.deleteById(id);
            return "delete";
        }
        else{
            diplomska.setTermin(new Termin(prostorija,year,month,day,cas));
            diplomska.setLevel(Level.SCHEDULED.getValue());
            diplomskaRepository.save(diplomska);
            return "update";
        }
    }

    @Override
    public String ApprovedArchived(boolean approve, Long id, int grade) {
        Diplomska diplomska = diplomskaRepository.getById(id);

        if(approve == false){
            diplomskaRepository.deleteById(id);
            return "delete";
        }
        else{
            diplomska.setGrade(grade);
            diplomska.setLevel(Level.ARCHIVE.getValue());
            diplomskaRepository.save(diplomska);
            return "update";
        }
    }

    @Override
    public String ApprovedByMentor(boolean approve, Long id) {

        Diplomska diplomska = diplomskaRepository.getById(id);

        if(approve == false){
            diplomskaRepository.deleteById(id);
            return "delete";
        }
        else{

            diplomska.setLevel(Level.APPROVED_BY_MENTOR.getValue());
            diplomskaRepository.save(diplomska);
            return "update";
        }
    }



    @Override
    public List<Diplomska> getAllByLevelApprovedByProdekan() {
        List<Diplomska> lista = null;
        lista.add(diplomskaRepository.getAllByLevel(Level.APPROVED_BY_PRODEKAN.getValue()));

        return lista;
    }

    @Override
    public List<Diplomska> getAllByLevelApprovedByMentor() {
        List<Diplomska> lista = null;
        lista.add(diplomskaRepository.getAllByLevel(Level.APPROVED_BY_MENTOR.getValue()));

        return lista;
    }

    @Override
    public List<Diplomska> getAllByLevelApprovedByCommission() {
        List<Diplomska> lista = null;
        lista.add(diplomskaRepository.getAllByLevel(Level.APPROVED_BY_COMMISSION.getValue()));

        return lista;
    }

    @Override
    public List<Diplomska> getAllByLevelScheduled() {
        List<Diplomska> lista = null;
        lista.add(diplomskaRepository.getAllByLevel(Level.SCHEDULED.getValue()));

        return lista;
    }

    @Override
    public List<Diplomska> getAllByLevelArchived() {
        List<Diplomska> lista = null;
        lista.add(diplomskaRepository.getAllByLevel(Level.ARCHIVE.getValue()));

        return lista;
    }

    @Override
    public List<Diplomska> getAllByLevelSubmitted() throws NullPointerException{
        List<Diplomska> lista = null;
        lista.add(diplomskaRepository.getAllByLevel(Level.SUBMITTED.getValue()));

        return lista;
    }

    @Override
    public Diplomska getByLevelSubmitted(Long id) {
        return diplomskaRepository.getByLevelAndId(Level.SUBMITTED.getValue(), id);
    }

    @Override
    public Diplomska getByLevelApprovedByProdekan(Long id) {
        return diplomskaRepository.getByLevelAndId(Level.APPROVED_BY_PRODEKAN.getValue(), id);
    }

    @Override
    public Diplomska getByLevelApprovedByMentor(Long id) {
        return diplomskaRepository.getByLevelAndId(Level.APPROVED_BY_MENTOR.getValue(), id);
    }

    @Override
    public Diplomska getByLevelApprovedByCommission(Long id) {
        return diplomskaRepository.getByLevelAndId(Level.APPROVED_BY_COMMISSION.getValue(), id);
    }

    @Override
    public Diplomska getByLevelScheduled(Long id) {
        return diplomskaRepository.getByLevelAndId(Level.SCHEDULED.getValue(), id);
    }

    @Override
    public Diplomska getByLevelArchived(Long id) {
        return diplomskaRepository.getByLevelAndId(Level.ARCHIVE.getValue(), id);
    }


}
