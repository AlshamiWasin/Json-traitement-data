package BO;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Realisateur {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @GenericGenerator(name = "seq", strategy = "increment")
    private Long id;

    @Embedded
    private InfoPersonnel infoPersonnel;


    @ManyToMany(mappedBy = "realisateurs", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Film> films = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Realisateur() {
    }

    public InfoPersonnel getInfoPersonnel() {
        return infoPersonnel;
    }

    public void setInfoPersonnel(InfoPersonnel infoPersonnel) {
        this.infoPersonnel = infoPersonnel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Realisateur{");
        sb.append("id=").append(id);
        sb.append(", infoPersonnel=").append(infoPersonnel);
        sb.append(", films=").append(films);
        sb.append('}');
        return sb.toString();
    }
}
