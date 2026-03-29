package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class SkaitytojasMyBatis {
    private Long id;
    private String vardas;
    private String pavarde;
    private List<KnygaMyBatis> knygos;
}