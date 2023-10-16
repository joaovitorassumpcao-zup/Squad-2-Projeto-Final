package com.zup.StudyGoals.presentation.apiclient;

import okhttp3.*;

import java.io.IOException;

public class ApiClient {

    private final OkHttpClient client = new OkHttpClient();

    private final String LOCAL_URL = "http://localhost:8080/api";

    public String getRequest(String getEndpoint) throws IOException {
        Request request = new Request.Builder()
                .url(LOCAL_URL + getEndpoint)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }

    public ResponseBody postRequest(RequestBody formBody, String postEndpoint) throws IOException {
        Request request = new Request.Builder()
                .url(LOCAL_URL + postEndpoint)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body();
    }
}
