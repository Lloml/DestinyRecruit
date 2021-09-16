package cn.lloml.destinyrecruit.domain;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class User implements Serializable {


    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String bungieName;

    @NotNull
    private Long destinyMembershipId;

    private String toekn;

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

    public String getToekn() {
        return toekn;
    }

    public void setToekn(String toekn) {
        this.toekn = toekn;
    }
}