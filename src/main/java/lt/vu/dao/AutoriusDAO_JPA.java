package lt.vu.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lt.vu.entities.Autorius;

import java.util.List;

@ApplicationScoped
@Alternative
public class AutoriusDAO_JPA {

    @Inject
    private EntityManager em;

    public List<Autorius> loadAll() {
        return em.createQuery("SELECT a FROM Autorius a", Autorius.class)
                .getResultList();
    }

    public Autorius findById(Long id) {
        return em.find(Autorius.class, id);
    }

    @Transactional
    public void save(Autorius autorius) {
        em.persist(autorius);
    }

    @Transactional
    public void update(Autorius autorius) {
        em.merge(autorius);
    }

    @Transactional
    public void delete(Long id) {
        Autorius autorius = findById(id);
        if (autorius != null) {
            em.remove(autorius);
        }
    }
}
