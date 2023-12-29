package io.github.dft.sendle.model.quote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuoteResponse {

    private Quote quote;
    private PriceBreakdown priceBreakdown;
    private TaxBreakdown taxBreakdown;
    private String planName;
    private Eta eta;
    private Route route;
    private String allowedPackaging;
    private ErrorDetail errorDetail;
}