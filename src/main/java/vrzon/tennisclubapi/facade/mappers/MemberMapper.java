package vrzon.tennisclubapi.facade.mappers;

import org.mapstruct.Mapper;
import vrzon.tennisclubapi.api.dtos.read.MemberReadDto;
import vrzon.tennisclubapi.api.dtos.bases.MemberBaseDto;
import vrzon.tennisclubapi.data.models.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper extends GenericMapper<Member, MemberReadDto, MemberBaseDto, MemberBaseDto> {
}
