package lt.vu.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.entities.Knyga;
import lt.vu.entities.Skaitytojas;
import lt.vu.mybatis.mappers.SkaitytojasMapper;
import lt.vu.mybatis.model.KnygaMyBatis;
import lt.vu.mybatis.model.SkaitytojasMyBatis;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Alternative
public class SkaitytojasDAOMyBatis implements SkaitytojasDAO {

    @Inject
    private SkaitytojasMapper skaitytojasMapper;

    @Override
    public List<Skaitytojas> loadAll() {
        return skaitytojasMapper.loadAll()
                .stream()
                .map(this::convertToEntity)
                .toList();
    }

    @Override
    public Skaitytojas findById(Long id) {
        SkaitytojasMyBatis myBatisModel = skaitytojasMapper.findById(id);
        return myBatisModel != null ? convertToEntity(myBatisModel) : null;
    }

    @Override
    @Transactional
    public void save(Skaitytojas skaitytojas) {
        SkaitytojasMyBatis myBatisModel = convertToMyBatis(skaitytojas);

        skaitytojasMapper.insert(myBatisModel);

        Long skaitytojasId = myBatisModel.getId();

        if (skaitytojas.getKnygos() != null && !skaitytojas.getKnygos().isEmpty()) {
            for (Knyga knyga : skaitytojas.getKnygos()) {
                if (knyga != null && knyga.getId() != null) {
                    skaitytojasMapper.insertSkaitytojasKnyga(skaitytojasId, knyga.getId());
                }
            }
        }
    }

    @Override
    @Transactional
    public void update(Skaitytojas skaitytojas) {
        // Delete existing relationships
        if (skaitytojas.getId() != null) {
            skaitytojasMapper.deleteSkaitytojasKnygaByReader(skaitytojas.getId());
        }

        // Save with new relationships
        save(skaitytojas);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Not implemented in original
    }

    private Skaitytojas convertToEntity(SkaitytojasMyBatis model) {
        Skaitytojas entity = new Skaitytojas();
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

    private SkaitytojasMyBatis convertToMyBatis(Skaitytojas entity) {
        SkaitytojasMyBatis model = new SkaitytojasMyBatis();
        model.setId(entity.getId());
        model.setVardas(entity.getVardas());
        model.setPavarde(entity.getPavarde());
        return model;
    }
}