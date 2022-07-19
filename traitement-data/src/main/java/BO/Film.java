package BO;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Film")
public class Film {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String nom;
    private String url;
    private String plot;
    private Integer annesSortie;
    private String langue;

    @ManyToMany()
    @JoinTable(name = "Film_Realisateurs",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "realisateurs_id"))
    private List<Realisateur> realisateurs = new ArrayList<>();

    @ManyToMany(mappedBy = "films")
    private List<Acteur> acteurs = new ArrayList<>();

    @ManyToMany(mappedBy = "filmActeursPrincipal")
    private List<Acteur> castingPrincipal = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "Film_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id"))
    private List<Genre> genres = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "lieu_tournage_id")
    private LieuTournage lieuTournage;

    @OneToMany(mappedBy = "film", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Role> roles = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "pays_id")
    private Pays pays;

    public Pays getPays() {
        return pays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Integer getAnnesSortie() {
        return annesSortie;
    }

    public void setAnnesSortie(Integer annesSortie) {
        this.annesSortie = annesSortie;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public LieuTournage getLieuTournage() {
        return lieuTournage;
    }

    public void setLieuTournage(LieuTournage lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Acteur> getCastingPrincipal() {
        return castingPrincipal;
    }

    public void setCastingPrincipal(List<Acteur> castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Film() {
    }

    public List<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public List<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(List<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Film{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", plot='").append(plot).append('\'');
        sb.append(", annesSortie=").append(annesSortie);
        sb.append(", langue='").append(langue).append('\'');
        sb.append(", realisateurs=").append(realisateurs);
        sb.append(", acteurs=").append(acteurs);
        sb.append(", genres=").append(genres);
        sb.append(", lieuTournage=").append(lieuTournage);
        sb.append(", roles=").append(roles);
        sb.append(", pays=").append(pays);
        sb.append('}');
        return sb.toString();
    }*/
}
