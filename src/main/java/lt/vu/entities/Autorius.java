package lt.vu.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="AUTORIUS")
public class Autorius {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VARDAS", nullable = false)
    private String vardas;

    @Column(name = "PAVARDE", nullable = false)
    private String pavarde;

    @OneToMany(mappedBy = "autorius", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Knyga> knygos = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVardas() { return vardas; }
    public void setVardas(String vardas) { this.vardas = vardas; }

    public String getPavarde() { return pavarde; }
    public void setPavarde(String pavarde) { this.pavarde = pavarde; }

    public List<Knyga> getKnygos() { return knygos; }
    public void setKnygos(List<Knyga> knygos) { this.knygos = knygos; }
}