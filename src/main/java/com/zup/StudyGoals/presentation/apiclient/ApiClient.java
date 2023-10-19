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

    // POST com form
    public ResponseBody postRequest(RequestBody formBody, String postEndpoint) throws IOException {
        Request request = new Request.Builder()
                .url(LOCAL_URL + postEndpoint)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            assert response.body() != null;
            return response.body();
        }
    }

    // POST simples sem parametros
    public ResponseBody postRequest(String postEndpoint) throws IOException {
        Request request = new Request.Builder()
                .url(LOCAL_URL + postEndpoint)
                .build();
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            assert response.body() != null;
            return response.body();
        }
    }

    // POST com um parametro
    public ResponseBody postRequest(String postEndpoint, String paramChave, String paramValor) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .addEncoded(paramChave, paramValor)
                .build();
        Request request = new Request.Builder()
                .url(LOCAL_URL + postEndpoint)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            assert response.body() != null;
            return response.body();
        }
    }

    public ResponseBody putRequest(RequestBody formBody, String postEndpoint) throws IOException {
        Request request = new Request.Builder()
                .url(LOCAL_URL + postEndpoint)
                .put(formBody)
                .build();
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            assert response.body() != null;
            return response.body();
        }
    }

    public String deleteRequest(String deleteEndpoint) throws IOException {
        Request request = new Request.Builder()
                .url(LOCAL_URL + deleteEndpoint)
                .delete()
                .build();
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }
}
