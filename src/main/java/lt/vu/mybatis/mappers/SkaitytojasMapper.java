package lt.vu.mybatis.mappers;

import lt.vu.mybatis.model.SkaitytojasMyBatis;
import org.mybatis.cdi.Mapper;
import java.util.List;

@Mapper
public interface SkaitytojasMapper {
    List<SkaitytojasMyBatis> loadAll();
    SkaitytojasMyBatis findById(Long id);
    void insert(SkaitytojasMyBatis skaitytojas);
}