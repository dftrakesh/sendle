package io.github.dft.sendle.model.quote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PriceBreakdown {

    private Amount base;
    private Amount baseTax;
    private Amount cover;
    private Amount coverTax;
    private Amount discount;
    private Amount discountTax;
    private Amount fuelSurcharge;
    private Amount fuelSurchargeTax;
}