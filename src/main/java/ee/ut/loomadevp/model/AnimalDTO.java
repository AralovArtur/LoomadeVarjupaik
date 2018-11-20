package ee.ut.loomadevp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class AnimalDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Max(45)
    @NotNull
    private String liik;

    @NotNull
    private int vanus;

    @Max(45)
    @NotNull
    private String linn;

    private MultipartFile avatarImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLiik() {
        return liik;
    }

    public void setLiik(String liik) {
        this.liik = liik;
    }

    public int getVanus() {
        return vanus;
    }

    public void setVanus(int vanus) {
        this.vanus = vanus;
    }

    public String getLinn() {
        return linn;
    }

    public void setLinn(String linn) {
        this.linn = linn;
    }

    @Override
    public String toString() {
        return "AnimalDTO{" +
                "liik='" + liik + '\'' +
                ", vanus=" + vanus +
                ", linn='" + linn + '\'' +
                '}';
    }
}
