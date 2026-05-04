package lt.vu.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SKAITYTOJAS")
public class Skaitytojas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VARDAS", nullable = false)
    private String vardas;

    @Column(name = "PAVARDE", nullable = false)
    private String pavarde;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SKAITYTOJAS_KNYGA",
            joinColumns = @JoinColumn(name = "SKAITYTOJAS_ID"),
            inverseJoinColumns = @JoinColumn(name = "KNYGA_ID")
    )
    private List<Knyga> knygos = new ArrayList<>();


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVardas() { return vardas; }
    public void setVardas(String vardas) { this.vardas = vardas; }

    public String getPavarde() { return pavarde; }
    public void setPavarde(String pavarde) { this.pavarde = pavarde; }

    public void setKnygos(List<Knyga> knygos) {
        this.knygos = knygos;
    }

    public List<Knyga> getKnygos() {
        return knygos;
    }
}
