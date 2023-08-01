package com.project.map.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    @JsonProperty("id")
    private long id;
    @JsonProperty("ownershipType")
    private String ownershipType;
    @JsonProperty("zoneNilai")
    private String zoneNilai;
    @JsonProperty("penggunaan")
    private String penggunaan;
    @JsonProperty("area")
    private long area;
    @JsonProperty("nib")
    private long nib;
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("address")
    private String address;
    @JsonProperty("center")
    private List<Double> center;
    @JsonProperty("perimetr")
    private long perimetr;
    @JsonProperty("snapshot")
    private String snapshot;

}
