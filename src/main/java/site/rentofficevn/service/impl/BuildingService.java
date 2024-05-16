package site.rentofficevn.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.converter.BuildingConverter;
import site.rentofficevn.converter.UserConverter;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.AssignmentStaffResponse;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.entity.AssignmentBuildingEntity;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;
import site.rentofficevn.entity.UserEntity;
import site.rentofficevn.enums.BuildingTypesEnum;
import site.rentofficevn.enums.DistrictsEnum;
import site.rentofficevn.repository.AssignmentBuildingRepository;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.service.IBuildingService;
import site.rentofficevn.utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BuildingService.class);
    private final BuildingRepository buildingRepository;
    private final BuildingConverter buildingConverter;
    private final UserRepository userRepository;
    private final RentAreaRepository rentAreaRepository;
    private final AssignmentBuildingRepository assignmentBuildingRepository;
    private final UserConverter userConverter;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository, BuildingConverter buildingConverter, UserRepository userRepository, RentAreaRepository rentAreaRepository, AssignmentBuildingRepository assignmentBuildingRepository, UserConverter userConverter) {
        this.buildingRepository = buildingRepository;
        this.buildingConverter = buildingConverter;
        this.userRepository = userRepository;
        this.rentAreaRepository = rentAreaRepository;
        this.assignmentBuildingRepository = assignmentBuildingRepository;
        this.userConverter = userConverter;
    }


    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> foundBuildings = buildingRepository.findBuilding(buildingConverter.convertParamToBuilder(buildingSearchRequest));
        return foundBuildings.stream().map(buildingConverter::toSearchResponse).collect(Collectors.toList());
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = Optional.of(buildingRepository.findById(id)).get()
                .orElseThrow(() -> new NotFoundException("Buliding not found!"));
        return buildingConverter.toDTO(buildingEntity);
    }

    @Override
    @Transactional
    public void delete(List<Long> buildingIds) {
        if (buildingIds != null && !buildingIds.isEmpty()) {
            for (Long item : buildingIds) {
                BuildingEntity foundBuilding = buildingRepository.findById(item).orElse(null);
                if (foundBuilding != null) {
                    rentAreaRepository.deleteAll(foundBuilding.getRentAreas());
                    assignmentBuildingRepository.deleteAll(foundBuilding.getAssignBuildings());
                    buildingRepository.delete(foundBuilding);
                }
            }
        }
    }


    @Override
    public Map<String, String> getDistrictMap() {
        return Arrays.stream(DistrictsEnum.values()).collect(Collectors.toMap(Enum::toString, DistrictsEnum::getDistrictValue));
    }

    @Override
    public Map<String, String> getBuildingTypeMap() {
        return Arrays.stream(BuildingTypesEnum.values()).collect(Collectors.toMap(Enum::toString, BuildingTypesEnum::getBuildingTypeValue));
    }

    @Override
    public BuildingDTO save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.toEntity(buildingDTO);
        processRentArea(buildingEntity, buildingDTO);
        BuildingEntity savedBuilding = buildingRepository.save(buildingEntity);
        rentAreaRepository.saveAll(savedBuilding.getRentAreas());
        return buildingConverter.toDTO(savedBuilding);
    }

    private void processRentArea(BuildingEntity buildingEntity, BuildingDTO buildingDTO) {
        boolean isUpdating = buildingDTO.getId() != null;
        if(isUpdating){
            processInCaseUpdating(buildingEntity, buildingDTO);
        }else{
            processInCaseCreatingNew(buildingEntity, buildingDTO);
        }
    }

    private void processInCaseCreatingNew(BuildingEntity buildingEntity, BuildingDTO buildingDTO) {
       String rentArea = buildingDTO.getRentArea();
       if(!StringUtils.isNullOrEmpty(rentArea)){
           List<String> convertedRentAreas = Arrays.asList(rentArea.split(","));

           List<RentAreaEntity> rentAreas = convertedRentAreas.stream().map((String value) -> {
               RentAreaEntity rentAreaEntity = new RentAreaEntity();
               rentAreaEntity.setValue(Integer.parseInt(value));
               rentAreaEntity.setBuilding(buildingEntity);
               return rentAreaEntity;
           }).collect(Collectors.toList());
           buildingEntity.setRentAreas(rentAreas);
       }
    }

    private void processInCaseUpdating(BuildingEntity buildingEntity, BuildingDTO buildingDTO) {
        List<Integer> validRentAreaValues = getValidRentAreaValues(buildingDTO.getRentArea());
        List<RentAreaEntity> currentRentAreas = rentAreaRepository.findByBuildingId(buildingDTO.getId());
        if(validRentAreaValues.isEmpty()){
            rentAreaRepository.deleteAll(currentRentAreas);
        }else{
            List<RentAreaEntity> deletedRentAreas = new ArrayList<>();
            List<RentAreaEntity> savedRentAreas = new ArrayList<>();
            List<Integer> ignoredValues = new ArrayList<>();
            for(RentAreaEntity currentRentArea : currentRentAreas){
                Integer value = currentRentArea.getValue();
                if(validRentAreaValues.contains(value)){
                    ignoredValues.add(value);
                }else{
                    deletedRentAreas.add(currentRentArea);
                }
            }

            for(Integer value : validRentAreaValues){
                if(!ignoredValues.contains(value)){
                    RentAreaEntity rentAreaEntity = new RentAreaEntity();
                    rentAreaEntity.setValue(value);
                    rentAreaEntity.setBuilding(buildingEntity);
                    savedRentAreas.add(rentAreaEntity);
                }

            }
            rentAreaRepository.deleteAll(deletedRentAreas);
            rentAreaRepository.saveAll(savedRentAreas);
        }
    }

    private List<Integer> getValidRentAreaValues(String rentArea) {
        List<Integer> result = new ArrayList<>();
        if(!StringUtils.isNullOrEmpty(rentArea)){
            String []strRentAreas = rentArea.split(",");
            for(String strValue : strRentAreas){
                try{
                    Integer intValue = Integer.parseInt(strValue);
                    result.add(intValue);
                }catch (Exception e){
                    LOGGER.info("Error when parse to int: " + e.getMessage());
                }
            }
        }
        return result;
    }

    @Override
    public List<AssignmentStaffResponse> loadStaffByBuildingId(Long buildingId) {
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(SystemConstant.ACTIVE_STATUS, "STAFF");
        List<Long> currentStaffIds = assignmentBuildingRepository.findByBuildingId(buildingId).stream()
                .map(a -> a.getUser().getId()).collect(Collectors.toList());

        return allStaffs.stream()
                .map(staff -> userConverter.toAssignmentStaffResponse(staff, currentStaffIds.contains(staff.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public void assignmentBuildingToStaffs(AssignmentBuildingRequest assignmentBuildingRequest) {
        Long buildingId = assignmentBuildingRequest.getBuildingId();

        List<Long> staffIdRequest = assignmentBuildingRequest.getStaffIds();

        BuildingEntity foundBuilding = Optional.of(buildingRepository.findById(buildingId)).get()
                .orElseThrow(() -> new NotFoundException("Building not found!"));
        Map<Long,UserEntity> idToUser = userRepository.findByIdIn(staffIdRequest).stream()
                .collect(Collectors.toMap(UserEntity::getId, user -> user));

        List<AssignmentBuildingEntity> currentAssignments = foundBuilding.getAssignBuildings();
        if(staffIdRequest.isEmpty()){
            assignmentBuildingRepository.deleteAll(currentAssignments);
        }else{
            List<AssignmentBuildingEntity> deletedAssignments = new ArrayList<>();
            List<AssignmentBuildingEntity> savedAssignments = new ArrayList<>();
            List<Long> ignoredIds = new ArrayList<>();

            for(AssignmentBuildingEntity assignmentBuilding : currentAssignments){
                Long staffId = assignmentBuilding.getUser().getId();
                if(staffIdRequest.contains(staffId)){
                    ignoredIds.add(staffId);}
                else{
                    deletedAssignments.add(assignmentBuilding);
                }
            }

            for(Long staffId : staffIdRequest){
                if(!ignoredIds.contains(staffId)){
                    AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
                    assignmentBuildingEntity.setBuilding(foundBuilding);
                    assignmentBuildingEntity.setUser(idToUser.get(staffId));
                    savedAssignments.add(assignmentBuildingEntity);
                }
            }
            assignmentBuildingRepository.deleteAll(deletedAssignments);
            assignmentBuildingRepository.saveAll(savedAssignments);
        }
    }
}