package com.katkat_maker.Blog.Domain.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class authRequest {
    private String token;
    private long expiredIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(long expiredIn) {
        this.expiredIn = expiredIn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        authRequest that = (authRequest) o;
        return expiredIn == that.expiredIn && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expiredIn);
    }
}
