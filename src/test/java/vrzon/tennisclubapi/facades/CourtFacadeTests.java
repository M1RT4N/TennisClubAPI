package vrzon.tennisclubapi.facades;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import vrzon.tennisclubapi.api.dtos.read.CourtReadDto;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.bases.CourtTestsBase;
import vrzon.tennisclubapi.data.models.Reservation;
import vrzon.tennisclubapi.facade.CourtFacade;
import vrzon.tennisclubapi.facade.mappers.CourtMapper;
import vrzon.tennisclubapi.facade.mappers.ReservationMapper;
import vrzon.tennisclubapi.services.CourtService;
import vrzon.tennisclubapi.services.ReservationService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CourtFacadeTests extends CourtTestsBase {

    @Mock
    private CourtService courtService;

    @Mock
    private CourtMapper courtMapper;

    @Mock
    private ReservationService reservationService;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private CourtFacade courtFacade;

    @Test
    void create_returnsCourtDto_whenValid() {
        when(courtMapper.mapCreateDtoToModel(createDto)).thenReturn(court);
        when(courtService.create(court)).thenReturn(court);
        when(courtMapper.mapModelToDto(court)).thenReturn(readDto);

        CourtReadDto createdCourtDto = courtFacade.create(createDto);

        assertNotNull(createdCourtDto);
        assertEquals(readDto, createdCourtDto);
    }

    @Test
    void create_returnsNull_whenServiceReturnsNull() {
        when(courtMapper.mapCreateDtoToModel(createDto)).thenReturn(court);
        when(courtService.create(court)).thenReturn(null);

        CourtReadDto createdCourtDto = courtFacade.create(createDto);

        assertNull(createdCourtDto);
    }

    @Test
    void getById_returnsCourtDto_whenIdExists() {
        when(courtService.getById(id)).thenReturn(court);
        when(courtMapper.mapModelToDto(court)).thenReturn(readDto);

        CourtReadDto foundCourtDto = courtFacade.getById(id);

        assertNotNull(foundCourtDto);
        assertEquals(readDto, foundCourtDto);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        when(courtService.getById(id)).thenReturn(null);

        CourtReadDto foundCourtDto = courtFacade.getById(id);

        assertNull(foundCourtDto);
    }

    @Test
    void getAll_returnsAllCourtDtos() {
        when(courtService.getAll()).thenReturn(Collections.singletonList(court));
        when(courtMapper.mapModelsToDtoList(Collections.singletonList(court))).thenReturn(Collections.singletonList(readDto));

        List<CourtReadDto> courtDtos = courtFacade.getAll();

        assertNotNull(courtDtos);
        assertEquals(1, courtDtos.size());
        assertEquals(readDto, courtDtos.getFirst());
    }

    @Test
    void update_returnsUpdatedCourtDto_whenIdExists() {
        when(courtMapper.mapCreateDtoToModel(updateDto)).thenReturn(court);
        when(courtService.update(id, court)).thenReturn(court);
        when(courtMapper.mapModelToDto(court)).thenReturn(readDto);

        CourtReadDto updatedCourtDto = courtFacade.update(id, updateDto);

        assertNotNull(updatedCourtDto);
        assertEquals(readDto, updatedCourtDto);
    }

    @Test
    void update_returnsNull_whenIdDoesNotExist() {
        when(courtMapper.mapCreateDtoToModel(updateDto)).thenReturn(court);
        when(courtService.update(id, court)).thenReturn(null);

        CourtReadDto updatedCourtDto = courtFacade.update(id, updateDto);

        assertNull(updatedCourtDto);
    }

    @Test
    void delete_returnsDeletedCourtDto_whenIdExists() {
        when(courtService.delete(id)).thenReturn(court);
        when(courtMapper.mapModelToDto(court)).thenReturn(readDto);

        CourtReadDto deletedCourtDto = courtFacade.delete(id);

        assertNotNull(deletedCourtDto);
        assertEquals(readDto, deletedCourtDto);
    }

    @Test
    void delete_returnsNull_whenIdDoesNotExist() {
        when(courtService.delete(id)).thenReturn(null);

        CourtReadDto deletedCourtDto = courtFacade.delete(id);

        assertNull(deletedCourtDto);
    }

    @Test
    void getReservationsByCourtNumber_returnsReservations_whenValid() {
        Reservation reservation = new Reservation();
        ReservationReadDto reservationReadDto = new ReservationReadDto();

        when(reservationService.getReservationsByCourtNumber(courtNumber)).thenReturn(Collections.singletonList(reservation));
        when(reservationMapper.mapModelsToDtoList(Collections.singletonList(reservation))).thenReturn(Collections.singletonList(reservationReadDto));

        List<ReservationReadDto> reservations = courtFacade.getReservationsByCourtNumber(courtNumber);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
        assertEquals(reservationReadDto, reservations.getFirst());
    }

    @Test
    void getReservationsByCourtNumber_returnsEmptyList_whenNoReservations() {
        when(reservationService.getReservationsByCourtNumber(courtNumber)).thenReturn(Collections.emptyList());
        when(reservationMapper.mapModelsToDtoList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<ReservationReadDto> reservations = courtFacade.getReservationsByCourtNumber(courtNumber);

        assertNotNull(reservations);
        assertTrue(reservations.isEmpty());
    }
}