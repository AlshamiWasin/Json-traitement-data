package BO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Acteur {
    @Id
    @Column(name = "id", nullable = false)
    private String id;


    @Embedded
    private InfoPersonnel infoPersonnel;

    private LocalDate dateNaissance;
    private String lieuNaissaice;

    @ManyToMany()
    @JoinTable(name = "Film_acteurs",
            joinColumns = @JoinColumn(name = "acteurs_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Film> films = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "Film_acteursPrincipal",
            joinColumns = @JoinColumn(name = "acteurs_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Film> filmActeursPrincipal = new ArrayList<>();

    @OneToMany(mappedBy = "acteur")
    private List<Role> roles = new ArrayList<>();


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Acteur() {
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissaice() {
        return lieuNaissaice;
    }

    public void setLieuNaissaice(String lieuNaissaice) {
        this.lieuNaissaice = lieuNaissaice;
    }

    public InfoPersonnel getInfoPersonnel() {
        return infoPersonnel;
    }

    public void setInfoPersonnel(InfoPersonnel infoPersonnel) {
        this.infoPersonnel = infoPersonnel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Film> getFilmActeursPrincipal() {
        return filmActeursPrincipal;
    }

    public void setFilmActeursPrincipal(List<Film> filmActeursPrincipal) {
        this.filmActeursPrincipal = filmActeursPrincipal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acteur acteur = (Acteur) o;
        return Objects.equals(id, acteur.id) && infoPersonnel.equals(acteur.infoPersonnel) && dateNaissance.equals(acteur.dateNaissance) && lieuNaissaice.equals(acteur.lieuNaissaice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, infoPersonnel, dateNaissance, lieuNaissaice);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Acteur{");
        sb.append("id='").append(id).append('\'');
        sb.append(", infoPersonnel=").append(infoPersonnel);
        sb.append(", dateNaissance=").append(dateNaissance);
        sb.append(", lieuNaissaice='").append(lieuNaissaice).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
