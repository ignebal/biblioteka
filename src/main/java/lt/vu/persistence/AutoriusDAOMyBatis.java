package lt.vu.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.entities.Autorius;
import lt.vu.entities.Knyga;
import lt.vu.mybatis.mappers.AutoriusMapper;
import lt.vu.mybatis.model.AutoriusMyBatis;
import lt.vu.mybatis.model.KnygaMyBatis;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Alternative
public class AutoriusDAOMyBatis implements AutoriusDAO {

    @Inject
    private AutoriusMapper autoriusMapper;

    @Override
    public List<Autorius> loadAll() {
        return autoriusMapper.loadAll()
                .stream()
                .map(this::convertToEntity)
                .toList();
    }

    @Override
    public Autorius findById(Long id) {
        AutoriusMyBatis myBatisModel = autoriusMapper.findById(id);
        return myBatisModel != null ? convertToEntity(myBatisModel) : null;
    }

    @Override
    @Transactional
    public void save(Autorius autorius) {
        AutoriusMyBatis myBatisModel = convertToMyBatis(autorius);
        autoriusMapper.insert(myBatisModel);
    }

    @Override
    @Transactional
    public void update(Autorius autorius) {
        save(autorius);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Not implemented
    }

    private Autorius convertToEntity(AutoriusMyBatis model) {
        Autorius entity = new Autorius();
        entity.setId(model.getId());
        entity.setVardas(model.getVardas());
        entity.setPavarde(model.getPavarde());

        if (model.getKnygos() != null && !model.getKnygos().isEmpty()) {
            List<Knyga> knygos = new ArrayList<>();
            for (Object knygaObj : model.getKnygos()) {
                if (knygaObj instanceof KnygaMyBatis) {
                    KnygaMyBatis knygaMyBatis = (KnygaMyBatis) knygaObj;
                    Knyga knyga = new Knyga();
                    knyga.setId(knygaMyBatis.getId());
                    knyga.setPavadinimas(knygaMyBatis.getPavadinimas());
                    knyga.setIsbn(knygaMyBatis.getIsbn());
                    knygos.add(knyga);
                }
            }
            entity.setKnygos(knygos);
        }

        return entity;
    }

    private AutoriusMyBatis convertToMyBatis(Autorius entity) {
        AutoriusMyBatis model = new AutoriusMyBatis();
        model.setId(entity.getId());
        model.setVardas(entity.getVardas());
        model.setPavarde(entity.getPavarde());
        return model;
    }
}