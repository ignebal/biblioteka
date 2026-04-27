package lt.vu.usecases;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lt.vu.dao.AutoriusDAO_JPA;
import lt.vu.dao.KnygaDAO_JPA;
import lt.vu.entities.Autorius;
import lt.vu.entities.Knyga;
import java.util.List;

@Named
@RequestScoped
public class KnygaController {

    @Inject
    private KnygaDAO_JPA knygaDAOJPA;

    @Inject
    private AutoriusDAO_JPA autoriusDAOJPA;

    private Knyga naujas = new Knyga();
    private Long autoriusId;

    public List<Knyga> getVisosKnygos() {
        return knygaDAOJPA.loadAll();
    }

    public List<Autorius> getVisiAutoriai() {
        return autoriusDAOJPA.loadAll();
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
            Autorius autorius = autoriusDAOJPA.findById(autoriusId);
            naujas.setAutorius(autorius);
        }
        knygaDAOJPA.save(naujas);
        naujas = new Knyga();
        return "knygos?faces-redirect=true";
    }
}