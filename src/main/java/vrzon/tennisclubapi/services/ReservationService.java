package vrzon.tennisclubapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.data.models.Member;
import vrzon.tennisclubapi.data.models.Reservation;
import vrzon.tennisclubapi.data.repositories.CourtRepository;
import vrzon.tennisclubapi.data.repositories.MemberRepository;
import vrzon.tennisclubapi.data.repositories.ReservationRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService extends ServiceBase<Reservation> {
    private static final float DOUBLES_MULTIPLIER = 1.5f;
    private final CourtRepository courtRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ReservationService(ReservationRepository repository, CourtRepository courtRepository, MemberRepository memberRepository) {
        super(repository);
        this.courtRepository = courtRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public Reservation create(Reservation reservation) {
        Court court = courtRepository.findCourtByNumber(reservation.getCourt().getNumber());
        if (court == null || court.getDeleted()) {
            return null;
        }

        Member reservationMember = reservation.getMember();
        Member storedMember = memberRepository.findByPhoneNumber(reservationMember.getPhoneNumber());
        if (storedMember == null || storedMember.getDeleted()) {
            storedMember = memberRepository.save(reservationMember);
        }

        reservation.setCourt(court);
        reservation.setMember(storedMember);
        float totalPrice = calculateTotalPrice(reservation);
        reservation.setTotalPrice(totalPrice);
        reservation.setCreatedAt(LocalDateTime.now());

        if (((ReservationRepository) repository).isReservationClash(reservation)) {
            return null;
        }

        return repository.save(reservation);
    }

    public List<Reservation> getReservationsByCourtNumber(long number) {
        return ((ReservationRepository) repository).getReservationsByReservationNumber(number);
    }

    public List<Reservation> getMemberReservations(String phoneNumber, boolean futureOnly) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber);
        if (member == null) {
            return null;
        }

        return ((ReservationRepository) repository).getMemberReservations(member, futureOnly);
    }

    private float calculateTotalPrice(Reservation reservation) {
        float minutePrice = reservation.getCourt().getSurface().getMinutePrice();
        long durationMinutes = Duration.between(reservation.getStart(), reservation.getEnd()).toMinutes();
        float totalPrice = durationMinutes * minutePrice;
        if (reservation.getDoublesGame()) {
            totalPrice *= DOUBLES_MULTIPLIER;
        }

        return totalPrice;
    }
}
