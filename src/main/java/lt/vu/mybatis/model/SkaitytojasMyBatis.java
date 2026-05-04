package lt.vu.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class SkaitytojasMyBatis {
    private Long id;
    private String vardas;
    private String pavarde;
    private List<Object> knygos = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }
    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }
    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public List<Object> getKnygos() {
        return knygos;
    }
    public void setKnygos(List<Object> knygos) {
        this.knygos = knygos;
    }
}