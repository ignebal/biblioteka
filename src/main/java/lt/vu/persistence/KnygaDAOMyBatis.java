package lt.vu.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.entities.Autorius;
import lt.vu.entities.Knyga;
import lt.vu.mybatis.mappers.KnygaMapper;
import lt.vu.mybatis.model.AutoriusMyBatis;
import lt.vu.mybatis.model.KnygaMyBatis;
import java.util.List;

@ApplicationScoped
@Alternative
public class KnygaDAOMyBatis implements KnygaDAO {

    @Inject
    private KnygaMapper knygaMapper;

    @Override
    public List<Knyga> loadAll() {
        return knygaMapper.loadAll()
                .stream()
                .map(this::convertToEntity)
                .toList();
    }

    @Override
    public Knyga findById(Long id) {
        KnygaMyBatis myBatisModel = knygaMapper.findById(id);
        return myBatisModel != null ? convertToEntity(myBatisModel) : null;
    }

    @Override
    @Transactional
    public void save(Knyga knyga) {
        KnygaMyBatis myBatisModel = convertToMyBatis(knyga);
        knygaMapper.insert(myBatisModel);
    }

    @Override
    @Transactional
    public void update(Knyga knyga) {
        save(knyga);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Not implemented in original
    }

    @Override
    public List<Knyga> findByAutorius(Long autoriusId) {
        return knygaMapper.findByAutorius(autoriusId)  // Changed method name!
                .stream()
                .map(this::convertToEntity)
                .toList();
    }

    private Knyga convertToEntity(KnygaMyBatis model) {
        Knyga entity = new Knyga();
        entity.setId(model.getId());
        entity.setPavadinimas(model.getPavadinimas());
        entity.setIsbn(model.getIsbn());

        if (model.getAutorius() != null) {
            Autorius autorius = new Autorius();
            autorius.setId(model.getAutorius().getId());
            autorius.setVardas(model.getAutorius().getVardas());
            autorius.setPavarde(model.getAutorius().getPavarde());
            entity.setAutorius(autorius);
        }

        return entity;
    }

    private KnygaMyBatis convertToMyBatis(Knyga entity) {
        KnygaMyBatis model = new KnygaMyBatis();
        model.setId(entity.getId());
        model.setPavadinimas(entity.getPavadinimas());
        model.setIsbn(entity.getIsbn());

        if (entity.getAutorius() != null) {
            AutoriusMyBatis autoriusModel = new AutoriusMyBatis();
            autoriusModel.setId(entity.getAutorius().getId());
            autoriusModel.setVardas(entity.getAutorius().getVardas());
            autoriusModel.setPavarde(entity.getAutorius().getPavarde());
            model.setAutorius(autoriusModel);
            model.setAutoriusId(entity.getAutorius().getId());
        }

        return model;
    }
}

