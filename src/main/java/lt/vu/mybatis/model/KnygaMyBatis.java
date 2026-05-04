package lt.vu.mybatis.model;

public class KnygaMyBatis {
    private Long id;
    private String pavadinimas;
    private String isbn;
    private Long autoriusId;
    private AutoriusMyBatis autorius;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPavadinimas() { return pavadinimas; }
    public void setPavadinimas(String pavadinimas) { this.pavadinimas = pavadinimas; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Long getAutoriusId() { return autoriusId; }
    public void setAutoriusId(Long autoriusId) { this.autoriusId = autoriusId; }

    public AutoriusMyBatis getAutorius() { return autorius; }
    public void setAutorius(AutoriusMyBatis autorius) { this.autorius = autorius; }
}