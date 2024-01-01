package io.github.dft.sendle.model.quote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxBreakdown {

    private Amount gst;
}