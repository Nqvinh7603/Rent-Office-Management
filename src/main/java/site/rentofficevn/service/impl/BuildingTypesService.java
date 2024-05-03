package site.rentofficevn.service.impl;

import org.springframework.stereotype.Service;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.response.BuildingTypesResponse;
import site.rentofficevn.enums.BuildingTypesEnum;
import site.rentofficevn.service.IBuildingTypeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingTypesService implements IBuildingTypeService {
    @Override
    public List<BuildingTypesResponse> getAll() {
        return Arrays.stream(BuildingTypesEnum.values())
                .map(item -> {
                    BuildingTypesResponse types = new BuildingTypesResponse();
                    types.setCode(item.name());
                    types.setName(item.getBuildingTypeValue());
                    return types;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<BuildingTypesResponse> getAllByBuilding(BuildingDTO buildingDTO) {
        List<BuildingTypesResponse> result = new ArrayList<>();
        for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
            BuildingTypesResponse types = new BuildingTypesResponse();
            types.setCode(item.name());
            types.setName(item.getBuildingTypeValue());
            if (buildingDTO.getTypes() != null && buildingDTO.getTypes().contains(item.name())) {
                types.setChecked("checked");
            }
            result.add(types);
        }
        return result;
    }
}
