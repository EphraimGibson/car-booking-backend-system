package dtos;


import entity.Car;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class CreateBookingDto {
    private final User user;
    private final Car car;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int numberOfDays;
    private final BigDecimal totalPrice;

    public int getNumberOfDays() {

        if (startDate != null && endDate != null) {
            return startDate.until(endDate).getDays();
        }
        return 0;
    }

    public BigDecimal getTotalPrice() {
        return this.car != null ?
                this.car.getPricePerDay().multiply(new BigDecimal(getNumberOfDays())) : null;
    }
}