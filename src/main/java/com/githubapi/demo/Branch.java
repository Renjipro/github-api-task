package com.githubapi.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public record Branch(String name, String sha) {
    @JsonCreator
    public Branch(@JsonProperty("name") String name, @JsonProperty("commit") JsonNode commit) {
        this(name, commit.get("sha").asText());
    }
}
