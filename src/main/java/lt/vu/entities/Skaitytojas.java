package lt.vu.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "SKAITYTOJAS")
@Getter @Setter
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
    private List<Knyga> knygos;
}
