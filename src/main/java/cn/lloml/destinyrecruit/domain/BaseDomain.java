package cn.lloml.destinyrecruit.domain;

import java.io.Serializable;

public class BaseDomain implements Serializable {

    private Long id;


    private String idStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdStr() {
        return id.toString();
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
        this.id = Long.valueOf(idStr);
    }
}
