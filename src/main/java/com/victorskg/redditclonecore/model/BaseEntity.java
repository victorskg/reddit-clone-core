package com.victorskg.redditclonecore.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public abstract class BaseEntity<T extends Number> implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseEntity<T> that = (BaseEntity<T>) o;

        return Objects.equals(id, that.id);
    }

}
