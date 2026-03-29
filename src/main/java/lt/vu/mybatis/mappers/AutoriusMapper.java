package lt.vu.mybatis.mappers;

import lt.vu.mybatis.model.AutoriusMyBatis;
import org.mybatis.cdi.Mapper;
import java.util.List;

@Mapper
public interface AutoriusMapper {
    List<AutoriusMyBatis> loadAll();
    AutoriusMyBatis findById(Long id);
    void insert(AutoriusMyBatis autorius);
}