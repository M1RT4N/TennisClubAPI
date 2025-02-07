package vrzon.tennisclubapi.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import vrzon.tennisclubapi.bases.ReservationTestsBase;
import vrzon.tennisclubapi.data.models.Reservation;
import vrzon.tennisclubapi.data.repositories.CourtRepository;
import vrzon.tennisclubapi.data.repositories.MemberRepository;
import vrzon.tennisclubapi.data.repositories.ReservationRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ReservationServiceTests extends ReservationTestsBase {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CourtRepository courtRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void create_returnsReservation_whenValid() {
        when(courtRepository.findCourtByNumber(reservation.getCourt().getNumber())).thenReturn(court);
        when(memberRepository.findByPhoneNumber(reservation.getMember().getPhoneNumber())).thenReturn(member);
        when(reservationRepository.isReservationClash(reservation)).thenReturn(false);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation createdReservation = reservationService.create(reservation);

        assertNotNull(createdReservation);
        assertEquals(court, createdReservation.getCourt());
        assertEquals(member, createdReservation.getMember());
    }

    @Test
    void create_returnsNull_whenCourtDeleted() {
        court.setDeleted(true);
        when(courtRepository.findCourtByNumber(reservation.getCourt().getNumber())).thenReturn(court);

        Reservation createdReservation = reservationService.create(reservation);

        assertNull(createdReservation);
    }

    @Test
    void create_returnsNull_whenReservationClashes() {
        when(courtRepository.findCourtByNumber(reservation.getCourt().getNumber())).thenReturn(court);
        when(memberRepository.findByPhoneNumber(reservation.getMember().getPhoneNumber())).thenReturn(member);
        when(reservationRepository.isReservationClash(reservation)).thenReturn(true);

        Reservation createdReservation = reservationService.create(reservation);

        assertNull(createdReservation);
    }

    @Test
    void getReservationsByCourtNumber_returnsReservations_whenValid() {
        when(reservationRepository.getReservationsByReservationNumber(1L)).thenReturn(Collections.singletonList(reservation));

        List<Reservation> reservations = reservationService.getReservationsByCourtNumber(1L);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
        assertEquals(court, reservations.getFirst().getCourt());
    }

    @Test
    void getReservationsByCourtNumber_returnsEmptyList_whenNoReservations() {
        when(reservationRepository.getReservationsByReservationNumber(1L)).thenReturn(Collections.emptyList());

        List<Reservation> reservations = reservationService.getReservationsByCourtNumber(1L);

        assertNotNull(reservations);
        assertTrue(reservations.isEmpty());
    }

    @Test
    void getMemberReservations_returnsReservations_whenValid() {
        when(memberRepository.findByPhoneNumber(reservation.getMember().getPhoneNumber())).thenReturn(member);
        when(reservationRepository.getMemberReservations(member, true)).thenReturn(Collections.singletonList(reservation));

        List<Reservation> reservations = reservationService.getMemberReservations(reservation.getMember().getPhoneNumber(), true);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
        assertEquals(member, reservations.getFirst().getMember());
    }

    @Test
    void getMemberReservations_returnsNull_whenMemberNotFound() {
        when(memberRepository.findByPhoneNumber(reservation.getMember().getPhoneNumber())).thenReturn(null);

        List<Reservation> reservations = reservationService.getMemberReservations(reservation.getMember().getPhoneNumber(), true);

        assertNull(reservations);
    }

    @Test
    void getAll_returnsAllReservations() {
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));

        List<Reservation> reservations = reservationService.getAll();

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    void getById_returnsReservation_whenIdExists() {
        when(reservationRepository.findById(1L)).thenReturn(reservation);

        Reservation foundReservation = reservationService.getById(1L);

        assertNotNull(foundReservation);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        when(reservationRepository.findById(1L)).thenReturn(null);

        Reservation foundReservation = reservationService.getById(1L);

        assertNull(foundReservation);
    }

    @Test
    void update_returnsUpdatedReservation_whenIdExists() {
        Reservation updatedReservation = new Reservation();
        updatedReservation.setTotalPrice(200f);

        when(reservationRepository.findById(1L)).thenReturn(reservation);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.update(1L, updatedReservation);

        assertNotNull(result);
        assertEquals(200f, result.getTotalPrice());
    }

    @Test
    void update_returnsNull_whenIdDoesNotExist() {
        Reservation updatedReservation = new Reservation();
        when(reservationRepository.findById(1L)).thenReturn(null);

        Reservation result = reservationService.update(1L, updatedReservation);

        assertNull(result);
    }

    @Test
    void delete_returnsDeletedReservation_whenIdExists() {
        when(reservationRepository.findById(1L)).thenReturn(reservation);
        when(reservationRepository.delete(reservation)).thenReturn(reservation);

        Reservation result = reservationService.delete(1L);

        assertNotNull(result);
        assertTrue(result.getDeleted());
    }

    @Test
    void delete_returnsNull_whenIdDoesNotExist() {
        when(reservationRepository.findById(1L)).thenReturn(null);

        Reservation result = reservationService.delete(1L);

        assertNull(result);
    }
}