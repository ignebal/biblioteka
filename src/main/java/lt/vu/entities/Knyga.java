package lt.vu.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "KNYGA")
@Getter @Setter
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
    private lt.vu.entities.Autorius autorius;

    @ManyToMany(mappedBy = "knygos", fetch = FetchType.EAGER)
    private List<Skaitytojas> skaitytojai;

}
