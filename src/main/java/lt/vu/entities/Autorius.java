package lt.vu.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name="AUTORIUS")
@Getter @Setter
public class Autorius {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VARDAS", nullable = false)
    private String vardas;

    @Column(name = "PAVARDE", nullable = false)
    private String pavarde;

    @OneToMany(mappedBy = "autorius", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Knyga> knygos;
}