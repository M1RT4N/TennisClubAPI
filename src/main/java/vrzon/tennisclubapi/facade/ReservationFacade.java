package vrzon.tennisclubapi.facade;

import org.springframework.stereotype.Service;
import vrzon.tennisclubapi.api.dtos.bases.ReservationBaseDto;
import vrzon.tennisclubapi.api.dtos.create.ReservationCreateDto;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.data.models.Reservation;
import vrzon.tennisclubapi.facade.mappers.ReservationMapper;
import vrzon.tennisclubapi.services.ReservationService;

import java.util.List;

@Service
public class ReservationFacade extends FacadeBase<Reservation, ReservationReadDto, ReservationCreateDto, ReservationBaseDto> {

    public ReservationFacade(ReservationService service, ReservationMapper mapper) {
        super(service, mapper);
    }

    @Override
    public ReservationReadDto create(ReservationCreateDto createDto) {
        Reservation reservation = mapper.mapCreateDtoToModel(createDto);
        reservation = service.create(reservation);
        if (reservation == null) {
            return null;
        }

        return mapper.mapModelToDto(reservation);
    }

    public List<ReservationReadDto> getReservationsByPhoneNumber(String phoneNumber, boolean futureOnly) {
        List<Reservation> reservations = ((ReservationService) service).getMemberReservations(phoneNumber, futureOnly);
        return mapper.mapModelsToDtoList(reservations);
    }
}
