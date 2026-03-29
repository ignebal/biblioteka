package lt.vu.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class Resources {

    @Produces
    @PersistenceContext(unitName = "BibliotekaDS")
    private EntityManager entityManager;

}
