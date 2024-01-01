package io.github.dft.sendle.model.quote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private Amount gross;
    private Amount net;
    private Amount tax;
}