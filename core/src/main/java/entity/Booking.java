package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Booking {

    private String id;
    private User user;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingStatus status;
    private BigDecimal totalPrice;

    private LocalDate createdOn;

    public Booking(User pUser, Car pCar, LocalDate pStartDate, LocalDate pEndDate, BookingStatus pStatus) {

        validateInput(pUser, pCar, pStartDate, pEndDate);

        this.car = pCar;
        this.startDate = pStartDate;
        this.endDate = pEndDate;
        this.status = pStatus;
        this.createdOn = LocalDate.now();

        BigDecimal calculatedTotalPrice = this.calculateTotalPrice();
        validateTotalPrice(calculatedTotalPrice);

        this.totalPrice = calculatedTotalPrice;
    }

    public Booking(User pUser, Car pCar, LocalDate pStartDate, LocalDate pEndDate) {

        validateInput(pUser, pCar, pStartDate, pEndDate);

        this.car = pCar;
        this.startDate = pStartDate;
        this.endDate = pEndDate;
        this.createdOn = LocalDate.now();


        BigDecimal calculatedTotalPrice = this.calculateTotalPrice();
        validateTotalPrice(calculatedTotalPrice);

        this.totalPrice = calculatedTotalPrice;
    }

    private void validateInput(User pUser, Car pCar, LocalDate pStartDate, LocalDate pEndDate) {
        if (pUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = pUser;

        if (pCar == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }
        if (pStartDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (pEndDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        if (pStartDate.isAfter(pEndDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        if (!pCar.isAvailable()) {
            throw new IllegalArgumentException("Car is not available to be booked");
        }
    }

    public void setUser(User pUser) {
        if (pUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = pUser;
    }

    public void setCar(Car pCar) {
        if (pCar == null) {
            throw new IllegalArgumentException("Car cannot be null");
        }

        if (!pCar.isAvailable()) {
            throw new IllegalArgumentException("Car is not available to be booked");
        }
        this.car = pCar;
    }

    public void setStartDate(LocalDate pStartDate) {
        if (pStartDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }

        this.startDate = pStartDate;
    }

    public void setEndDate(LocalDate pEndDate) {
        if (pEndDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        this.endDate = pEndDate;
    }

    private void setTotalPrice(BigDecimal pTotalPrice) {
        if (pTotalPrice == null || pTotalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total price of booking must be positive");
        }
        this.totalPrice = pTotalPrice;
    }

    private int getNumberOfDays() {
        if (startDate != null && endDate != null) {
            return startDate.until(endDate).getDays();
        }
        return 0;
    }

    private BigDecimal calculateTotalPrice() {
        return this.car != null ?
                this.car.getPricePerDay().multiply(new BigDecimal(getNumberOfDays())) : null;
    }

    private void validateTotalPrice(BigDecimal pTotalPrice) {
        if (pTotalPrice == null || pTotalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total price of booking must be positive");
        }
    }

}