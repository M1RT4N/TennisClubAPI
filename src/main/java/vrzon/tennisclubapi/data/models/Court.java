package vrzon.tennisclubapi.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Court extends BaseModel<Court> {

    @Column(nullable = false, unique = true)
    private Long number;

    @ManyToOne
    @JoinColumn(nullable = false, name = "surfaceId")
    private Surface surface;

    @Override
    public void updateWith(Court updatingObject) {
        super.updateWithBase(updatingObject);
        this.number = updatingObject.getNumber() != null ? updatingObject.getNumber() : this.number;
        this.surface = updatingObject.getSurface() != null ? updatingObject.getSurface() : this.surface;
    }
}
