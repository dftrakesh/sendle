package io.github.dft.sendle.constantcode;

public interface ConstantCodes {

    String AUTHORIZATION = "Authorization";
    String BASIC = "Basic";
    String ACCEPT = "Accept";
    String CONTENT_TYPE = "Content-Type";
    String BASE_ENDPOINT = "https://api.sendle.com/api/";
    String QUOTE_ENDPOINT = String.format("%squote", BASE_ENDPOINT);
    String HTTP_REQUEST_CONTENT_TYPE_JOSN = "application/json";
}