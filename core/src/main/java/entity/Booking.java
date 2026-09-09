package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Booking {

    private String id = UUID.randomUUID().toString();
    private User user;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal totalPrice;

    private BookingStatus status;

    private LocalDate createdOn;

    public Booking(User pUser, Car pCar, LocalDate pStartDate, LocalDate pEndDate, BigDecimal pTotalPrice, BookingStatus pStatus) {
        validateInput(pUser, pCar, pStartDate, pEndDate, pTotalPrice);

        this.car = pCar;
        this.startDate = pStartDate;
        this.endDate = pEndDate;
        this.totalPrice = pTotalPrice;
        this.status = pStatus;
        this.createdOn = LocalDate.now();
    }

    private void validateInput(User pUser, Car pCar, LocalDate pStartDate, LocalDate pEndDate, BigDecimal pTotalPrice) {
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

        if (pTotalPrice == null || pTotalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
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

    public void setTotalPrice(BigDecimal pTotalPrice) {
        if (pTotalPrice == null || pTotalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.totalPrice = pTotalPrice;
    }


}