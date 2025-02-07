package vrzon.tennisclubapi.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import vrzon.tennisclubapi.bases.MemberTestsBase;
import vrzon.tennisclubapi.data.models.Member;
import vrzon.tennisclubapi.data.repositories.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.datasource.seedOnStart=false")
public class MemberRepositoryTests extends MemberTestsBase {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save_whenMemberIsValid_savesAndReturnsMember() {
        Member savedMember = memberRepository.save(member);

        assertThat(savedMember).isEqualTo(member);
    }

    @Test
    void save_whenMemberIsInvalid_throwsException() {
        Member invalidMember = new Member();

        assertThrows(DataIntegrityViolationException.class, () -> memberRepository.save(invalidMember));
    }

    @Test
    void getById_returnsMember_whenIdExists() {
        entityManager.persist(member);

        Member foundMember = memberRepository.findById(member.getId());

        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        Member foundMember = memberRepository.findById(-1L);

        assertThat(foundMember).isNull();
    }

    @Test
    void getAll_returnsAllMembers() {
        entityManager.persist(member);

        List<Member> foundMembers = memberRepository.findAll();

        assertThat(foundMembers).hasSize(1).contains(member);
    }

    @Test
    void getAll_returnsEmptyList_whenNoMembersExist() {
        List<Member> foundMembers = memberRepository.findAll();

        assertThat(foundMembers).isEmpty();
    }

    @Test
    void findByPhoneNumber_returnsMember_whenPhoneNumberExists() {
        entityManager.persist(member);

        Member foundMember = memberRepository.findByPhoneNumber(phoneNumber);

        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void findByPhoneNumber_returnsNull_whenPhoneNumberDoesNotExist() {
        Member foundMember = memberRepository.findByPhoneNumber("nonexistent");

        assertThat(foundMember).isNull();
    }

    @Test
    void delete_whenMemberExists_deletesMember() {
        entityManager.persist(member);

        Member deletedMember = memberRepository.delete(member);

        assertThat(deletedMember).isNotNull();
        assertThat(deletedMember.getDeleted()).isTrue();
    }
}