package site.rentofficevn.service.impl;

import org.springframework.stereotype.Service;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.response.DistrictResponse;
import site.rentofficevn.enums.DistrictsEnum;
import site.rentofficevn.service.IDistrictService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService implements IDistrictService {
    @Override
    public List<DistrictResponse> getAllDistrict() {
        return Arrays.stream(DistrictsEnum.values())
                .map(item -> {
                    DistrictResponse response = new DistrictResponse();
                    response.setCode(item.name());
                    response.setName(item.getDistrictValue());
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<DistrictResponse> getDistrictByBuilding(BuildingDTO buildingDTO) {
        if(buildingDTO == null) {
            return getAllDistrict();
        }
        return Arrays.stream(DistrictsEnum.values())
                .map(item -> {
                    DistrictResponse response = new DistrictResponse();
                    response.setCode(item.name());
                    response.setName(item.getDistrictValue());
                    if (buildingDTO.getDistrict() != null && buildingDTO.getDistrict().equals(item.name())) {
                        response.setSelected("selected");
                    }
                    return response;
                })
                .collect(Collectors.toList());
    }
}
