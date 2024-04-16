package data.service.entities;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name="products")
@Data
public class Product  implements Persistable<String>
{
    @Id
    @Column(name = "productid")
    private String productId;
    private String category;

    private String brand;

    @Transient
    private boolean update;

    @Override
    public boolean isNew() {
        return !this.update;
    }

    @Override
    public String getId()
    {
        return this.getProductId();
    }

    @PrePersist
    @PostLoad
    void markUpdated() {
        this.update = true;
    }


}