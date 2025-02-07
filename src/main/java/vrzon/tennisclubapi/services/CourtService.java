package vrzon.tennisclubapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.data.models.Surface;
import vrzon.tennisclubapi.data.repositories.CourtRepository;
import vrzon.tennisclubapi.data.repositories.SurfaceRepository;

@Service
public class CourtService extends ServiceBase<Court> {
    private final SurfaceRepository surfaceRepository;

    @Autowired
    public CourtService(CourtRepository repository, SurfaceRepository surfaceRepository) {
        super(repository);
        this.surfaceRepository = surfaceRepository;
    }

    @Override
    public Court create(Court court) {
        Surface surface = surfaceRepository.findById(court.getSurface().getId());
        if (surface == null || surface.getDeleted()) {
            return null;
        }

        court.setSurface(surface);

        return repository.save(court);
    }
}
