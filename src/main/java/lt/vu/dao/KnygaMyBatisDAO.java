package lt.vu.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.mybatis.mappers.KnygaMapper;
import lt.vu.mybatis.model.KnygaMyBatis;
import java.util.List;

@ApplicationScoped
public class KnygaMyBatisDAO {

    @Inject
    private KnygaMapper knygaMapper;

    public List<KnygaMyBatis> loadAll() {
        return knygaMapper.loadAll();
    }

    public KnygaMyBatis findById(Long id) {
        return knygaMapper.findById(id);
    }

    @Transactional
    public void save(KnygaMyBatis knyga) {
        knygaMapper.insert(knyga);
    }
}