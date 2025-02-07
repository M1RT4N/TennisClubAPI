package vrzon.tennisclubapi.facade;

import org.springframework.stereotype.Service;
import vrzon.tennisclubapi.api.dtos.read.MemberReadDto;
import vrzon.tennisclubapi.api.dtos.bases.MemberBaseDto;
import vrzon.tennisclubapi.data.models.Member;
import vrzon.tennisclubapi.facade.mappers.MemberMapper;
import vrzon.tennisclubapi.services.MemberService;

@Service
public class MemberFacade extends FacadeBase<Member, MemberReadDto, MemberBaseDto, MemberBaseDto> {
    public MemberFacade(MemberService service, MemberMapper mapper) {
        super(service, mapper);
    }
}
