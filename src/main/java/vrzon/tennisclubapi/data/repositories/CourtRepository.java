package vrzon.tennisclubapi.data.repositories;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import vrzon.tennisclubapi.data.models.Court;

@Repository
public class CourtRepository extends RepositoryBase<Court> {

    public CourtRepository() {
        super(Court.class);
    }

    public Court findCourtByNumber(long number) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Court> cq = cb.createQuery(modelClass);
        Root<Court> courtRoot = cq.from(modelClass);
        cq.select(courtRoot).where(cb.equal(courtRoot.get("number"), number));
        TypedQuery<Court> query = entityManager.createQuery(cq);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
