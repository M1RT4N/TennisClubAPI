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
public class Surface extends BaseModel<Surface> {
    @Column(nullable = false, unique = true)
    private String typeName;

    @Column(nullable = false)
    private Float minutePrice;

    @Override
    public void updateWith(Surface updatingObject) {
        super.updateWithBase(updatingObject);
        this.typeName = updatingObject.typeName != null ? updatingObject.typeName : typeName;
        this.minutePrice = updatingObject.minutePrice != null ? updatingObject.minutePrice : minutePrice;
    }
}
