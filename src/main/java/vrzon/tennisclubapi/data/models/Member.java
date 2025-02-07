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
public class Member extends BaseModel<Member> {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Override
    public void updateWith(Member updatingObject) {
        super.updateWithBase(updatingObject);
        this.name = updatingObject.getName() != null ? updatingObject.getName() : name;
        this.phoneNumber = updatingObject.getPhoneNumber() != null ? updatingObject.getPhoneNumber() : phoneNumber;
    }
}
