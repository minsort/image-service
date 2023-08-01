package com.project.map.service;

import com.project.map.dto.ProfileDto;
import com.project.map.model.ProfileModel;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Convert {

    public static ProfileModel convertToProfileModel(ProfileDto profileDto) {
        ProfileModel profileModel = new ProfileModel();
        profileModel.setId(String.valueOf(profileDto.getId()));
        profileModel.setOwnershipType(profileDto.getOwnershipType());
        profileModel.setZoneNilai(profileDto.getZoneNilai());
        profileModel.setPenggunaan(profileDto.getPenggunaan());
        profileModel.setArea(String.valueOf(profileDto.getArea()));
        profileModel.setNib(String.valueOf(profileDto.getNib()));
        profileModel.setUuid(profileDto.getUuid().toString());
        profileModel.setAddress(profileDto.getAddress());
        profileModel.setCenter(
                profileDto.getCenter().stream()
                        .map(Object::toString) //
                        .collect(Collectors.joining(",")));
        profileModel.setPerimetr(String.valueOf(profileDto.getPerimetr()));

        return profileModel;
    }

}
