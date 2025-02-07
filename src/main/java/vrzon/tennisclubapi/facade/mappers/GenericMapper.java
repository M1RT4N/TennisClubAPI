package vrzon.tennisclubapi.facade.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vrzon.tennisclubapi.api.dtos.read.ReadDtoBase;
import vrzon.tennisclubapi.data.models.BaseModel;

import java.util.List;

public interface GenericMapper<Model extends BaseModel<Model>, ReadDto extends ReadDtoBase<BaseDto>, CreateDto, BaseDto> {
    @Named("mapToBaseDto")
    BaseDto mapToBaseDto(Model model);

    @Mapping(target = "data", source = ".", qualifiedByName = "mapToBaseDto")
    ReadDto mapModelToDto(Model model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Model mapCreateDtoToModel(CreateDto createDto);

    List<ReadDto> mapModelsToDtoList(List<Model> models);
}
