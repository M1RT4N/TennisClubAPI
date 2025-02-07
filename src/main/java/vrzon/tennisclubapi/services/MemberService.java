package vrzon.tennisclubapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vrzon.tennisclubapi.data.models.Member;
import vrzon.tennisclubapi.data.repositories.MemberRepository;

@Service
public class MemberService extends ServiceBase<Member> {

    @Autowired
    public MemberService(MemberRepository repository) {
        super(repository);
    }
}
