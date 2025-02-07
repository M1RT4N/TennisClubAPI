package vrzon.tennisclubapi.facade;

import org.springframework.stereotype.Service;
import vrzon.tennisclubapi.api.dtos.bases.CourtBaseDto;
import vrzon.tennisclubapi.api.dtos.create.CourtCreateDto;
import vrzon.tennisclubapi.api.dtos.read.CourtReadDto;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.data.models.Reservation;
import vrzon.tennisclubapi.facade.mappers.CourtMapper;
import vrzon.tennisclubapi.facade.mappers.ReservationMapper;
import vrzon.tennisclubapi.services.CourtService;
import vrzon.tennisclubapi.services.ReservationService;

import java.util.List;

@Service
public class CourtFacade extends FacadeBase<Court, CourtReadDto, CourtCreateDto, CourtBaseDto> {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public CourtFacade(CourtService service, CourtMapper mapper, ReservationService reservationService, ReservationMapper reservationMapper) {
        super(service, mapper);
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationReadDto> getReservationsByCourtNumber(long number) {
        List<Reservation> reservations = reservationService.getReservationsByCourtNumber(number);
        return reservationMapper.mapModelsToDtoList(reservations);
    }

    @Override
    public CourtReadDto create(CourtCreateDto createDto) {
        Court court = mapper.mapCreateDtoToModel(createDto);
        Court created = service.create(court);
        if (created == null) {
            return null;
        }

        return mapper.mapModelToDto(created);
    }
}
