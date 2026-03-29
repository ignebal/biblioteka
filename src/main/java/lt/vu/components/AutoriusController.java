package lt.vu.components;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lt.vu.dao.AutoriusDAO;
import lt.vu.entities.Autorius;
import java.util.List;

@Named
@RequestScoped
public class AutoriusController {

    @Inject
    private AutoriusDAO autoriusDAO;

    private Autorius naujas = new Autorius();

    public List<Autorius> getVisiAutoriai() {
        return autoriusDAO.loadAll();
    }

    public Autorius getNaujas() {
        return naujas;
    }

    public String prideti() {
        autoriusDAO.save(naujas);
        naujas = new Autorius();
        return "autoriai?faces-redirect=true";
    }
}