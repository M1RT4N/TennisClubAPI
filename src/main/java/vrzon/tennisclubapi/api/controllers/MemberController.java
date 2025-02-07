package vrzon.tennisclubapi.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrzon.tennisclubapi.api.dtos.read.MemberReadDto;
import vrzon.tennisclubapi.api.dtos.bases.MemberBaseDto;
import vrzon.tennisclubapi.data.models.Member;
import vrzon.tennisclubapi.facade.MemberFacade;

@RestController
@RequestMapping(path = "/api/member")
public final class MemberController extends ControllerBase<Member, MemberReadDto, MemberBaseDto, MemberBaseDto> {
    public MemberController(MemberFacade facade) {
        super(facade);
    }
}
