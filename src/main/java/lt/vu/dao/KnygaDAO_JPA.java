package lt.vu.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lt.vu.entities.Knyga;
import java.util.List;

@ApplicationScoped
public class KnygaDAO_JPA {

    @Inject
    private EntityManager em;

    public List<Knyga> loadAll() {
        return em.createQuery("SELECT k FROM Knyga k", Knyga.class)
                .getResultList();
    }

    public Knyga findById(Long id) {
        return em.find(Knyga.class, id);
    }

    @Transactional
    public void save(Knyga knyga) {
        em.persist(knyga);
    }

    @Transactional
    public void update(Knyga knyga) {
        em.merge(knyga);
    }

    @Transactional
    public void delete(Long id) {
        Knyga knyga = findById(id);
        if (knyga != null) {
            em.remove(knyga);
        }
    }

    public List<Knyga> findByAutorius(Long autoriusId) {
        return em.createQuery(
                        "SELECT k FROM Knyga k WHERE k.autorius.id = :autoriusId",
                        Knyga.class)
                .setParameter("autoriusId", autoriusId)
                .getResultList();
    }
}