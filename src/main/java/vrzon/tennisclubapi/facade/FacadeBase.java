package vrzon.tennisclubapi.facade;

import lombok.RequiredArgsConstructor;
import vrzon.tennisclubapi.api.dtos.read.ReadDtoBase;
import vrzon.tennisclubapi.data.models.BaseModel;
import vrzon.tennisclubapi.facade.mappers.GenericMapper;
import vrzon.tennisclubapi.services.ServiceBase;

import java.util.List;

@RequiredArgsConstructor
public abstract class FacadeBase<Model extends BaseModel<Model>, ReadDto extends ReadDtoBase<BaseDto>, CreateDto, BaseDto> {
    protected final ServiceBase<Model> service;
    protected final GenericMapper<Model, ReadDto, CreateDto, BaseDto> mapper;

    public List<ReadDto> getAll() {
        return mapper.mapModelsToDtoList(service.getAll());
    }

    public ReadDto getById(Long id) {
        Model model = service.getById(id);
        if (model == null) {
            return null;
        }

        return mapper.mapModelToDto(model);
    }

    public ReadDto create(CreateDto createDto) {
        Model createModel = mapper.mapCreateDtoToModel(createDto);
        Model created = service.create(createModel);
        if (created == null) {
            return null;
        }

        return mapper.mapModelToDto(created);
    }

    public ReadDto update(Long id, CreateDto updateDto) {
        Model updateModel = mapper.mapCreateDtoToModel(updateDto);
        Model updated = service.update(id, updateModel);
        if (updated == null) {
            return null;
        }

        return mapper.mapModelToDto(updated);
    }

    public ReadDto delete(Long id) {
        Model deleted = service.delete(id);
        if (deleted == null) {
            return null;
        }

        return mapper.mapModelToDto(deleted);
    }
}
