package vrzon.tennisclubapi.data.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel<T extends BaseModel<T>> implements Updatable<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected Boolean deleted = false;

    public void updateWithBase(BaseModel<T> updatingObject) {
        this.id = updatingObject.getId() != null ? updatingObject.getId() : this.id;
        this.deleted = updatingObject.getDeleted() != null ? updatingObject.getDeleted() : this.deleted;
    }
}
