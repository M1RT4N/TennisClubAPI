package vrzon.tennisclubapi.controllers;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vrzon.tennisclubapi.api.controllers.SurfaceController;
import vrzon.tennisclubapi.api.dtos.read.SurfaceReadDto;
import vrzon.tennisclubapi.bases.SurfaceTestsBase;
import vrzon.tennisclubapi.facade.SurfaceFacade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurfaceControllerTests extends SurfaceTestsBase {

    @Mock
    private SurfaceFacade surfaceFacade;

    @InjectMocks
    private SurfaceController surfaceController;

    @Test
    void getAll_returnsAllSurfaces() {
        List<SurfaceReadDto> surfaces = List.of(readDto);
        Mockito.when(surfaceFacade.getAll()).thenReturn(surfaces);

        ResponseEntity<List<SurfaceReadDto>> response = surfaceController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(surfaces, response.getBody());
    }

    @Test
    void getById_returnsSurface_whenIdExists() {
        Mockito.when(surfaceFacade.getById(id)).thenReturn(readDto);

        ResponseEntity<SurfaceReadDto> response = surfaceController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void getById_returnsNotFound_whenIdDoesNotExist() {
        Mockito.when(surfaceFacade.getById(id)).thenReturn(null);

        ResponseEntity<SurfaceReadDto> response = surfaceController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create_createsSurface() {
        Mockito.when(surfaceFacade.create(createDto)).thenReturn(readDto);

        ResponseEntity<SurfaceReadDto> response = surfaceController.create(createDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void update_updatesSurface() {
        Mockito.when(surfaceFacade.update(id, updateDto)).thenReturn(readDto);

        ResponseEntity<SurfaceReadDto> response = surfaceController.update(id, updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void delete_deletesSurface() {
        Mockito.when(surfaceFacade.delete(id)).thenReturn(readDto);

        ResponseEntity<SurfaceReadDto> response = surfaceController.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }
}