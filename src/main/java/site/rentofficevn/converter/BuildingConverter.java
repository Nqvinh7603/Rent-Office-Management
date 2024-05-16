package site.rentofficevn.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.enums.DistrictsEnum;
import site.rentofficevn.utils.DateUtils;
import site.rentofficevn.utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO toDTO(BuildingEntity buildingEntity){
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);

        String buildingTypes = buildingEntity.getTypes();

        if(!StringUtils.isNullOrEmpty(buildingTypes)){
            List<String> convertedBuildingType = Arrays.asList(buildingTypes.split(","));
            buildingDTO.setTypes(convertedBuildingType);
        }

        String rentAreaString = buildingEntity.getRentAreas().stream()
                .map(rentArea -> String.valueOf(rentArea.getValue()))
                .collect(Collectors.joining(","));
        buildingDTO.setRentArea(rentAreaString);
        return buildingDTO;
    }

    public BuildingEntity toEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        List<String> buildingTypes = buildingDTO.getTypes();
        if (!buildingTypes.isEmpty()) {
            String convertedType = String.join(",", buildingTypes);
            buildingEntity.setTypes(convertedType);
        }
        return buildingEntity;
    }
    public BuildingSearchBuilder convertParamToBuilder(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
                .setName(buildingSearchRequest.getName())
                .setFloorArea(buildingSearchRequest.getFloorArea())
                .setDistrict(buildingSearchRequest.getDistrictCode())
                .setWard(buildingSearchRequest.getWard())
                .setStreet(buildingSearchRequest.getStreet())
                .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
                .setDirection(buildingSearchRequest.getDirection())
                .setLevel(buildingSearchRequest.getLevel())
                .setRentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                .setRentAreaTo(buildingSearchRequest.getRentAreaTo())
                .setRentPriceFrom(buildingSearchRequest.getRentPriceFrom())
                .setRentPriceTo(buildingSearchRequest.getRentPriceTo())
                .setManagerName(buildingSearchRequest.getManagerName())
                .setManagerPhone(buildingSearchRequest.getManagerPhone())
                .setStaffID(buildingSearchRequest.getStaffId())
                .setTypes(buildingSearchRequest.getTypes())
                .build();
        return result;
    }


    public BuildingSearchResponse toSearchResponse(BuildingEntity buildingEntity){
        BuildingSearchResponse result = modelMapper.map(buildingEntity, BuildingSearchResponse.class);

        result.setCreatedDate(DateUtils.convertDateToString(buildingEntity.getCreatedDate()));

        List<String> address = new ArrayList<>();

        address.add(buildingEntity.getStreet());
        address.add(buildingEntity.getWard());

        String districtCode = buildingEntity.getDistrict();
        if(Arrays.toString(DistrictsEnum.values()).contains(districtCode)){
            address.add(DistrictsEnum.valueOf(districtCode).getDistrictValue());
        }
        result.setAddress(address.stream()
                .filter(str -> !StringUtils.isNullOrEmpty(str))
                .collect(Collectors.joining(",")));

        String rentAreaString = buildingEntity.getRentAreas().stream()
                .map(rentArea -> String.valueOf(rentArea.getValue()))
                .collect(Collectors.joining(","));

        result.setRentAreaDescription("Diện tích còn trống: " + rentAreaString);
        return result;
    }
}
