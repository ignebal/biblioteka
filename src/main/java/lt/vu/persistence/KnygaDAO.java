package lt.vu.persistence;

import lt.vu.entities.Knyga;
import java.util.List;

public interface KnygaDAO {
    List<Knyga> loadAll();
    Knyga findById(Long id);
    void save(Knyga knyga);
    void update(Knyga knyga);
    void delete(Long id);
    List<Knyga> findByAutorius(Long autoriusId);
}

