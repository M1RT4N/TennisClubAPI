package vrzon.tennisclubapi.controllers;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import vrzon.tennisclubapi.api.controllers.CourtController;
import vrzon.tennisclubapi.api.dtos.read.CourtReadDto;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.bases.CourtTestsBase;
import vrzon.tennisclubapi.facade.CourtFacade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourtControllerTests extends CourtTestsBase {

    @Mock
    private CourtFacade courtFacade;

    @InjectMocks
    private CourtController courtController;

    @Test
    void getAll_returnsAllCourts() {
        List<CourtReadDto> courts = List.of(readDto);
        Mockito.when(courtFacade.getAll()).thenReturn(courts);

        ResponseEntity<List<CourtReadDto>> response = courtController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courts, response.getBody());
    }

    @Test
    void getById_returnsCourt_whenIdExists() {
        Mockito.when(courtFacade.getById(id)).thenReturn(readDto);

        ResponseEntity<CourtReadDto> response = courtController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void getById_returnsNotFound_whenIdDoesNotExist() {
        Mockito.when(courtFacade.getById(id)).thenReturn(null);

        ResponseEntity<CourtReadDto> response = courtController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create_createsCourt() {
        Mockito.when(courtFacade.create(createDto)).thenReturn(readDto);

        ResponseEntity<CourtReadDto> response = courtController.create(createDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void update_updatesCourt() {
        Mockito.when(courtFacade.update(id, updateDto)).thenReturn(readDto);

        ResponseEntity<CourtReadDto> response = courtController.update(id, updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void delete_deletesCourt() {
        Mockito.when(courtFacade.delete(id)).thenReturn(readDto);

        ResponseEntity<CourtReadDto> response = courtController.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void getReservationsByCourtNumber_returnsReservations_whenNumberExists() {
        ReservationReadDto reservation = new ReservationReadDto();
        List<ReservationReadDto> reservations = List.of(reservation);

        Mockito.when(courtFacade.getReservationsByCourtNumber(courtNumber)).thenReturn(reservations);

        ResponseEntity<List<ReservationReadDto>> response = courtController.getReservationsByCourtNumber(courtNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
    }

    @Test
    void getReservationsByCourtNumber_returnsEmptyList_whenNoReservationsFound() {
        Mockito.when(courtFacade.getReservationsByCourtNumber(courtNumber)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ReservationReadDto>> response = courtController.getReservationsByCourtNumber(courtNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
}