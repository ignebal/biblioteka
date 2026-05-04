package lt.vu.persistence;

import lt.vu.entities.Skaitytojas;
import java.util.List;

public interface SkaitytojasDAO {
    List<Skaitytojas> loadAll();
    Skaitytojas findById(Long id);
    void save(Skaitytojas skaitytojas);
    void update(Skaitytojas skaitytojas);
    void delete(Long id);
}

