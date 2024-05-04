package site.rentofficevn.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;
import site.rentofficevn.enums.DistrictsEnum;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    public BuildingSearchResponse convertFromEntitytoBuildingSearchResponse(BuildingEntity buildingEntity) {

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
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
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

    public BuildingDTO convertToDTOCustom(BuildingEntity entity) {
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        // rent area
        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(entity.getId());
        String rentAreaString = rentAreaEntities.stream()
                .map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue())).collect(Collectors.joining(", "));
        result.setRentArea(rentAreaString);

        // types
        // db: NGUYEN_CAN, NOI_THAT
        if (entity.getTypes() != null) {
            String[] arrTypes = entity.getTypes().trim().split(",");
            List<String> types = Arrays.stream(arrTypes)
                    .map(String::trim)
                    .collect(Collectors.toList());
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
            List<RentAreaEntity> rentareaEntities = Arrays.stream(dto.getRentArea().replaceAll(" ", "").trim().split(","))
                    .map(item -> {
                        RentAreaEntity rentareaEntity = new RentAreaEntity();
                        rentareaEntity.setBuilding(result);
                        rentareaEntity.setValue(ValidateUtils.parseInteger(item));
                        return rentareaEntity;
                    })
                    .collect(Collectors.toList());
            result.setRentAreas(rentareaEntities);
        }
        return result;
    }

}
