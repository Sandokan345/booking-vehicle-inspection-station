package com.example.booking.service.impl;

import com.example.booking.ModelMapper.MappingHelper;
import com.example.booking.dto.ReservationDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.ExceptionDescription;
import com.example.booking.model.Customer;
import com.example.booking.model.InspectionStation;
import com.example.booking.model.Register;
import com.example.booking.model.Reservation;
import com.example.booking.repository.CustomerRepository;
import com.example.booking.repository.InspectionStationRepository;
import com.example.booking.repository.RegisterRepository;
import com.example.booking.repository.ReservationRepository;
import com.example.booking.response.ReservationMessageResponse;
import com.example.booking.service.InspectionStationService;
import com.example.booking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImlp implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private InspectionStationRepository inspectionStationRepository;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InspectionStationService inspectionStationService;

    private void validate(ReservationDTO reservationDTO) {
        if (reservationDTO.getLicenseNumber() == null || reservationDTO.getLicenseNumber() < 0) {
            throw new BookingException(ExceptionDescription.RESERVATION_LICENSE_NUMBER_NOT_FOUND.getDescription());
        }
        if (reservationDTO.getDateTime() == null) {
            throw new BookingException(ExceptionDescription.RESERVATION_DATE_TIME_NOT_FOUND.getDescription());
        }
        if (reservationDTO.getLicenseOwner() == null) {
            throw new BookingException(ExceptionDescription.RESERVATION_LICENSE_OWNER_NOT_FOUND.getDescription());
        }
        if (reservationDTO.getId() <0) {
            throw new BookingException(ExceptionDescription.RESERVATION_ID_NOT_FOUND.getDescription());
        }
        if (reservationDTO.getInspectionStationId() == null || reservationDTO.getInspectionStationId() <0) {
            throw new BookingException(ExceptionDescription.INSPECTION_STATION_ID_NOT_FOUND.getDescription());
        }
        if (reservationDTO.getCustomerId() == null || reservationDTO.getCustomerId() <0) {
            throw new BookingException(ExceptionDescription.CUSTOMER_ID_NOT_FOUND.getDescription());
        }
        Optional<InspectionStation> optionalInspectionStation = inspectionStationRepository.findById(reservationDTO.getInspectionStationId());
        if (!optionalInspectionStation.isPresent()){
            throw new BookingException(ExceptionDescription.INSPECTION_STATION_NOT_FOUND.getDescription());
        }
        Optional<Customer> optionalCustomer = customerRepository.findById(reservationDTO.getCustomerId());
        if (!optionalCustomer.isPresent()){
            throw new BookingException(ExceptionDescription.CUSTOMER_NOT_FOUND.getDescription());
        }
        Register registerByCustomerId = registerRepository.findByCustomerId(reservationDTO.getCustomerId());
        if (registerByCustomerId == null) {
            throw new BookingException(ExceptionDescription.CUSTOMER_NOT_A_MEMBER.getDescription());
        }
    }

    private void validateIsEmpty(ReservationDTO reservationDTO){
        Reservation reservationDate =reservationRepository.findOneByDateTime(reservationDTO.getDateTime());
        if (Optional.ofNullable(reservationDate).isPresent()){
            throw new BookingException(ExceptionDescription.RESERVATION_HOUR_IS_FULL.getDescription());
        }
        Reservation reservationLicenseNumber = reservationRepository.findOneByLicenseNumber(reservationDTO.getLicenseNumber());
        if (Optional.ofNullable(reservationLicenseNumber).isPresent()){
            throw new BookingException(ExceptionDescription.LICENSE_NUMBER_IS_USING.getDescription());
        }
    }

    private void validateTime(ReservationDTO reservationDTO){
        if (reservationDTO.getDateTime().getHour() <9 || reservationDTO.getDateTime().getHour() >19){
            throw new BookingException(ExceptionDescription.RESERVATION_HOUR_NOT_FOUND.getDescription());
        }
        InspectionStation inspectionStation = inspectionStationService.getById(reservationDTO.getInspectionStationId());
        if (inspectionStation.getVehicleCapacity() == 10){
            if (reservationDTO.getDateTime().getMinute() != 0){
                throw new BookingException(ExceptionDescription.CHOOSE_FULL_HOURS.getDescription());
            }
        }
        if (inspectionStation.getVehicleCapacity() == 20){
            if (reservationDTO.getDateTime().getMinute() != 30 && reservationDTO.getDateTime().getMinute() != 0){
                throw new BookingException(ExceptionDescription.CHOOSE_FULL_OR_HALF_HOURS.getDescription());
            }
        }
    }

    @Override
    public List<ReservationDTO> findAll() {
        List<Reservation> reservationList = reservationRepository.findAll();
        return MappingHelper.mapList(reservationList, ReservationDTO.class);
    }

    @Override
    public void delete(Long id) {
        Reservation reservation =getById(id);
        reservationRepository.delete(reservation);
    }

    @Override
    public Long update(ReservationDTO reservationDTO) {
        validate(reservationDTO);
        validateTime(reservationDTO);
        Reservation result = MappingHelper.map(reservationDTO, Reservation.class);
        return reservationRepository.save(result).getId();
    }

    @Override
    public Reservation getById(long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        return optionalReservation.orElse(null);
    }

    @Override
    public ReservationMessageResponse save(ReservationDTO reservationDTO) {
        validate(reservationDTO);
        validateIsEmpty(reservationDTO);
        validateTime(reservationDTO);
        Reservation reservation = MappingHelper.map(reservationDTO, Reservation.class);
        ReservationMessageResponse reservationMessageResponse = new ReservationMessageResponse();
        reservationMessageResponse.setId(reservationRepository.save(reservation).getId());
        reservationMessageResponse.setMessage(ExceptionDescription.THE_VEHICLE_INSPECTION_STATION_HAS_BEEN_RESERVED_SUCCESSFULLY.getDescription());
        return reservationMessageResponse;
    }
}
