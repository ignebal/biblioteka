package lt.vu.usecases;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lt.vu.dao.AutoriusDAO_JPA;
import lt.vu.entities.Autorius;
import java.util.List;

@Named
@RequestScoped
public class AutoriusController {

    @Inject
    private AutoriusDAO_JPA autoriusDAOJPA;

    private Autorius naujas = new Autorius();

    public List<Autorius> getVisiAutoriai() {
        return autoriusDAOJPA.loadAll();
    }

    public Autorius getNaujas() {
        return naujas;
    }

    public String prideti() {
        autoriusDAOJPA.save(naujas);
        naujas = new Autorius();
        return "autoriai?faces-redirect=true";
    }
}