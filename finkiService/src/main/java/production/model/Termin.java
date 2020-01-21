package production.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "termin")
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String prostorija;
    private Date date;
    private String cas;

    public Termin(String prostorija, int year, int month, int day, String cas) {
        this.prostorija=prostorija;
        this.date = new Date(year,month,day);
        this.cas=cas;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProstorija() {
        return prostorija;
    }

    public void setProstorija(String prostorija) {
        this.prostorija = prostorija;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }
}
