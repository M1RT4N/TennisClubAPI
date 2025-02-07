package vrzon.tennisclubapi.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import vrzon.tennisclubapi.bases.CourtTestsBase;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.data.repositories.CourtRepository;
import vrzon.tennisclubapi.data.repositories.SurfaceRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CourtServiceTests extends CourtTestsBase {

    @Mock
    private CourtRepository courtRepository;

    @Mock
    private SurfaceRepository surfaceRepository;

    @InjectMocks
    private CourtService courtService;

    @Test
    void create_returnsCourt_whenValid() {
        when(surfaceRepository.findById(surface.getId())).thenReturn(surface);
        when(courtRepository.save(court)).thenReturn(court);

        Court createdCourt = courtService.create(court);

        assertNotNull(createdCourt);
        assertEquals(surface, createdCourt.getSurface());
    }

    @Test
    void create_returnsNull_whenSurfaceDeleted() {
        surface.setDeleted(true);
        when(surfaceRepository.findById(surface.getId())).thenReturn(surface);

        Court createdCourt = courtService.create(court);

        assertNull(createdCourt);
    }

    @Test
    void create_returnsNull_whenSurfaceNotFound() {
        when(surfaceRepository.findById(surface.getId())).thenReturn(null);

        Court createdCourt = courtService.create(court);

        assertNull(createdCourt);
    }

    @Test
    void getById_returnsCourt_whenIdExists() {
        when(courtRepository.findById(courtNumber)).thenReturn(court);

        Court foundCourt = courtService.getById(courtNumber);

        assertNotNull(foundCourt);
        assertEquals(court, foundCourt);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        when(courtRepository.findById(courtNumber)).thenReturn(null);

        Court foundCourt = courtService.getById(courtNumber);

        assertNull(foundCourt);
    }

    @Test
    void getAll_returnsAllCourts() {
        when(courtRepository.findAll()).thenReturn(Collections.singletonList(court));

        List<Court> courts = courtService.getAll();

        assertNotNull(courts);
        assertEquals(1, courts.size());
        assertEquals(court, courts.getFirst());
    }

    @Test
    void update_returnsUpdatedCourt_whenIdExists() {
        Court updatedCourt = new Court(courtNumber, surface);
        updatedCourt.setSurface(surface);

        when(courtRepository.findById(courtNumber)).thenReturn(court);
        when(courtRepository.save(court)).thenReturn(court);

        Court result = courtService.update(courtNumber, updatedCourt);

        assertNotNull(result);
        assertEquals(surface, result.getSurface());
    }

    @Test
    void update_returnsNull_whenIdDoesNotExist() {
        Court updatedCourt = new Court(courtNumber, surface);
        when(courtRepository.findById(courtNumber)).thenReturn(null);

        Court result = courtService.update(courtNumber, updatedCourt);

        assertNull(result);
    }

    @Test
    void delete_returnsDeletedCourt_whenIdExists() {
        when(courtRepository.findById(courtNumber)).thenReturn(court);
        when(courtRepository.delete(court)).thenReturn(court);

        Court result = courtService.delete(courtNumber);

        assertNotNull(result);
        assertTrue(result.getDeleted());
    }

    @Test
    void delete_returnsNull_whenIdDoesNotExist() {
        when(courtRepository.findById(courtNumber)).thenReturn(null);

        Court result = courtService.delete(courtNumber);

        assertNull(result);
    }
}