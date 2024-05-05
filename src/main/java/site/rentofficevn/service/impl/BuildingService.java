package site.rentofficevn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.converter.BuildingConverter;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
import site.rentofficevn.dto.request.BuildingDeleteRequest;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.UserEntity;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.service.IBuildingService;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    BuildingConverter buildingConverter;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<BuildingDTO> findAll() {
        List<BuildingDTO> results = new ArrayList<>();
        List<BuildingEntity> buildingEntities =buildingRepository.findAll();
        for(BuildingEntity item : buildingEntities){
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
            results.add(buildingDTO);
        }
        return results;
    }

    @Override
    public BuildingDTO createBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntityCustom(buildingDTO);
        BuildingDTO buildingDTOAfter = buildingConverter.convertToDTOCustom(buildingRepository.save(buildingEntity));
        return buildingDTOAfter;
    }


    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingSearchResponse> results = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = convertParamToBuilder(buildingSearchRequest);

        try {
            List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(buildingSearchBuilder);
            return buildingEntities.stream()
                    .map(entity -> buildingConverter.convertFromEntitytoBuildingSearchResponse(entity))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        if (id != null) {
            BuildingEntity buildingEntity = buildingRepository.findById(id).get();
            BuildingDTO buildingDTO = buildingConverter.convertToDTOCustom(buildingEntity);
            return buildingDTO;
        }
        return null;
    }

    @Override
    @Transactional
    public BuildingDTO updateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntityCustom(buildingDTO); // trả ra cho dto
        try {
            return buildingConverter.convertToDTOCustom(buildingRepository.save(buildingEntity)); // sửa
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error building update in service");
        }
        return null;
    }

    @Override
    @Transactional
    public void removeBuilding(BuildingDeleteRequest buildingDeleteRequest)  {
        if (buildingDeleteRequest.getBuildingId() != null) {
            buildingRepository.deleteByIdIn(buildingDeleteRequest.getBuildingId());
        }
    }

    @Override
    @Transactional
    public void assignmentBuilding(AssignmentBuildingRequest assignmentBuildingRequest, Long buildingID) {
        List<UserEntity> userEntities = assignmentBuildingRequest.getStaffIds().stream()
                .map(id -> userRepository.findOnedById(id.longValue()))
                .collect(Collectors.toList());
        BuildingEntity buildingEntity = buildingRepository.findById(buildingID)
                .orElseThrow(() -> new EntityNotFoundException("Building not found with id: " + buildingID));
        buildingRepository.assignmentBuilding(userEntities, buildingEntity);
    }


    private BuildingSearchBuilder convertParamToBuilder(BuildingSearchRequest buildingSearchRequest) {
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
}