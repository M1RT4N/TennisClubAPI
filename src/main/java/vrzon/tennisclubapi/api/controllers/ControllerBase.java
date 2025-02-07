package vrzon.tennisclubapi.api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrzon.tennisclubapi.api.dtos.read.ReadDtoBase;
import vrzon.tennisclubapi.data.models.BaseModel;
import vrzon.tennisclubapi.facade.FacadeBase;

import java.util.List;

@RequiredArgsConstructor
public abstract class ControllerBase<Model extends BaseModel<Model>, ReadDto extends ReadDtoBase<BaseDto>, CreateDto, BaseDto> {
    protected final FacadeBase<Model, ReadDto, CreateDto, BaseDto> facade;

    @GetMapping
    public ResponseEntity<List<ReadDto>> getAll() {
        return ResponseEntity.ok(facade.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReadDto> getById(@Valid @PathVariable("id") Long id) {
       return sendResponse(facade.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReadDto> create(@Valid @RequestBody CreateDto createDto) {
        return sendResponse(facade.create(createDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReadDto> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody CreateDto updateDto) {
       return sendResponse(facade.update(id, updateDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReadDto> delete(@Valid @PathVariable("id") Long id) {
       return sendResponse(facade.delete(id), HttpStatus.OK);
    }

    public ResponseEntity<ReadDto> sendResponse(ReadDto dto, HttpStatus status) {
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(status).body(dto);
        }
    }
}
