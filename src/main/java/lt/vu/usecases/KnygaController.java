package lt.vu.usecases;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lt.vu.persistence.AutoriusDAO;
import lt.vu.persistence.KnygaDAO;
import lt.vu.entities.Autorius;
import lt.vu.entities.Knyga;
import java.util.List;

@Named
@RequestScoped
public class KnygaController {

    @Inject
    private KnygaDAO knygaDAO;

    @Inject
    private AutoriusDAO autoriusDAO;

    private Knyga naujas = new Knyga();
    private Long autoriusId;

    public List<Knyga> getVisosKnygos() {
        return knygaDAO.loadAll();
    }

    public List<Autorius> getVisiAutoriai() {
        return autoriusDAO.loadAll();
    }

    public Knyga getNaujas() {
        return naujas;
    }

    public Long getAutoriusId() {
        return autoriusId;
    }

    public void setAutoriusId(Long autoriusId) {
        this.autoriusId = autoriusId;
    }

    public String prideti() {
        if (autoriusId != null) {
            Autorius autorius = autoriusDAO.findById(autoriusId);
            naujas.setAutorius(autorius);
            if (autorius != null && !autorius.getKnygos().contains(naujas)) {
                autorius.getKnygos().add(naujas);
            }
        }
        knygaDAO.save(naujas);
        naujas = new Knyga();
        return "knygos?faces-redirect=true";
    }
}