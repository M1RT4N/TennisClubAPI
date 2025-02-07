package vrzon.tennisclubapi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import vrzon.tennisclubapi.bases.SurfaceTestsBase;
import vrzon.tennisclubapi.data.models.Surface;
import vrzon.tennisclubapi.data.repositories.SurfaceRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.datasource.seedOnStart=false")
public class SurfaceRepositoryTests extends SurfaceTestsBase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SurfaceRepository surfaceRepository;

    @Test
    void save_whenSurfaceIsValid_savesAndReturnsSurface() {
        Surface savedSurface = surfaceRepository.save(surface);

        assertThat(savedSurface).isEqualTo(surface);
    }

    @Test
    void save_whenSurfaceIsInvalid_throwsException() {
        Surface invalidSurface = new Surface();

        assertThrows(DataIntegrityViolationException.class, () -> surfaceRepository.save(invalidSurface));
    }

    @Test
    void getById_returnsSurface_whenIdExists() {
        entityManager.persist(surface);

        Surface foundSurface = surfaceRepository.findById(surface.getId());

        assertThat(foundSurface).isEqualTo(surface);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        Surface foundSurface = surfaceRepository.findById(-1L);

        assertThat(foundSurface).isNull();
    }

    @Test
    void getAll_returnsAllSurfaces() {
        entityManager.persist(surface);

        List<Surface> foundSurfaces = surfaceRepository.findAll();

        assertThat(foundSurfaces).hasSize(1).contains(surface);
    }

    @Test
    void getAll_returnsEmptyList_whenNoSurfacesExist() {
        List<Surface> foundSurfaces = surfaceRepository.findAll();

        assertThat(foundSurfaces).isEmpty();
    }

    @Test
    void delete_whenSurfaceExists_deletesSurface() {
        entityManager.persist(surface);

        Surface deletedSurface = surfaceRepository.delete(surface);

        assertThat(deletedSurface).isNotNull();
        assertThat(deletedSurface.getDeleted()).isTrue();
    }
}