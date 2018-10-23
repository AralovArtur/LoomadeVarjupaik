package ee.ut.loomadevp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Getter;
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


    @Override
    public String toString() {
        return "liik='" + liik + '\'' +
                ", vanus=" + vanus +
                ", linn='" + linn + '\'';
    }
}
