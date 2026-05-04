package lt.vu.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lt.vu.entities.Autorius;

import java.util.List;

@ApplicationScoped
public class AutoriusDAOJPA implements AutoriusDAO {

    @Inject
    private EntityManager em;

    @Override
    public List<Autorius> loadAll() {
        return em.createQuery(
                "SELECT DISTINCT a FROM Autorius a LEFT JOIN FETCH a.knygos",
                Autorius.class)
                .getResultList();
    }

    @Override
    public Autorius findById(Long id) {
        return em.find(Autorius.class, id);
    }

    @Override
    @Transactional
    public void save(Autorius autorius) {
        em.persist(autorius);
        em.flush();
    }

    @Override
    @Transactional
    public void update(Autorius autorius) {
        em.merge(autorius);
        em.flush();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Autorius autorius = findById(id);
        if (autorius != null) {
            em.remove(autorius);
        }
    }
}

