package BO;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LieuTournage {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @GenericGenerator(name = "seq", strategy = "increment")
    private Long id;

    private String ville;
    private String etatDept;
    private String pays;

    @OneToMany(mappedBy = "lieuTournage")
    private List<Film> films = new ArrayList<>();

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LieuTournage() {
    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEtatDept() {
        return etatDept;
    }

    public void setEtatDept(String etatDept) {
        this.etatDept = etatDept;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LieuTournage{");
        sb.append("id=").append(id);
        sb.append(", ville='").append(ville).append('\'');
        sb.append(", etatDept='").append(etatDept).append('\'');
        sb.append(", pays='").append(pays).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
