package vrzon.tennisclubapi.data.database;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.data.models.Surface;
import vrzon.tennisclubapi.data.repositories.CourtRepository;
import vrzon.tennisclubapi.data.repositories.SurfaceRepository;

@Component
public class DatabaseSeeder {

    @Bean
    @ConditionalOnProperty(name = "spring.datasource.seedOnStart", havingValue = "true")
    public ApplicationRunner seedDatabase(SurfaceRepository surfaceRepository,
                                          CourtRepository courtRepository) {
        return _ -> {
            System.out.println("Seeding the database...");

            Surface surfaceGrass = surfaceRepository.save(new Surface("Grass", 2.04f));
            Surface surfaceRubberCarpet = surfaceRepository.save(new Surface("Rubber Carpet", 1.38f));

            courtRepository.save(new Court(1L, surfaceGrass));
            courtRepository.save(new Court(2L, surfaceRubberCarpet));
            courtRepository.save(new Court(3L, surfaceGrass));
            courtRepository.save(new Court(4L, surfaceRubberCarpet));

            System.out.println("Seeded");
        };
    }
}
