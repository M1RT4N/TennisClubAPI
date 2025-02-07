package vrzon.tennisclubapi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import vrzon.tennisclubapi.bases.CourtTestsBase;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.data.repositories.CourtRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.datasource.seedOnStart=false")
public class CourtRepositoryTests extends CourtTestsBase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CourtRepository courtRepository;

    @BeforeEach
    public void seed() {
        entityManager.persist(surface);
    }

    @Test
    void save_whenCourtIsValid_savesAndReturnsCourt() {
        Court savedCourt = courtRepository.save(court);

        assertThat(savedCourt).isEqualTo(court);
    }

    @Test
    void save_whenCourtIsInvalid_throwsException() {
        Court invalidCourt = new Court();

        assertThrows(DataIntegrityViolationException.class, () -> courtRepository.save(invalidCourt));
    }

    @Test
    void getById_returnsCourt_whenIdExists() {
        entityManager.persist(court);

        Court foundCourt = courtRepository.findById(courtNumber);

        assertThat(foundCourt).isEqualTo(court);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        Court foundCourt = courtRepository.findById(-1L);

        assertThat(foundCourt).isNull();
    }

    @Test
    void getAll_returnsAllCourts() {
        entityManager.persist(court);

        List<Court> foundCourts = courtRepository.findAll();

        assertThat(foundCourts).hasSize(1).contains(court);
    }

    @Test
    void getAll_returnsEmptyList_whenNoCourtsExist() {
        List<Court> foundCourts = courtRepository.findAll();

        assertThat(foundCourts).isEmpty();
    }

    @Test
    void findCourtByNumber_returnsCourt_whenNumberExists() {
        entityManager.persist(court);

        Court foundCourt = courtRepository.findCourtByNumber(courtNumber);

        assertThat(foundCourt).isEqualTo(court);
    }

    @Test
    void findCourtByNumber_returnsNull_whenNumberDoesNotExist() {
        Court foundCourt = courtRepository.findCourtByNumber(-1L);

        assertThat(foundCourt).isNull();
    }

    @Test
    void delete_whenCourtExists_deletesCourt() {
        entityManager.persist(court);

        Court deletedCourt = courtRepository.delete(court);

        assertThat(deletedCourt).isNotNull();
        assertThat(deletedCourt.getDeleted()).isTrue();
    }
}