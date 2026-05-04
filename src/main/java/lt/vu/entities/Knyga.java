package lt.vu.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "KNYGA")
public class Knyga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PAVADINIMAS", nullable = false)
    private String pavadinimas;

    @Column(name = "ISBN")
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "AUTORIUS_ID")
    private Autorius autorius;

    @ManyToMany(mappedBy = "knygos", fetch = FetchType.EAGER)
    private List<Skaitytojas> skaitytojai = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPavadinimas() { return pavadinimas; }
    public void setPavadinimas(String pavadinimas) { this.pavadinimas = pavadinimas; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public void setAutorius(Autorius autorius) {
        this.autorius = autorius;
    }

    public Autorius getAutorius() {
        return autorius;
    }

    public List<Skaitytojas> getSkaitytojai() { return skaitytojai; }
    public void setSkaitytojai(List<Skaitytojas> skaitytojai) { this.skaitytojai = skaitytojai; }
}
