package production.model;

import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String student_index;
    private String nasoka;

    public Student(){}

    public Student(String firstName, String lastName, String student_index, String nasoka){
        this.firstName = firstName;
        this.lastName = lastName;
        this.student_index = student_index;
        this.nasoka = nasoka;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIndex() {
        return student_index;
    }

    public void setIndex(String index) {
        this.student_index = index;
    }

    public String getNasoka() {
        return nasoka;
    }

    public void setNasoka(String nasoka) {
        this.nasoka = nasoka;
    }
}
