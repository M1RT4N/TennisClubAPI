package vrzon.tennisclubapi.data.repositories;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import vrzon.tennisclubapi.data.models.Member;

@Repository
public class MemberRepository extends RepositoryBase<Member> {

    public MemberRepository() {
        super(Member.class);
    }

    public Member findByPhoneNumber(String phoneNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(modelClass);
        Root<Member> memberRoot = cq.from(modelClass);
        cq.select(memberRoot).where(cb.equal(memberRoot.get("phoneNumber"), phoneNumber));

        TypedQuery<Member> query = entityManager.createQuery(cq);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
