package lt.vu.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.mybatis.mappers.SkaitytojasMapper;
import lt.vu.mybatis.model.SkaitytojasMyBatis;
import java.util.List;

@ApplicationScoped
public class SkaitytojasMyBatisDAO {

    @Inject
    private SkaitytojasMapper skaitytojasMapper;

    public List<SkaitytojasMyBatis> loadAll() {
        return skaitytojasMapper.loadAll();
    }

    public SkaitytojasMyBatis findById(Long id) {
        return skaitytojasMapper.findById(id);
    }

    @Transactional
    public void save(SkaitytojasMyBatis skaitytojas) {
        skaitytojasMapper.insert(skaitytojas);
    }
}