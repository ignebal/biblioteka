package lt.vu.persistence;

import lt.vu.entities.Autorius;
import java.util.List;

public interface AutoriusDAO {
    List<Autorius> loadAll();
    Autorius findById(Long id);
    void save(Autorius autorius);
    void update(Autorius autorius);
    void delete(Long id);
}

