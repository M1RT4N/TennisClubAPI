package vrzon.tennisclubapi.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrzon.tennisclubapi.api.dtos.create.ReservationCreateDto;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.api.dtos.bases.ReservationBaseDto;
import vrzon.tennisclubapi.data.models.Reservation;
import vrzon.tennisclubapi.facade.ReservationFacade;

import java.util.List;

@RestController
@RequestMapping(path = "/api/reservation")
public class ReservationController extends ControllerBase<Reservation, ReservationReadDto, ReservationCreateDto, ReservationBaseDto> {
    public ReservationController(ReservationFacade facade) {
        super(facade);
    }

    @PostMapping(path = "/{phoneNumber}")
    public ResponseEntity<List<ReservationReadDto>> getReservationsByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber, @RequestBody boolean futureOnly) {
        return ResponseEntity.ok(((ReservationFacade) facade).getReservationsByPhoneNumber(phoneNumber, futureOnly));
    }
}
