package lt.vu.usecases;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lt.vu.dao.KnygaDAO_JPA;
import lt.vu.dao.SkaitytojasDAO_JPA;
import lt.vu.entities.Knyga;
import lt.vu.entities.Skaitytojas;
import java.util.List;

@Named
@RequestScoped
public class SkaitytojasController {

    @Inject
    private SkaitytojasDAO_JPA skaitytojasDAOJPA;

    @Inject
    private KnygaDAO_JPA knygaDAOJPA;

    private Skaitytojas naujas = new Skaitytojas();
    private List<Long> pasirinktoKnygosId;

    public List<Skaitytojas> getVisiSkaitytojai() {
        return skaitytojasDAOJPA.loadAll();
    }

    public List<Knyga> getVisosKnygos() {
        return knygaDAOJPA.loadAll();
    }

    public Skaitytojas getNaujas() {
        return naujas;
    }

    public List<Long> getPasirinktoKnygosId() {
        return pasirinktoKnygosId;
    }

    public void setPasirinktoKnygosId(List<Long> pasirinktoKnygosId) {
        this.pasirinktoKnygosId = pasirinktoKnygosId;
    }

    public String prideti() {
        if (pasirinktoKnygosId != null) {
            List<Knyga> knygos = pasirinktoKnygosId.stream()
                    .map(knygaDAOJPA::findById)
                    .toList();
            naujas.setKnygos(knygos);
        }
        skaitytojasDAOJPA.save(naujas);
        naujas = new Skaitytojas();
        return "skaitytojai?faces-redirect=true";
    }
}