package site.rentofficevn.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
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
import org.apache.commons.codec.binary.Base64;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.UserEntity;
import site.rentofficevn.enums.BuildingTypesEnum;
import site.rentofficevn.enums.DistrictsEnum;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.service.IBuildingService;
import site.rentofficevn.utils.UploadFileUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BuildingService.class);
    private final BuildingRepository buildingRepository;
    private final BuildingConverter buildingConverter;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UploadFileUtils uploadFileUtils;

    @Value("D:/estatedata")
    private String dirDefault;

    @Autowired
    public BuildingService( UploadFileUtils uploadFileUtils, UserConverter userConverter, UserRepository userRepository, BuildingConverter buildingConverter, BuildingRepository buildingRepository) {
        this.uploadFileUtils = uploadFileUtils;
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.buildingConverter = buildingConverter;
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<BuildingSearchResponse> findByCondition(BuildingSearchRequest buildingSearchRequest, PageRequest pageRequest) {
        try {
            List<BuildingEntity> foundBuildings = buildingRepository.findBuilding(buildingConverter.convertParamToBuilder(buildingSearchRequest), pageRequest);
            return foundBuildings.stream().map(buildingConverter::toSearchResponse).collect(Collectors.toList());
        } catch (IllegalArgumentException ex) {
            return Collections.emptyList();
        }
    }


    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = Optional.of(buildingRepository.findById(id)).get()
                .orElseThrow(() -> new NotFoundException("Buliding not found!"));
        return buildingConverter.toDTO(buildingEntity);
    }

    @Override
    public Map<String, String> getDistrictMap() {
        return Arrays.stream(DistrictsEnum.values())
                .collect(Collectors.toMap(Enum::toString, DistrictsEnum::getDistrictValue, (a, b) -> b, LinkedHashMap::new));
    }

    @Override
    public Map<String, String> getBuildingTypeMap() {
        return Arrays.stream(BuildingTypesEnum.values()).collect(Collectors.toMap(Enum::toString, BuildingTypesEnum::getBuildingTypeValue));
    }

    @Override
    @Transactional
    public void delete(List<Long> buildingIds) {
       if(!buildingIds.isEmpty()){
           Long count = buildingRepository.countByIdIn(buildingIds);
           if(count != buildingIds.size()){
               throw new NotFoundException("Some buildings not found!");
           }
           buildingRepository.deleteByIdIn(buildingIds);
       }
    }


    @Override
    @Transactional
    public BuildingDTO save(BuildingDTO buildingDTO)  throws IllegalArgumentException{
        Long buildingId = buildingDTO.getId();
        BuildingEntity buildingEntity = buildingConverter.toEntity(buildingDTO);
        if(buildingId != null){
            BuildingEntity foundBuilding = Optional.of(buildingRepository.findById(buildingId)).get()
                    .orElseThrow(() -> new NotFoundException("Building not found!"));
            buildingEntity.setCreatedDate(foundBuilding.getCreatedDate());
            buildingEntity.setCreatedBy(foundBuilding.getCreatedBy());
            buildingEntity.setModifiedDate(new Date());
            buildingEntity.setModifiedBy(foundBuilding.getModifiedBy());
            buildingEntity.setImage(foundBuilding.getImage());
            buildingEntity.setUserEntities(foundBuilding.getUserEntities());
        }
        saveThumbnail(buildingDTO, buildingEntity);
        return buildingConverter.toDTO(buildingRepository.save(buildingEntity));
    }
    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File(dirDefault + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

    @Override
    public List<AssignmentStaffResponse> loadStaffByBuildingId(Long buildingId) {
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(SystemConstant.ACTIVE_STATUS, "STAFF");

        return allStaffs.stream().map(staff ->
                {
                    AssignmentStaffResponse assignmentStaffResponse = userConverter.toAssignmentStaffResponse(staff);
                    for (BuildingEntity building : staff.getBuildingEntities()) {
                        if (buildingId.equals(building.getId())){
                            assignmentStaffResponse.setChecked("checked");
                            break;
                        }
                    }
                    return assignmentStaffResponse;
                }
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void assignmentBuildingToStaffs(AssignmentBuildingRequest assignmentBuildingRequest) {
        Long buildingId = assignmentBuildingRequest.getBuildingId();

        List<Long> staffIdRequest = assignmentBuildingRequest.getStaffIds();

        BuildingEntity foundBuilding = Optional.of(buildingRepository.findById(buildingId)).get()
                .orElseThrow(() -> new NotFoundException("Building not found!"));
        List<UserEntity> foundUsers = userRepository.findByIdIn(staffIdRequest);
        foundBuilding.setUserEntities(foundUsers);
        buildingRepository.save(foundBuilding);
        }

    @Override
    public int countByCondition(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countByCondition(buildingConverter.convertParamToBuilder(buildingSearchRequest));
    }
}

