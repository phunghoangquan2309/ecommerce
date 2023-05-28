package com.example.demo.entity.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class Persistable<PK extends Serializable> implements Serializable , org.springframework.data.domain.Persistable<PK> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected PK id;

    @Transient
    public boolean isNew() {
        return null == getId();
    }
}
