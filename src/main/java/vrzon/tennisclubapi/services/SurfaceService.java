package vrzon.tennisclubapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vrzon.tennisclubapi.data.models.Surface;
import vrzon.tennisclubapi.data.repositories.SurfaceRepository;

@Service
public class SurfaceService extends ServiceBase<Surface> {

    @Autowired
    public SurfaceService(SurfaceRepository repository) {
        super(repository);
    }
}
