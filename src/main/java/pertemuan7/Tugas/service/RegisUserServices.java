package main.java.pertemuan7.Tugas.service;


import java.io.IOException;

import okhttp3.*;


public class RegisUserServices {
    private OkHttpClient client;
    private String apiBaseUrl = "http://localhost:8080/api/auth"; // Your local endpoint URL

    public RegisUserServices() {
        this.client = new OkHttpClient(); // OkHttp client to handle HTTP requests
    }

    // Register the user by sending a POST request to the registration API
    public String registerUser(String username, String email, String password) throws IOException {
        // Prepare the request body as JSON
        String json = "{"
                + "\"username\":\"" + username + "\","
                + "\"email\":\"" + email + "\","
                + "\"password\":\"" + password + "\","
                + "\"isGoogle\":false"  // As this is a manual registration
                + "}";

        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        // Create the request
        Request request = new Request.Builder()
                .url(apiBaseUrl + "/register") // Register endpoint
                .post(body)
                .addHeader("X-Api-Key", "secret")  // Add your API key if needed
                .addHeader("Accept", "application/json")
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return "Registration successful: " + responseBody;  // Return success message
            } else {
                return "Registration failed: " + response.message();  // Return failure message
            }
        }
    }
}
