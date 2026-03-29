package lt.vu.components;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lt.vu.dao.KnygaDAO;
import lt.vu.dao.SkaitytojasDAO;
import lt.vu.entities.Knyga;
import lt.vu.entities.Skaitytojas;
import java.util.List;

@Named
@RequestScoped
public class SkaitytojasController {

    @Inject
    private SkaitytojasDAO skaitytojasDAO;

    @Inject
    private KnygaDAO knygaDAO;

    private Skaitytojas naujas = new Skaitytojas();
    private List<Long> pasirinktoKnygosId;

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
        if (pasirinktoKnygosId != null) {
            List<Knyga> knygos = pasirinktoKnygosId.stream()
                    .map(knygaDAO::findById)
                    .toList();
            naujas.setKnygos(knygos);
        }
        skaitytojasDAO.save(naujas);
        naujas = new Skaitytojas();
        return "skaitytojai?faces-redirect=true";
    }
}