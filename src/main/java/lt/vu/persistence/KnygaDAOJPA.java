package lt.vu.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lt.vu.entities.Knyga;
import java.util.List;

@ApplicationScoped
public class KnygaDAOJPA implements KnygaDAO {

    @Inject
    private EntityManager em;

    @Override
    public List<Knyga> loadAll() {
        return em.createQuery(
                "SELECT DISTINCT k FROM Knyga k LEFT JOIN FETCH k.autorius LEFT JOIN FETCH k.skaitytojai",
                Knyga.class)
                .getResultList();
    }

    @Override
    public Knyga findById(Long id) {
        return em.find(Knyga.class, id);
    }

    @Override
    @Transactional
    public void save(Knyga knyga) {
        em.persist(knyga);
        // associated author is also updated if it has changes
        if (knyga.getAutorius() != null) {
            em.merge(knyga.getAutorius());
        }
        em.flush();
    }

    @Override
    @Transactional
    public void update(Knyga knyga) {
        em.merge(knyga);
        em.flush();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Knyga knyga = findById(id);
        if (knyga != null) {
            em.remove(knyga);
        }
    }

    @Override
    public List<Knyga> findByAutorius(Long autoriusId) {
        return em.createQuery(
                        "SELECT k FROM Knyga k WHERE k.autorius.id = :autoriusId",
                        Knyga.class)
                .setParameter("autoriusId", autoriusId)
                .getResultList();
    }
}

