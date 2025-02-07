package vrzon.tennisclubapi.facade.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vrzon.tennisclubapi.api.dtos.create.ReservationCreateDto;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.api.dtos.bases.ReservationBaseDto;
import vrzon.tennisclubapi.data.models.Reservation;

@Mapper(componentModel = "spring", uses = {CourtMapper.class, MemberMapper.class})
public interface ReservationMapper extends GenericMapper<Reservation, ReservationReadDto, ReservationCreateDto, ReservationBaseDto> {

    @Override
    @Mapping(target = "court.number", source = "courtNumber")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    Reservation mapCreateDtoToModel(ReservationCreateDto createDto);
}
