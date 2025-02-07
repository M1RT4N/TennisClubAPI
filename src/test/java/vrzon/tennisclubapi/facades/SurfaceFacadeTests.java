package vrzon.tennisclubapi.facades;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import vrzon.tennisclubapi.api.dtos.read.SurfaceReadDto;
import vrzon.tennisclubapi.bases.SurfaceTestsBase;
import vrzon.tennisclubapi.facade.SurfaceFacade;
import vrzon.tennisclubapi.facade.mappers.SurfaceMapper;
import vrzon.tennisclubapi.services.SurfaceService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SurfaceFacadeTests extends SurfaceTestsBase {

    @Mock
    private SurfaceService surfaceService;

    @Mock
    private SurfaceMapper surfaceMapper;

    @InjectMocks
    private SurfaceFacade surfaceFacade;

    @Test
    void create_returnsSurfaceDto_whenValid() {
        when(surfaceMapper.mapCreateDtoToModel(createDto)).thenReturn(surface);
        when(surfaceService.create(surface)).thenReturn(surface);
        when(surfaceMapper.mapModelToDto(surface)).thenReturn(readDto);

        SurfaceReadDto createdSurfaceDto = surfaceFacade.create(createDto);

        assertNotNull(createdSurfaceDto);
        assertEquals(readDto, createdSurfaceDto);
    }

    @Test
    void create_returnsNull_whenServiceReturnsNull() {
        when(surfaceMapper.mapCreateDtoToModel(createDto)).thenReturn(surface);
        when(surfaceService.create(surface)).thenReturn(null);

        SurfaceReadDto createdSurfaceDto = surfaceFacade.create(createDto);

        assertNull(createdSurfaceDto);
    }

    @Test
    void getById_returnsSurfaceDto_whenIdExists() {
        when(surfaceService.getById(id)).thenReturn(surface);
        when(surfaceMapper.mapModelToDto(surface)).thenReturn(readDto);

        SurfaceReadDto foundSurfaceDto = surfaceFacade.getById(id);

        assertNotNull(foundSurfaceDto);
        assertEquals(readDto, foundSurfaceDto);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        when(surfaceService.getById(id)).thenReturn(null);

        SurfaceReadDto foundSurfaceDto = surfaceFacade.getById(id);

        assertNull(foundSurfaceDto);
    }

    @Test
    void getAll_returnsAllSurfaceDtos() {
        when(surfaceService.getAll()).thenReturn(Collections.singletonList(surface));
        when(surfaceMapper.mapModelsToDtoList(Collections.singletonList(surface))).thenReturn(Collections.singletonList(readDto));

        List<SurfaceReadDto> surfaceDtos = surfaceFacade.getAll();

        assertNotNull(surfaceDtos);
        assertEquals(1, surfaceDtos.size());
        assertEquals(readDto, surfaceDtos.getFirst());
    }

    @Test
    void update_returnsUpdatedSurfaceDto_whenIdExists() {
        when(surfaceMapper.mapCreateDtoToModel(updateDto)).thenReturn(surface);
        when(surfaceService.update(id, surface)).thenReturn(surface);
        when(surfaceMapper.mapModelToDto(surface)).thenReturn(readDto);

        SurfaceReadDto updatedSurfaceDto = surfaceFacade.update(id, updateDto);

        assertNotNull(updatedSurfaceDto);
        assertEquals(readDto, updatedSurfaceDto);
    }

    @Test
    void update_returnsNull_whenIdDoesNotExist() {
        when(surfaceMapper.mapCreateDtoToModel(updateDto)).thenReturn(surface);
        when(surfaceService.update(id, surface)).thenReturn(null);

        SurfaceReadDto updatedSurfaceDto = surfaceFacade.update(id, updateDto);

        assertNull(updatedSurfaceDto);
    }

    @Test
    void delete_returnsDeletedSurfaceDto_whenIdExists() {
        when(surfaceService.delete(id)).thenReturn(surface);
        when(surfaceMapper.mapModelToDto(surface)).thenReturn(readDto);

        SurfaceReadDto deletedSurfaceDto = surfaceFacade.delete(id);

        assertNotNull(deletedSurfaceDto);
        assertEquals(readDto, deletedSurfaceDto);
    }

    @Test
    void delete_returnsNull_whenIdDoesNotExist() {
        when(surfaceService.delete(id)).thenReturn(null);

        SurfaceReadDto deletedSurfaceDto = surfaceFacade.delete(id);

        assertNull(deletedSurfaceDto);
    }
}