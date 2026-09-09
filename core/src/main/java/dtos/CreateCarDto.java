package dtos;

import entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class CreateCarDto {
    private final String model;
    private final String regNumber;
    private final BigDecimal pricePerDay;
    private final Brand brand;
    private final boolean isElectric;
}