package lt.vu.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lt.vu.entities.Skaitytojas;
import java.util.List;

@ApplicationScoped
public class SkaitytojasDAO {

    @Inject
    private EntityManager em;

    public List<Skaitytojas> loadAll() {
        return em.createQuery("SELECT s FROM Skaitytojas s", Skaitytojas.class)
                .getResultList();
    }

    public Skaitytojas findById(Long id) {
        return em.find(Skaitytojas.class, id);
    }

    @Transactional
    public void save(Skaitytojas skaitytojas) {
        em.persist(skaitytojas);
    }

    @Transactional
    public void update(Skaitytojas skaitytojas) {
        em.merge(skaitytojas);
    }

    @Transactional
    public void delete(Long id) {
        Skaitytojas skaitytojas = findById(id);
        if (skaitytojas != null) {
            em.remove(skaitytojas);
        }
    }
}