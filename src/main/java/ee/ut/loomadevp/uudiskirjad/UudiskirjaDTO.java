package ee.ut.loomadevp.uudiskirjad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UudiskirjaDTO {
    private String email;
    private String sonum;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSonum() {
        return sonum;
    }

    public void setSonum(String sonum) {
        this.sonum = sonum;
    }
}
