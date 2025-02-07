package vrzon.tennisclubapi.controllers;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vrzon.tennisclubapi.api.controllers.MemberController;
import vrzon.tennisclubapi.api.dtos.read.MemberReadDto;
import vrzon.tennisclubapi.bases.MemberTestsBase;
import vrzon.tennisclubapi.facade.MemberFacade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberControllerTests extends MemberTestsBase {

    @Mock
    private MemberFacade memberFacade;

    @InjectMocks
    private MemberController memberController;

    @Test
    void getAll_returnsAllMembers() {
        List<MemberReadDto> members = List.of(readDto);
        Mockito.when(memberFacade.getAll()).thenReturn(members);

        ResponseEntity<List<MemberReadDto>> response = memberController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(members, response.getBody());
    }

    @Test
    void getById_returnsMember_whenIdExists() {
        Mockito.when(memberFacade.getById(id)).thenReturn(readDto);

        ResponseEntity<MemberReadDto> response = memberController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void getById_returnsNotFound_whenIdDoesNotExist() {
        Mockito.when(memberFacade.getById(id)).thenReturn(null);

        ResponseEntity<MemberReadDto> response = memberController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create_createsMember() {
        Mockito.when(memberFacade.create(createDto)).thenReturn(readDto);

        ResponseEntity<MemberReadDto> response = memberController.create(createDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void update_updatesMember() {
        Mockito.when(memberFacade.update(id, updateDto)).thenReturn(readDto);

        ResponseEntity<MemberReadDto> response = memberController.update(id, updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }

    @Test
    void delete_deletesMember() {
        Mockito.when(memberFacade.delete(id)).thenReturn(readDto);

        ResponseEntity<MemberReadDto> response = memberController.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(readDto, response.getBody());
    }
}