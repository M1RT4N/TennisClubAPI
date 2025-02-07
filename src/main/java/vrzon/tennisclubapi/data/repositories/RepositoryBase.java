package vrzon.tennisclubapi.data.repositories;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vrzon.tennisclubapi.data.models.BaseModel;

import java.util.List;

@Repository
@Transactional
public abstract class RepositoryBase<Model extends BaseModel<Model>> {
    protected Class<Model> modelClass;

    @PersistenceContext
    protected EntityManager entityManager;

    protected RepositoryBase(Class<Model> modelClass) {
        this.modelClass = modelClass;
    }

    public Model findById(Long id) {
        return entityManager.find(modelClass, id);
    }

    public List<Model> findAll() {
        CriteriaQuery<Model> cq = entityManager.getCriteriaBuilder().createQuery(modelClass);
        cq.select(cq.from(modelClass));
        return entityManager.createQuery(cq).getResultList();
    }

    public Model save(Model model) {
        if (model.getId() == null) {
            try {
                entityManager.persist(model);
                return model;

            } catch (ConstraintViolationException | EntityExistsException e) {
                return null;
            }
        } else {
            return entityManager.merge(model);
        }
    }

    public Model delete(Model model) {
        model.setDeleted(true);
        return entityManager.merge(model);
    }
}
