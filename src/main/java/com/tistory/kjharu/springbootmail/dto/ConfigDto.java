package com.tistory.kjharu.springbootmail.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configures", schema = "kjharu")
public class ConfigDto {

    @Id
    private String key;

    @Column
    private String value;

    public String getValue() {
        return value;
    }
}
