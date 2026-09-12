package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import utils.StringUtility;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Car {
    private String id;
    private String model;
    private String registrationNumber;
    private BigDecimal pricePerDay;
    private Brand brand;
    private boolean isElectric;
    private boolean isAvailable = true;

    public Car(String pName, String pRegistrationNumber, BigDecimal pPrice, Brand pbrand, boolean pIsElectric) {

        if (StringUtility.isStringNullOrBlank(pRegistrationNumber)) {
            throw new IllegalArgumentException("Registration number cannot be empty");
        }

        if (pPrice == null || pPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }

        registrationNumber = pRegistrationNumber;
        model = pName;
        pricePerDay = pPrice;
        brand = pbrand;
        isElectric = pIsElectric;
    }


    public void setRegistrationNumber(String pRegNumber) {
        if (StringUtility.isStringNullOrBlank(pRegNumber)) {
            throw new IllegalArgumentException("Registration number cannot be empty");
        }
        registrationNumber = pRegNumber;
    }

    public void setPricePerDay(BigDecimal pPricePerDay) {
        if (pPricePerDay == null || pPricePerDay.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }

        pricePerDay = pPricePerDay;
    }
}