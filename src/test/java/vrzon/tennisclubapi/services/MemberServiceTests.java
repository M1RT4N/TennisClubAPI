package vrzon.tennisclubapi.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import vrzon.tennisclubapi.bases.MemberTestsBase;
import vrzon.tennisclubapi.data.models.Member;
import vrzon.tennisclubapi.data.repositories.MemberRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MemberServiceTests extends MemberTestsBase {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void create_returnsMember_whenValid() {
        when(memberRepository.save(member)).thenReturn(member);

        Member createdMember = memberService.create(member);

        assertNotNull(createdMember);
        assertEquals(member, createdMember);
    }

    @Test
    void create_returnsNull_whenRepositoryFails() {
        when(memberRepository.save(member)).thenReturn(null);

        Member createdMember = memberService.create(member);

        assertNull(createdMember);
    }

    @Test
    void getById_returnsMember_whenIdExists() {
        when(memberRepository.findById(id)).thenReturn(member);

        Member foundMember = memberService.getById(id);

        assertNotNull(foundMember);
        assertEquals(member, foundMember);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        when(memberRepository.findById(id)).thenReturn(null);

        Member foundMember = memberService.getById(id);

        assertNull(foundMember);
    }

    @Test
    void getAll_returnsAllMembers() {
        when(memberRepository.findAll()).thenReturn(Collections.singletonList(member));

        List<Member> members = memberService.getAll();

        assertNotNull(members);
        assertEquals(1, members.size());
        assertEquals(member, members.getFirst());
    }

    @Test
    void update_returnsUpdatedMember_whenIdExists() {
        Member updatedMember = new Member("Jane Doe", phoneNumber);

        when(memberRepository.findById(id)).thenReturn(member);
        when(memberRepository.save(member)).thenReturn(member);

        Member result = memberService.update(id, updatedMember);

        assertNotNull(result);
        assertEquals(updatedMember.getName(), result.getName());
    }

    @Test
    void update_returnsNull_whenIdDoesNotExist() {
        Member updatedMember = new Member("Jane Doe", phoneNumber);
        when(memberRepository.findById(id)).thenReturn(null);

        Member result = memberService.update(id, updatedMember);

        assertNull(result);
    }

    @Test
    void delete_returnsDeletedMember_whenIdExists() {
        when(memberRepository.findById(id)).thenReturn(member);
        when(memberRepository.delete(member)).thenReturn(member);

        Member result = memberService.delete(id);

        assertNotNull(result);
        assertTrue(result.getDeleted());
    }

    @Test
    void delete_returnsNull_whenIdDoesNotExist() {
        when(memberRepository.findById(id)).thenReturn(null);

        Member result = memberService.delete(id);

        assertNull(result);
    }
}