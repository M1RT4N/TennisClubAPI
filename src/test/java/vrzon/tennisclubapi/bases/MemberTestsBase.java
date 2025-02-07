package vrzon.tennisclubapi.bases;

import vrzon.tennisclubapi.api.dtos.create.MemberCreateDto;
import vrzon.tennisclubapi.api.dtos.read.MemberReadDto;
import vrzon.tennisclubapi.data.models.Member;

public class MemberTestsBase extends TestsBase {
    public final String phoneNumber = "+123456789";
    public final boolean futureOnly = true;
    public final Member member = new Member("John Doe", phoneNumber);

    protected final MemberCreateDto createDto = new MemberCreateDto();
    protected final MemberCreateDto updateDto = new MemberCreateDto();
    protected final MemberReadDto readDto = new MemberReadDto();
}
