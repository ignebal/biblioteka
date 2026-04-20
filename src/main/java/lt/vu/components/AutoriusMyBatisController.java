package lt.vu.components;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lt.vu.dao.AutoriusMyBatisDAO;
import lt.vu.mybatis.model.AutoriusMyBatis;
import java.util.List;

@Named
@RequestScoped
public class AutoriusMyBatisController {

    @Inject
    private AutoriusMyBatisDAO autoriusMyBatisDAO;

    private AutoriusMyBatis naujas = new AutoriusMyBatis();

    public List<AutoriusMyBatis> getVisiAutoriai() {
        return autoriusMyBatisDAO.loadAll();
    }

    public AutoriusMyBatis getNaujas() {
        return naujas;
    }

    public String prideti() {
        autoriusMyBatisDAO.save(naujas);
        naujas = new AutoriusMyBatis();
        return "mybatis?faces-redirect=true";
    }
}