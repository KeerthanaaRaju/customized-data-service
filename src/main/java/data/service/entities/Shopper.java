package data.service.entities;

import lombok.Data;

import org.springframework.data.domain.Persistable;
import javax.persistence.*;

@Entity
@Table(name = "shoppers")
@Data
public class Shopper implements Persistable<String>
{
    @Id
    @Column(name = "shopperid")
    private String shopperId;

    private String name;

    @Transient
    private boolean update;

    @Override
    public boolean isNew() {
        return !this.update;
    }

    @Override
    public String getId()
    {
        return this.shopperId;
    }

    @PrePersist
    @PostLoad
    void markUpdated() {
        this.update = true;
    }
}