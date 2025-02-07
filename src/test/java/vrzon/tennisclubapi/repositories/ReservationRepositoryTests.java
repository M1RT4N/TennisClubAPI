package vrzon.tennisclubapi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import vrzon.tennisclubapi.bases.ReservationTestsBase;
import vrzon.tennisclubapi.data.models.Reservation;
import vrzon.tennisclubapi.data.repositories.ReservationRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.datasource.seedOnStart=false")
public class ReservationRepositoryTests extends ReservationTestsBase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void seed() {
        entityManager.persist(court.getSurface());
        entityManager.persist(court);
        entityManager.persist(member);
    }

    @Test
    void save_whenReservationIsValid_savesAndReturnsReservation() {
        Reservation savedReservation = reservationRepository.save(reservation);

        assertThat(savedReservation).isEqualTo(reservation);
    }

    @Test
    void save_whenReservationIsInvalid_throwsException() {
        Reservation reservation = new Reservation();

        assertThrows(DataIntegrityViolationException.class, () -> reservationRepository.save(reservation));
    }

    @Test
    void findById_whenReservationExists_returnsReservation() {
        entityManager.persist(reservation);

        Reservation foundReservation = reservationRepository.findById(reservation.getId());

        assertThat(foundReservation).isEqualTo(reservation);
    }

    @Test
    void findById_whenReservationDoesNotExist_returnsNull() {
        Reservation foundReservation = reservationRepository.findById(-1L);

        assertThat(foundReservation).isNull();
    }

    @Test
    void findAll_whenReservationsExist_returnsReservations() {
        entityManager.persist(reservation);

        List<Reservation> foundReservations = reservationRepository.findAll();

        assertThat(foundReservations).hasSize(1).contains(reservation);
    }

    @Test
    void findAll_whenNoReservationsExist_returnsEmptyList() {
        List<Reservation> foundReservations = reservationRepository.findAll();

        assertThat(foundReservations).isEmpty();
    }

    @Test
    void delete_whenReservationExists_deletesReservation() {
        entityManager.persist(reservation);

        Reservation deletedReservation = reservationRepository.delete(reservation);

        assertThat(deletedReservation).isNotNull();
        assertThat(deletedReservation.getDeleted()).isTrue();
    }
}