package lt.vu.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.mybatis.mappers.AutoriusMapper;
import lt.vu.mybatis.model.AutoriusMyBatis;
import java.util.List;

@ApplicationScoped
public class AutoriusMyBatisDAO {

    @Inject
    private AutoriusMapper autoriusMapper;

    public List<AutoriusMyBatis> loadAll() {
        return autoriusMapper.loadAll();
    }

    public AutoriusMyBatis findById(Long id) {
        return autoriusMapper.findById(id);
    }

    @Transactional
    public void save(AutoriusMyBatis autorius) {
        autoriusMapper.insert(autorius);
    }
}