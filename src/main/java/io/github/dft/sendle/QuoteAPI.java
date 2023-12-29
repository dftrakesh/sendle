package io.github.dft.sendle;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dft.sendle.model.SendleCredentials;
import io.github.dft.sendle.model.quote.ErrorDetail;
import io.github.dft.sendle.model.quote.QuoteResponse;
import io.github.dft.sendle.model.quote.QuoteResponseList;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.HashMap;

import static io.github.dft.sendle.constantcode.ConstantCodes.QUOTE_ENDPOINT;

public class QuoteAPI extends SendleSDK {

    private final ObjectMapper objectMapper;

    public QuoteAPI(SendleCredentials sendleCredentials) {
        super(sendleCredentials);
        objectMapper = new ObjectMapper();
    }

    public QuoteResponseList getQuote(HashMap<String, String> params) {
        URI uri = addParameters(URI.create(QUOTE_ENDPOINT), params);
        HttpRequest get = get(uri);

        Object response = getRequestWrapped(get, Object.class);

        if (!response.getClass().getSimpleName().equalsIgnoreCase("ArrayList")) {
            QuoteResponse quoteResponse = new QuoteResponse();
            QuoteResponseList quoteResponseList = new QuoteResponseList();

            quoteResponse.setErrorDetail(objectMapper.convertValue(response, ErrorDetail.class));
            quoteResponseList.add(quoteResponse);
            return quoteResponseList;
        }

        return objectMapper.convertValue(response, QuoteResponseList.class);
    }
}