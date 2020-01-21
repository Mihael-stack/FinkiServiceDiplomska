package production.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="diplomska")
public class Diplomska {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String description;
    private Integer level;
    private int grade;
    private int checkCommission;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "diplomska_student",
            joinColumns = @JoinColumn(
                    name = "diplomska_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"))
    private Student student;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "diplomska_termin",
            joinColumns = @JoinColumn(
                    name = "diplomska_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "termin_id", referencedColumnName = "id"))
    private Termin termin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "diplomski_mentor",
            joinColumns = @JoinColumn(
                    name = "diplomska_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "mentor_id", referencedColumnName = "id"))
    private User mentor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "diplomski_commission",
            joinColumns = @JoinColumn(
                    name = "diplomska_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "commission_id", referencedColumnName = "id"))
    private Set<User> commission;

    public Diplomska(){}


    public Diplomska(String title, String description, String firstName, String lastName, String student_index,
                     String nasoka, User mentor, Set<User> commission){
        this.title = title;
        this.description = description;
        this.student = new Student(firstName, lastName, student_index, nasoka);
        this.mentor = mentor;
        this.commission = commission;
        this.level = Level.SUBMITTED.getValue();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getCheckCommission() {
        return checkCommission;
    }

    public void setCheckCommission(int checkCommission) {
        this.checkCommission = checkCommission;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public User getMentor() {
        return mentor;
    }

    public void setMentor(User mentor) {
        this.mentor = mentor;
    }

    public Set<User> getCommission() {
        return commission;
    }

    public void setCommission(Set<User> commission) {
        this.commission = commission;
    }
}
