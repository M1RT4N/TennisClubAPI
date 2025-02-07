package vrzon.tennisclubapi.data.repositories;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.data.models.Member;
import vrzon.tennisclubapi.data.models.Reservation;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationRepository extends RepositoryBase<Reservation> {

    public ReservationRepository() {
        super(Reservation.class);
    }

    public List<Reservation> getReservationsByReservationNumber(long number) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(Reservation.class);
        Root<Reservation> reservationRoot = cq.from(Reservation.class);

        Join<Reservation, Court> courtJoin = reservationRoot.join("court");

        cq.select(reservationRoot)
                .where(cb.equal(courtJoin.get("number"), number))
                .orderBy(cb.desc(reservationRoot.get("createdAt")));

        TypedQuery<Reservation> query = entityManager.createQuery(cq);
        return query.getResultList();
    }


    public List<Reservation> getMemberReservations(Member member, boolean futureOnly) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(modelClass);
        Root<Reservation> reservationRoot = cq.from(modelClass);

        cq = cq.select(reservationRoot).where(cb.equal(reservationRoot.get("member"), member));
        if (futureOnly) {
            cq = cq.where(cb.greaterThanOrEqualTo(reservationRoot.get("start"), LocalDateTime.now()));
        }
        cq = cq.orderBy(cb.desc(reservationRoot.get("start")));

        TypedQuery<Reservation> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    public boolean isReservationClash(Reservation reservation) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> cq = cb.createQuery(modelClass);
        Root<Reservation> reservationRoot = cq.from(modelClass);

        cq.select(reservationRoot).where(
                cb.and(
                        cb.lessThan(reservationRoot.get("start"), reservation.getEnd()),
                        cb.lessThan(cb.literal(reservation.getStart()), reservationRoot.get("end"))
                )
        );

        TypedQuery<Reservation> query = entityManager.createQuery(cq);
        query.setMaxResults(1);

        return !query.getResultList().isEmpty();
    }
}
