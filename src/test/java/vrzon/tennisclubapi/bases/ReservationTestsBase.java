package vrzon.tennisclubapi.bases;

import vrzon.tennisclubapi.api.dtos.create.ReservationCreateDto;
import vrzon.tennisclubapi.api.dtos.read.ReservationReadDto;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.data.models.Member;
import vrzon.tennisclubapi.data.models.Reservation;

import java.time.LocalDateTime;

public class ReservationTestsBase extends TestsBase {
    private final CourtTestsBase courtTestsBase = new CourtTestsBase();
    private final MemberTestsBase memberTestsBase = new MemberTestsBase();

    public final Court court = courtTestsBase.court;
    public final Member member = memberTestsBase.member;
    public boolean doublesGame = true;
    public float totalPrice = court.getSurface().getMinutePrice();
    public final String phoneNumber = member.getPhoneNumber();
    public final boolean futureOnly = memberTestsBase.futureOnly;

    public final Reservation reservation = new Reservation(courtTestsBase.court, memberTestsBase.member, totalPrice, LocalDateTime.now(), LocalDateTime.now().plusHours(1), doublesGame, LocalDateTime.now());
    protected final ReservationReadDto readDto = new ReservationReadDto();
    protected final ReservationCreateDto createDto = new ReservationCreateDto();
    protected final ReservationCreateDto updateDto = new ReservationCreateDto();
}
