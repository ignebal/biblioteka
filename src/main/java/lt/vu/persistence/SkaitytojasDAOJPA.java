package lt.vu.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lt.vu.entities.Knyga;
import lt.vu.entities.Skaitytojas;
import java.util.List;

@ApplicationScoped
public class SkaitytojasDAOJPA implements SkaitytojasDAO {

    @Inject
    private EntityManager em;

    @Override
    public List<Skaitytojas> loadAll() {
        return em.createQuery(
                "SELECT DISTINCT s FROM Skaitytojas s LEFT JOIN FETCH s.knygos",
                Skaitytojas.class)
                .getResultList();
    }

    @Override
    public Skaitytojas findById(Long id) {
        return em.find(Skaitytojas.class, id);
    }

    @Override
    @Transactional
    public void save(Skaitytojas skaitytojas) {
        em.persist(skaitytojas);
        // Ensure all associated books are also updated if they have changes
        if (skaitytojas.getKnygos() != null) {
            for (Knyga knyga : skaitytojas.getKnygos()) {
                if (knyga != null) {
                    em.merge(knyga);
                }
            }
        }
        em.flush();
    }

    @Override
    @Transactional
    public void update(Skaitytojas skaitytojas) {
        em.merge(skaitytojas);
        em.flush();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Skaitytojas skaitytojas = findById(id);
        if (skaitytojas != null) {
            em.remove(skaitytojas);
        }
    }
}

