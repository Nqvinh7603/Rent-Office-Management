package site.rentofficevn.service.impl;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
import site.rentofficevn.dto.response.BuildingTypesResponse;
import site.rentofficevn.dto.response.DistrictResponse;
import site.rentofficevn.entity.AssignBuildingEntity;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;
import site.rentofficevn.entity.UserEntity;
import site.rentofficevn.exception.MyException;
import site.rentofficevn.repository.AssignmentBuildingRepository;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.service.IBuildingService;

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
    private RentAreaService rentAreaService;

    @Autowired
    private RentAreaConverter rentAreaConverter;

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
        try {
            // Chuyển đổi DTO thành entity
            BuildingEntity buildingEntity = buildingConverter.convertToEntityCustom(buildingDTO);
            // Lưu hoặc cập nhật building
            buildingEntity = buildingRepository.save(buildingEntity);
            // Nếu có id của building và có rentArea, thực hiện tối ưu hóa việc xử lý rent areas
            if (buildingDTO.getId() != null && buildingDTO.getRentArea() != null) {
                // Lấy danh sách rent areas mới từ buildingDTO
                List<RentAreaDTO> newRentAreas = rentAreaConverter.convertToRentArea(buildingEntity.getId(), buildingDTO);
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
                // Lưu rent areas mới
                rentAreaRepository.saveAll(rentAreasToAdd);
            }
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
    @Transactional
    public void assignmentBuilding(AssignmentBuildingRequest assignmentBuildingRequest, Long buildingID) throws NotFoundException {
        // Lấy danh sách id của nhân viên từ AssignmentBuildingRequest
        List<Long> staffIds = assignmentBuildingRequest.getStaffIds().stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        // Tìm kiếm tòa nhà dựa trên buildingID
        Optional<BuildingEntity> buildingOptional = buildingRepository.findById(buildingID);
        if (buildingOptional.isPresent()) {
            BuildingEntity buildingEntity = buildingOptional.get();

            // Lấy danh sách người dùng cần được gán tòa nhà
            List<UserEntity> userEntities = userRepository.findAllById(staffIds);

            // Tạo ra các đối tượng AssignBuildingEntity và gán cho mỗi UserEntity
            List<AssignBuildingEntity> assignBuildingEntities = new ArrayList<>();
            userEntities.forEach(userEntity -> {
                AssignBuildingEntity assignBuildingEntity = new AssignBuildingEntity();
                assignBuildingEntity.setUser(userEntity);
                assignBuildingEntity.setBuilding(buildingEntity);
                assignBuildingEntities.add(assignBuildingEntity);
            });

            // Lưu các đối tượng AssignBuildingEntity vào cơ sở dữ liệu
            assignmentBuildingRepository.saveAll(assignBuildingEntities);
        } else {
            throw new NotFoundException("Building not found with ID: " + buildingID);
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
}