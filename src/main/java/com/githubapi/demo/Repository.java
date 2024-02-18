package com.githubapi.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Repository(int id,
                         String name,
                         @JsonProperty("fork") boolean isFork) {
}
