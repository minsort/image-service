package com.project.map.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileModel {

    private String id;
    private String ownershipType;
    private String zoneNilai;
    private String penggunaan;
    private String area;
    private String nib;
    private String uuid;
    private String address;
    private String center;
    private String perimetr;

}
