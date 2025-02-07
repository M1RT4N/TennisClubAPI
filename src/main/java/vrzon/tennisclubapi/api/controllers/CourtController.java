package vrzon.tennisclubapi.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrzon.tennisclubapi.api.dtos.create.CourtCreateDto;
import vrzon.tennisclubapi.api.dtos.read.CourtReadDto;
import vrzon.tennisclubapi.api.dtos.bases.CourtBaseDto;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.facade.CourtFacade;

import java.util.List;

@RestController
@RequestMapping(path = "/api/court")
public final class CourtController extends ControllerBase<Court, CourtReadDto, CourtCreateDto, CourtBaseDto> {
    public CourtController(CourtFacade facade) {
        super(facade);
    }

    @GetMapping(path = "/{number}/reservations")
    public ResponseEntity<List<ReservationReadDto>> getReservationsByCourtNumber(@PathVariable("number") Long number) {
        return ResponseEntity.ok(((CourtFacade) facade).getReservationsByCourtNumber(number));
    }
}
