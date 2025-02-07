package vrzon.tennisclubapi.controllers;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.bases.ReservationTestsBase;
import vrzon.tennisclubapi.api.controllers.ReservationController;
import vrzon.tennisclubapi.facade.ReservationFacade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationControllerTests extends ReservationTestsBase {

    @Mock
    private ReservationFacade reservationFacade;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    void getAll_returnsAllReservations() {
        List<ReservationReadDto> reservations = List.of(readDto);
        Mockito.when(reservationFacade.getAll()).thenReturn(reservations);

        ResponseEntity<List<ReservationReadDto>> response = reservationController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
    }

    @Test
    void getById_returnsReservation_whenIdExists() {
        Mockito.when(reservationFacade.getById(id)).thenReturn(readDto);

        ResponseEntity<ReservationReadDto> response = reservationController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void getById_returnsNotFound_whenIdDoesNotExist() {
        Mockito.when(reservationFacade.getById(id)).thenReturn(null);

        ResponseEntity<ReservationReadDto> response = reservationController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create_createsReservation() {
        Mockito.when(reservationFacade.create(createDto)).thenReturn(readDto);

        ResponseEntity<ReservationReadDto> response = reservationController.create(createDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void update_updatesReservation() {
        Mockito.when(reservationFacade.update(id, updateDto)).thenReturn(readDto);

        ResponseEntity<ReservationReadDto> response = reservationController.update(id, updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void delete_deletesReservation() {
        Mockito.when(reservationFacade.delete(id)).thenReturn(readDto);

        ResponseEntity<ReservationReadDto> response = reservationController.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void getReservationsByPhoneNumber_returnsReservations_whenPhoneNumberExists() {

        List<ReservationReadDto> reservations = List.of(readDto);
        Mockito.when(reservationFacade.getReservationsByPhoneNumber(phoneNumber, futureOnly)).thenReturn(reservations);

        ResponseEntity<List<ReservationReadDto>> response = reservationController.getReservationsByPhoneNumber(phoneNumber, futureOnly);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
    }

    @Test
    void getReservationsByPhoneNumber_returnsEmptyList_whenNoReservationsFound() {
        Mockito.when(reservationFacade.getReservationsByPhoneNumber(phoneNumber, futureOnly)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ReservationReadDto>> response = reservationController.getReservationsByPhoneNumber(phoneNumber, futureOnly);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
}