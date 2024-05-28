package com.sp.dtos;

public class JwtResponseDTO {
    private String accessToken;

    private JwtResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static class Builder {
        private String accessToken;

        public Builder withAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public JwtResponseDTO build() {
            return new JwtResponseDTO(accessToken);
        }
    }
}
