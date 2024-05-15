package site.rentofficevn.converter;

import org.apache.commons.lang.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;
import site.rentofficevn.enums.DistrictsEnum;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.utils.StringUtils;
import site.rentofficevn.utils.ValidateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    /*ublic BuildingSearchResponse convertFromEntitytoBuildingSearchResponse(BuildingEntity buildingEntity) {

        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);

        //Xử lý District
        String districtName = "";
        String testName = buildingEntity.getDistrict();
        if (testName != null) {
            for (DistrictsEnum district : DistrictsEnum.values()) {
                if (testName.equals(district.name())) {
                    districtName = district.getDistrictValue();
                    break; // Kết thúc vòng lặp khi tìm thấy tên quận
                }
            }
        }
        buildingSearchResponse.setAddress(buildingEntity.getStreet() + " - " + buildingEntity.getWard() + " - " + districtName);

        //Xử lý rent area -> By Stream API
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuilding(buildingEntity);
        String rentAreaString = rentAreaEntities.stream()
                .map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue())).collect(Collectors.joining(", "));
        buildingSearchResponse.setEmptyArea(rentAreaString);

        return buildingSearchResponse;
    }

    public BuildingDTO convertToDto(BuildingEntity entity) {
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        return result;
    }

    public BuildingEntity convertToEntity(BuildingDTO dto) {
        BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);
        return result;
    }

    // convert entity -> dto custom
    public BuildingDTO convertToDTOCustom(BuildingEntity entity) {
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        // rent area
        List<String> rentareas = new ArrayList<>();
        if (entity.getRentAreas() != null) {
            for (RentAreaEntity itemRentArea : entity.getRentAreas()) {
                rentareas.add(String.valueOf(itemRentArea.getValue()));
            }
            String rentArea = String.join(",", rentareas);  // tách = dấu phẩy
            result.setRentArea(rentArea);
        }

        // types
        // db: NGUYEN_CAN, NOI_THAT
        if (entity.getTypes() != null) {
            List<String> types = new ArrayList<>();
            String[] arrTypes = entity.getTypes().trim().split(",");  // tách = dấu phẩy
            for (String item : arrTypes) {
                types.add(item);
            }
            result.setTypes(types);
        }
        return result;
    }

    // tạo mới -> lưu
    // convert dto -> entity custom
    public BuildingEntity convertToEntityCustom(BuildingDTO dto) {
        BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);

        if (dto.getTypes() != null) {
            String type = String.join(",", dto.getTypes());
            result.setTypes(type);
        }
        if (!ValidateUtils.checkNullEmpty(dto.getRentArea())) {
            List<RentAreaEntity> rentareaEntities = new ArrayList<>();
            String[] arrAreaRent = dto.getRentArea().replaceAll(" ", "").trim().split(","); // tách chuỗi qa dau ','
            for (String item : arrAreaRent) {
                RentAreaEntity rentareaEntity = new RentAreaEntity();
                rentareaEntity.setBuilding(result); //
                rentareaEntity.setValue(ValidateUtils.parseInteger(item));
                rentareaEntities.add(rentareaEntity);
            }
            result.setRentAreas(rentareaEntities);
        }
        return result;
    }

    public BuildingSearchBuilder convertParamToBuilder(BuildingSearchRequest buildingSearchRequest) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
*/
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

        result.setCreatedDate(buildingEntity.getCreatedDate());

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
