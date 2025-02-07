package vrzon.tennisclubapi.data.repositories;

import org.springframework.stereotype.Repository;
import vrzon.tennisclubapi.data.models.Surface;

@Repository
public class SurfaceRepository extends RepositoryBase<Surface> {

    public SurfaceRepository() {
        super(Surface.class);
    }
}
