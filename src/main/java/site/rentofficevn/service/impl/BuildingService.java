package site.rentofficevn.service.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.converter.BuildingConverter;
import site.rentofficevn.converter.RentAreaConverter;
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
        // Lưu hoặc cập nhật building
        BuildingEntity buildingEntity = buildingRepository.save(buildingConverter.convertToEntityCustom(buildingDTO));
        try {
            List<RentAreaDTO> newRentAreas = rentAreaConverter.convertToRentArea(buildingEntity.getId(), buildingDTO);
            if (buildingDTO.getId() != null ) {
                BuildingEntity foundBuilding = buildingRepository.findById(buildingDTO.getId())
                        .orElseThrow(() -> new NotFoundException("Building not found!"));
                buildingEntity.setImage(foundBuilding.getImage());
                if( buildingDTO.getRentArea() != null) {
                    // Lấy danh sách rent areas cũ từ cơ sở dữ liệu
                    List<RentAreaEntity> oldRentAreas = rentAreaRepository.findByBuilding(buildingEntity);
                    // Xác định rent areas cần xóa và cần thêm
                    List<RentAreaEntity> rentAreasToDelete = new ArrayList<>();
                    List<RentAreaEntity> rentAreasToAdd = new ArrayList<>();
                    // Tạo danh sách các giá trị rent areas cần xóa và cần thêm
                    List<Integer> oldRentAreaValues = oldRentAreas.stream().map(RentAreaEntity::getValue).collect(Collectors.toList());
                    List<Integer> newRentAreaValues = newRentAreas.stream().map(RentAreaDTO::getValue).collect(Collectors.toList());
                    // Xác định rent areas cần xóa
                    for (RentAreaEntity oldRentArea : oldRentAreas) {
                        if (!newRentAreaValues.contains(oldRentArea.getValue())) {
                            rentAreasToDelete.add(oldRentArea);
                        }
                    }
                    // Xác định rent areas cần thêm
                    for (RentAreaDTO newRentArea : newRentAreas) {
                        if (!oldRentAreaValues.contains(newRentArea.getValue())) {
                            RentAreaEntity newRentAreaEntity = rentAreaConverter.convertToEntity(newRentArea);
                            newRentAreaEntity.setBuilding(buildingEntity);
                            rentAreasToAdd.add(newRentAreaEntity);
                        }
                    }
                    // Xóa rent areas cũ
                    rentAreaRepository.deleteAll(rentAreasToDelete);
                    rentAreaRepository.saveAll(rentAreasToAdd);
                }
            }else {
                rentAreaRepository.saveAll(newRentAreas.stream().map(rentAreaConverter::convertToEntity).collect(Collectors.toList()));
            }
            saveThumbnail(buildingDTO, buildingEntity);
            // Chuyển đổi entity thành DTO và trả về
            return buildingConverter.convertToDTOCustom(buildingEntity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error building update in service");
        }
        return null;
    }
    @Override
    @Transactional
    public void delete(List<Long> buildingIds) {
        for (Long item : buildingIds) {
            BuildingEntity buildingDelete = buildingRepository.findById(item).get();

            if (buildingDelete.getRentAreas().size() > 0) {
                rentAreaRepository.deleteByBuilding_Id(buildingDelete.getId());
            }
            if (buildingDelete.getAssignBuildings().size() > 0) {
                assignmentBuildingRepository.deleteByBuilding_Id(buildingDelete.getId());
            }
            buildingRepository.deleteById(buildingDelete.getId());
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
   public void assignmentBuilding(AssignmentBuildingRequest assignmentBuildingRequest, Long buildingID) {
       // Lấy danh sách người dùng từ assignmentBuildingRequest
       List<Long> userIds = assignmentBuildingRequest.getStaffIds().stream().map(Long::valueOf).collect(Collectors.toList());
       List<UserEntity> users = userRepository.findAllById(userIds);

       Optional<BuildingEntity> buildingOptional = buildingRepository.findById(buildingID);
       if (buildingOptional.isPresent()) {
           BuildingEntity buildingEntity = buildingOptional.get();
           List<AssignBuildingEntity> oldAssignments = assignmentBuildingRepository.findByBuilding(buildingEntity);
           List<AssignBuildingEntity> oldAssignmentsCopy = new ArrayList<>(oldAssignments);
           oldAssignmentsCopy.removeIf(oldAssignment -> users.stream().anyMatch(newUser -> oldAssignment.getUser().getId().equals(newUser.getId())));
           assignmentBuildingRepository.deleteAll(oldAssignmentsCopy);
           List<AssignBuildingEntity> newAssignments = users.stream()
                   .filter(newUser -> oldAssignments.stream().noneMatch(oldAssignment -> oldAssignment.getUser().getId().equals(newUser.getId())))
                   .map(user -> {
                       AssignBuildingEntity assignment = new AssignBuildingEntity();
                       assignment.setUser(user);
                       assignment.setBuilding(buildingEntity);
                       return assignment;
                   })
                   .collect(Collectors.toList());
           // Lưu danh sách assignment mới vào cơ sở dữ liệu
           assignmentBuildingRepository.saveAll(newAssignments);
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