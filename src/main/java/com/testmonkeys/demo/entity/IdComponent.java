package com.testmonkeys.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class IdComponent<T extends IdComponent> implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    // TODO SONAR-MAJOR never use SuppressWarnings
    //You better should omit object casting. Instead use instanceoff check and throw runtime exception if it fails
    @SuppressWarnings("unchecked")
    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }
}
