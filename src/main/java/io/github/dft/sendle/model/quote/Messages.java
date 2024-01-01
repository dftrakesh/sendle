package io.github.dft.sendle.model.quote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Messages {

    private List<String> weightValue;
    private List<String> pickupSuburb;
    private List<String> pickupPostcode;
    private List<String> pickupCountry;
    private List<String> deliveryCountry;
    private List<String> deliverySuburb;
    private List<String> deliveryPostcode;
    private List<String> weightUnits;
}