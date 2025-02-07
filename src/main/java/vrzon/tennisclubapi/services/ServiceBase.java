package vrzon.tennisclubapi.services;

import lombok.RequiredArgsConstructor;
import vrzon.tennisclubapi.data.models.BaseModel;
import vrzon.tennisclubapi.data.repositories.RepositoryBase;

import java.util.List;

@RequiredArgsConstructor
public abstract class ServiceBase<Model extends BaseModel<Model>>{
    protected final RepositoryBase<Model> repository;

    public List<Model> getAll() {
        return repository.findAll();
    }

    public Model getById(Long id) {
        return repository.findById(id);
    }

    public Model create(Model model) {
        return repository.save(model);
    }

    public Model update(Long id, Model updatingModel) {
       Model storedModel = repository.findById(id);
       if (storedModel == null) {
           return null;
       }

       storedModel.updateWith(updatingModel);

       return repository.save(storedModel);
    }

    public Model delete(Long id) {
        Model storedModel = repository.findById(id);
        if (storedModel == null) {
            return null;
        }

        storedModel.setDeleted(true);

        return repository.delete(storedModel);
    }
}
