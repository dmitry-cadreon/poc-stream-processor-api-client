package com.example.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntityA {
    @JsonProperty("field.int")
    private int fieldInt;
}
