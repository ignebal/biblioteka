package lt.vu.mybatis.mappers;

import lt.vu.mybatis.model.KnygaMyBatis;
import org.mybatis.cdi.Mapper;
import java.util.List;

@Mapper
public interface KnygaMapper {
    List<KnygaMyBatis> loadAll();
    KnygaMyBatis findById(Long id);
    void insert(KnygaMyBatis knyga);
    List<KnygaMyBatis> findBySkaitytojas(Long skaitytojasId);
}