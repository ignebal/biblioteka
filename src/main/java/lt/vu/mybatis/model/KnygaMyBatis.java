package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KnygaMyBatis {
    private Long id;
    private String pavadinimas;
    private String isbn;
    private Long autoriusId;
    private AutoriusMyBatis autorius;
}