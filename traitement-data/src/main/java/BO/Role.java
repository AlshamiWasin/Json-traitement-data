package BO;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class  Role {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq1")
    @GenericGenerator(name = "seq1", strategy = "increment")
    private Long id;

    private String characterName;

    @ManyToOne
    @JoinColumn(name = "acteur_id")
    private Acteur acteur;

    @ManyToOne()
    @JoinColumn(name = "film_id")
    private Film film;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Acteur getActeur() {
        return acteur;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", characterName='").append(characterName).append('\'');
        sb.append(", film=").append(film);
        sb.append('}');
        return sb.toString();
    }
}
