package com.zup.StudyGoals.presentation.apiclient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiClient {

    private static OkHttpClient client = new OkHttpClient();

    private final String LOCAL_URL = "https://localhost:8080/api";

    public String getRequest(String getEndpoint) throws IOException {
        Request request = new Request.Builder()
                .url(LOCAL_URL + getEndpoint)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }
}
