package site.rentofficevn.service.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.converter.BuildingConverter;
import site.rentofficevn.converter.RentAreaConverter;
import site.rentofficevn.dto.AssignmentDTO;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.RentAreaDTO;
import site.rentofficevn.dto.request.AssignmentBuildingRequest;
import site.rentofficevn.dto.request.BuildingSearchRequest;
import site.rentofficevn.dto.response.BuildingSearchResponse;
import site.rentofficevn.entity.AssignBuildingEntity;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;
import site.rentofficevn.entity.UserEntity;
import site.rentofficevn.repository.AssignmentBuildingRepository;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.security.utils.SecurityUtils;
import site.rentofficevn.service.IBuildingService;
import site.rentofficevn.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import java.io.File;
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

    @Autowired
    AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingTypesService buildingTypesService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private RentAreaRepository rentAreaRepository;


    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public List<BuildingDTO> findAll() {
        List<BuildingDTO> results = new ArrayList<>();
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
            results.add(buildingDTO);
        }
        return results;
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
    public BuildingDTO createAndUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntityCustom(buildingDTO); // trả ra cho dto
        try {
            if (buildingDTO.getId() != null) {
                BuildingEntity foundBuilding = buildingRepository.findById(buildingDTO.getId())
                        .orElseThrow(() -> new NotFoundException("Building not found!"));
                buildingEntity.setImage(foundBuilding.getImage());
            }
            saveThumbnail(buildingDTO, buildingEntity);   // save thumbnail
            return buildingConverter.convertToDTOCustom(buildingRepository.save(buildingEntity)); // sửa
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error building update in service");
        }
        return null;
    }
    @Override
    @Transactional
    public void delete(List<Long> buildingIds) {
        try {
            if (!buildingIds.isEmpty()) {
                Long count = buildingRepository.countByIdIn(buildingIds);

                if (count != buildingIds.size()) {
                    throw new NotFoundException("Building not found!");
                }
                // remove buildings
                buildingRepository.deleteByIdIn(buildingIds);
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BuildingDTO getBuildingDetails(Long id) {
        BuildingDTO buildingDTO = new BuildingDTO();
        if (id != null && buildingRepository.existsById(id)) {
            buildingDTO = findBuildingById(id);
            buildingDTO.setDistricts(districtService.getDistrictByBuilding(buildingDTO));
        } else {
            buildingDTO.setDistricts(districtService.getAllDistrict());
        }
        buildingDTO.setBuildingTypes(buildingTypesService.getAll());
        return buildingDTO;
    }

    @Override
    public List<BuildingSearchResponse> pageBuilding(Pageable pageable, BuildingSearchRequest buildingSearchRequest) {
        List<BuildingSearchResponse> result = new ArrayList<>();
        // Nếu staff thì chỉ xem đc building mình quản lí
        if (SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            buildingSearchRequest.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        BuildingSearchBuilder buildingSearchBuilder = convertParamToBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.pageBuilding(pageable, buildingSearchBuilder);
        for (BuildingEntity item : buildingEntities) {
            result.add(buildingConverter.convertFromEntitytoBuildingSearchResponse(item));
        }
        return result;
    }

    @Override
    public int getTotalItems() {
        return (int) buildingRepository.countAllBuilding();
    }

    @Override
   @Transactional
   public void assignmentBuilding(AssignmentDTO assignmentDTO) {
        try {
            BuildingEntity buildingEntity = buildingRepository.findById(assignmentDTO.getBuildingid()).get();
            buildingEntity.setUserEntities(new HashSet<>(userRepository.findAllById(assignmentDTO.getStaffIds())));
            buildingRepository.save(buildingEntity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error assignmentBuilding service");
        }
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

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("D:/estatedata" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

}