package vrzon.tennisclubapi.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import vrzon.tennisclubapi.bases.SurfaceTestsBase;
import vrzon.tennisclubapi.data.models.Surface;
import vrzon.tennisclubapi.data.repositories.SurfaceRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class SurfaceServiceTests extends SurfaceTestsBase {

    @Mock
    private SurfaceRepository surfaceRepository;

    @InjectMocks
    private SurfaceService surfaceService;

    @Test
    void create_returnsSurface_whenValid() {
        when(surfaceRepository.save(surface)).thenReturn(surface);

        Surface createdSurface = surfaceService.create(surface);

        assertNotNull(createdSurface);
        assertEquals(surface, createdSurface);
    }

    @Test
    void create_returnsNull_whenRepositoryFails() {
        when(surfaceRepository.save(surface)).thenReturn(null);

        Surface createdSurface = surfaceService.create(surface);

        assertNull(createdSurface);
    }

    @Test
    void getById_returnsSurface_whenIdExists() {
        when(surfaceRepository.findById(id)).thenReturn(surface);

        Surface foundSurface = surfaceService.getById(id);

        assertNotNull(foundSurface);
        assertEquals(surface, foundSurface);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        when(surfaceRepository.findById(id)).thenReturn(null);

        Surface foundSurface = surfaceService.getById(id);

        assertNull(foundSurface);
    }

    @Test
    void getAll_returnsAllSurfaces() {
        when(surfaceRepository.findAll()).thenReturn(Collections.singletonList(surface));

        List<Surface> surfaces = surfaceService.getAll();

        assertNotNull(surfaces);
        assertEquals(1, surfaces.size());
        assertEquals(surface, surfaces.getFirst());
    }

    @Test
    void update_returnsUpdatedSurface_whenIdExists() {
        Surface updatedSurface = new Surface("Clay", 1.5f);

        when(surfaceRepository.findById(id)).thenReturn(surface);
        when(surfaceRepository.save(surface)).thenReturn(updatedSurface);

        Surface result = surfaceService.update(id, updatedSurface);

        assertNotNull(result);
        assertEquals(updatedSurface, result);
    }

    @Test
    void update_returnsNull_whenIdDoesNotExist() {
        Surface updatedSurface = new Surface("Clay", 1.5f);
        when(surfaceRepository.findById(id)).thenReturn(null);

        Surface result = surfaceService.update(id, updatedSurface);

        assertNull(result);
    }

    @Test
    void delete_returnsDeletedSurface_whenIdExists() {
        when(surfaceRepository.findById(id)).thenReturn(surface);
        when(surfaceRepository.delete(surface)).thenReturn(surface);

        Surface result = surfaceService.delete(id);

        assertNotNull(result);
        assertTrue(result.getDeleted());
    }

    @Test
    void delete_returnsNull_whenIdDoesNotExist() {
        when(surfaceRepository.findById(id)).thenReturn(null);

        Surface result = surfaceService.delete(id);

        assertNull(result);
    }
}