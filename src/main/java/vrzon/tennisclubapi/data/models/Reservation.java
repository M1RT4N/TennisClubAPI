package vrzon.tennisclubapi.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseModel<Reservation> {
    @ManyToOne
    @JoinColumn(name = "courtId")
    private Court court;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(nullable = false)
    private Float totalPrice;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false, name = "end_time")
    private LocalDateTime end;

    @Column(nullable = false)
    private Boolean doublesGame;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Override
    public void updateWith(Reservation updatingObject) {
        super.updateWithBase(updatingObject);
        this.court = updatingObject.getCourt() != null ? updatingObject.getCourt() : this.court;
        this.member = updatingObject.getMember() != null ? updatingObject.getMember() : this.member;
        this.totalPrice = updatingObject.getTotalPrice() != null ? updatingObject.getTotalPrice() : this.totalPrice;
        this.start = updatingObject.getStart() != null ? updatingObject.getStart() : this.start;
        this.end = updatingObject.getEnd() != null ? updatingObject.getEnd() : this.end;
        this.doublesGame = updatingObject.getDoublesGame() != null ? updatingObject.getDoublesGame() : this.doublesGame;
        this.createdAt = updatingObject.getCreatedAt() != null ? updatingObject.getCreatedAt() : this.createdAt;
    }
}
