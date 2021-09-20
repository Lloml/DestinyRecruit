package cn.lloml.destinyrecruit.dto;



import java.io.Serializable;
import javax.validation.constraints.NotEmpty;


public class UserDTO implements Serializable {

    private Long id;

    @NotEmpty
    private String bungieName;

    private Long destinyMembershipId;

    private String token;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBungieName() {
        return bungieName;
    }

    public void setBungieName(String bungieName) {
        this.bungieName = bungieName;
    }

    public Long getDestinyMembershipId() {
        return destinyMembershipId;
    }

    public void setDestinyMembershipId(Long destinyMembershipId) {
        this.destinyMembershipId = destinyMembershipId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
