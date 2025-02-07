package vrzon.tennisclubapi.facades;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.bases.ReservationTestsBase;
import vrzon.tennisclubapi.facade.ReservationFacade;
import vrzon.tennisclubapi.facade.mappers.ReservationMapper;
import vrzon.tennisclubapi.services.ReservationService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ReservationFacadeTests extends ReservationTestsBase {

    @Mock
    private ReservationService reservationService;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationFacade reservationFacade;

    @Test
    void create_returnsReservationDto_whenValid() {
        when(reservationMapper.mapCreateDtoToModel(createDto)).thenReturn(reservation);
        when(reservationService.create(reservation)).thenReturn(reservation);
        when(reservationMapper.mapModelToDto(reservation)).thenReturn(readDto);

        ReservationReadDto createdReservationDto = reservationFacade.create(createDto);

        assertNotNull(createdReservationDto);
        assertEquals(readDto, createdReservationDto);
    }

    @Test
    void create_returnsNull_whenServiceReturnsNull() {
        when(reservationMapper.mapCreateDtoToModel(createDto)).thenReturn(reservation);
        when(reservationService.create(reservation)).thenReturn(null);

        ReservationReadDto createdReservationDto = reservationFacade.create(createDto);

        assertNull(createdReservationDto);
    }

    @Test
    void getById_returnsReservationDto_whenIdExists() {
        when(reservationService.getById(id)).thenReturn(reservation);
        when(reservationMapper.mapModelToDto(reservation)).thenReturn(readDto);

        ReservationReadDto foundReservationDto = reservationFacade.getById(id);

        assertNotNull(foundReservationDto);
        assertEquals(readDto, foundReservationDto);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        when(reservationService.getById(id)).thenReturn(null);

        ReservationReadDto foundReservationDto = reservationFacade.getById(id);

        assertNull(foundReservationDto);
    }

    @Test
    void getAll_returnsAllReservationDtos() {
        when(reservationService.getAll()).thenReturn(Collections.singletonList(reservation));
        when(reservationMapper.mapModelsToDtoList(Collections.singletonList(reservation))).thenReturn(Collections.singletonList(readDto));

        List<ReservationReadDto> reservationDtos = reservationFacade.getAll();

        assertNotNull(reservationDtos);
        assertEquals(1, reservationDtos.size());
        assertEquals(readDto, reservationDtos.getFirst());
    }

    @Test
    void update_returnsUpdatedReservationDto_whenIdExists() {
        when(reservationMapper.mapCreateDtoToModel(createDto)).thenReturn(reservation);
        when(reservationService.update(id, reservation)).thenReturn(reservation);
        when(reservationMapper.mapModelToDto(reservation)).thenReturn(readDto);

        ReservationReadDto updatedReservationDto = reservationFacade.update(id, createDto);

        assertNotNull(updatedReservationDto);
        assertEquals(readDto, updatedReservationDto);
    }

    @Test
    void update_returnsNull_whenIdDoesNotExist() {
        when(reservationMapper.mapCreateDtoToModel(createDto)).thenReturn(reservation);
        when(reservationService.update(id, reservation)).thenReturn(null);

        ReservationReadDto updatedReservationDto = reservationFacade.update(id, createDto);

        assertNull(updatedReservationDto);
    }

    @Test
    void delete_returnsDeletedReservationDto_whenIdExists() {
        when(reservationService.delete(id)).thenReturn(reservation);
        when(reservationMapper.mapModelToDto(reservation)).thenReturn(readDto);

        ReservationReadDto deletedReservationDto = reservationFacade.delete(id);

        assertNotNull(deletedReservationDto);
        assertEquals(readDto, deletedReservationDto);
    }

    @Test
    void delete_returnsNull_whenIdDoesNotExist() {
        when(reservationService.delete(id)).thenReturn(null);

        ReservationReadDto deletedReservationDto = reservationFacade.delete(id);

        assertNull(deletedReservationDto);
    }

    @Test
    void getReservationsByPhoneNumber_returnsReservations_whenValid() {
        when(reservationService.getMemberReservations(member.getPhoneNumber(), true)).thenReturn(Collections.singletonList(reservation));
        when(reservationMapper.mapModelsToDtoList(Collections.singletonList(reservation))).thenReturn(Collections.singletonList(readDto));

        List<ReservationReadDto> reservations = reservationFacade.getReservationsByPhoneNumber(member.getPhoneNumber(), true);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
        assertEquals(readDto, reservations.getFirst());
    }

    @Test
    void getReservationsByPhoneNumber_returnsEmptyList_whenNoReservations() {
        when(reservationService.getMemberReservations(member.getPhoneNumber(), true)).thenReturn(Collections.emptyList());
        when(reservationMapper.mapModelsToDtoList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<ReservationReadDto> reservations = reservationFacade.getReservationsByPhoneNumber(member.getPhoneNumber(), true);

        assertNotNull(reservations);
        assertTrue(reservations.isEmpty());
    }
}