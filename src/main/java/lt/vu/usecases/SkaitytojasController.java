package lt.vu.usecases;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lt.vu.persistence.SkaitytojasDAO;
import lt.vu.persistence.KnygaDAO;
import lt.vu.entities.Knyga;
import lt.vu.entities.Skaitytojas;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class SkaitytojasController {

    @Inject
    private SkaitytojasDAO skaitytojasDAO;

    @Inject
    private KnygaDAO knygaDAO;

    private Skaitytojas naujas = new Skaitytojas();
    private List<Long> pasirinktoKnygosId = new ArrayList<>();

    public List<Skaitytojas> getVisiSkaitytojai() {
        return skaitytojasDAO.loadAll();
    }

    public List<Knyga> getVisosKnygos() {
        return knygaDAO.loadAll();
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
        if (!pasirinktoKnygosId.isEmpty()) {
            List<Knyga> knygos = pasirinktoKnygosId.stream()
                    .map(knygaDAO::findById)
                    .toList();
            naujas.setKnygos(knygos);


            for (Knyga knyga : knygos) {
                if (knyga != null && !knyga.getSkaitytojai().contains(naujas)) {
                    knyga.getSkaitytojai().add(naujas);
                }
            }
        }
        skaitytojasDAO.save(naujas);
        naujas = new Skaitytojas();
        pasirinktoKnygosId = new ArrayList<>();  // Reset selected books
        return "skaitytojai?faces-redirect=true";
    }
}