package io.github.dft.sendle;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dft.sendle.model.SendleCredentials;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.github.dft.sendle.constantcode.ConstantCodes.ACCEPT;
import static io.github.dft.sendle.constantcode.ConstantCodes.AUTHORIZATION;
import static io.github.dft.sendle.constantcode.ConstantCodes.BASIC;
import static io.github.dft.sendle.constantcode.ConstantCodes.CONTENT_TYPE;
import static io.github.dft.sendle.constantcode.ConstantCodes.HTTP_REQUEST_CONTENT_TYPE_JOSN;

public class SendleSDK {

    protected HttpClient client;
    protected SendleCredentials sendleCredentials;
    protected final ObjectMapper objectMapper;

    public SendleSDK(SendleCredentials sendleCredentials) {
        client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.sendleCredentials = sendleCredentials;
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, Class<T> tClass) {

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenComposeAsync(CompletableFuture::completedFuture)
            .thenApplyAsync(HttpResponse::body)
            .thenApplyAsync(responseBody -> convertBody(responseBody, tClass))
            .get();
    }

    @SneakyThrows
    private <T> T convertBody(String body, Class<T> tClass) {
        return objectMapper.readValue(body, tClass);
    }

    protected HttpRequest get(URI uri) {
        String originalInput = this.sendleCredentials.getSendleId() + ":" + this.sendleCredentials.getApiKey();
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

        return HttpRequest.newBuilder(uri)
            .header(AUTHORIZATION, BASIC + encodedString)
            .header(CONTENT_TYPE, HTTP_REQUEST_CONTENT_TYPE_JOSN)
            .header(ACCEPT, HTTP_REQUEST_CONTENT_TYPE_JOSN)
            .GET()
            .build();
    }

    protected URI addParameters(URI uri, HashMap<String, String> params) {
        String query = uri.getQuery();
        StringBuilder builder = new StringBuilder();

        if (query != null)
            builder.append(query);

        for (Map.Entry<String, String> entry : params.entrySet()) {

            if (!builder.toString().isEmpty())
                builder.append("&");

            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        return URI.create(uri.getScheme() + "://" + uri.getAuthority() + uri.getPath() + "?" + builder);
    }
}