package vrzon.tennisclubapi.facades;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import vrzon.tennisclubapi.api.dtos.read.MemberReadDto;
import vrzon.tennisclubapi.bases.MemberTestsBase;
import vrzon.tennisclubapi.facade.MemberFacade;
import vrzon.tennisclubapi.facade.mappers.MemberMapper;
import vrzon.tennisclubapi.services.MemberService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MemberFacadeTests extends MemberTestsBase {

    @Mock
    private MemberService memberService;

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberFacade memberFacade;

    @Test
    void create_returnsMemberDto_whenValid() {
        when(memberMapper.mapCreateDtoToModel(createDto)).thenReturn(member);
        when(memberService.create(member)).thenReturn(member);
        when(memberMapper.mapModelToDto(member)).thenReturn(readDto);

        MemberReadDto createdMemberDto = memberFacade.create(createDto);

        assertNotNull(createdMemberDto);
        assertEquals(readDto, createdMemberDto);
    }

    @Test
    void create_returnsNull_whenServiceReturnsNull() {
        when(memberMapper.mapCreateDtoToModel(createDto)).thenReturn(member);
        when(memberService.create(member)).thenReturn(null);

        MemberReadDto createdMemberDto = memberFacade.create(createDto);

        assertNull(createdMemberDto);
    }

    @Test
    void getById_returnsMemberDto_whenIdExists() {
        when(memberService.getById(id)).thenReturn(member);
        when(memberMapper.mapModelToDto(member)).thenReturn(readDto);

        MemberReadDto foundMemberDto = memberFacade.getById(id);

        assertNotNull(foundMemberDto);
        assertEquals(readDto, foundMemberDto);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        when(memberService.getById(id)).thenReturn(null);

        MemberReadDto foundMemberDto = memberFacade.getById(id);

        assertNull(foundMemberDto);
    }

    @Test
    void getAll_returnsAllMemberDtos() {
        when(memberService.getAll()).thenReturn(Collections.singletonList(member));
        when(memberMapper.mapModelsToDtoList(Collections.singletonList(member))).thenReturn(Collections.singletonList(readDto));

        List<MemberReadDto> memberDtos = memberFacade.getAll();

        assertNotNull(memberDtos);
        assertEquals(1, memberDtos.size());
        assertEquals(readDto, memberDtos.getFirst());
    }

    @Test
    void update_returnsUpdatedMemberDto_whenIdExists() {
        when(memberMapper.mapCreateDtoToModel(updateDto)).thenReturn(member);
        when(memberService.update(id, member)).thenReturn(member);
        when(memberMapper.mapModelToDto(member)).thenReturn(readDto);

        MemberReadDto updatedMemberDto = memberFacade.update(id, updateDto);

        assertNotNull(updatedMemberDto);
        assertEquals(readDto, updatedMemberDto);
    }

    @Test
    void update_returnsNull_whenIdDoesNotExist() {
        when(memberMapper.mapCreateDtoToModel(updateDto)).thenReturn(member);
        when(memberService.update(id, member)).thenReturn(null);

        MemberReadDto updatedMemberDto = memberFacade.update(id, updateDto);

        assertNull(updatedMemberDto);
    }

    @Test
    void delete_returnsDeletedMemberDto_whenIdExists() {
        when(memberService.delete(id)).thenReturn(member);
        when(memberMapper.mapModelToDto(member)).thenReturn(readDto);

        MemberReadDto deletedMemberDto = memberFacade.delete(id);

        assertNotNull(deletedMemberDto);
        assertEquals(readDto, deletedMemberDto);
    }

    @Test
    void delete_returnsNull_whenIdDoesNotExist() {
        when(memberService.delete(id)).thenReturn(null);

        MemberReadDto deletedMemberDto = memberFacade.delete(id);

        assertNull(deletedMemberDto);
    }
}