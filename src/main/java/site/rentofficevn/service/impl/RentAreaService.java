    package site.rentofficevn.service.impl;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import site.rentofficevn.converter.RentAreaConverter;
    import site.rentofficevn.dto.RentAreaDTO;
    import site.rentofficevn.entity.BuildingEntity;
    import site.rentofficevn.entity.RentAreaEntity;
    import site.rentofficevn.repository.BuildingRepository;
    import site.rentofficevn.repository.RentAreaRepository;
    import site.rentofficevn.service.IRentAreaService;

    import java.util.*;
    import java.util.stream.Collectors;

    @Service
    public class RentAreaService implements IRentAreaService {
        @Autowired
        private RentAreaConverter rentAreaConverter;

        @Autowired
        private RentAreaRepository rentAreaRepository;

        @Autowired
        private BuildingRepository buildingRepository;

        public void updateRentAreas(BuildingEntity building, List<RentAreaDTO> newRentAreas) {
            List<RentAreaEntity> oldRentAreas = rentAreaRepository.findByBuilding(building);
            List<RentAreaEntity> rentAreasToAdd = new ArrayList<>();
            List<RentAreaEntity> rentAreasToDelete = new ArrayList<>();

            for (RentAreaDTO newRentArea : newRentAreas) {
                boolean exists = oldRentAreas.stream()
                        .anyMatch(oldRentArea -> Objects.equals(oldRentArea.getValue(), newRentArea.getValue()));

                if (!exists) {
                    RentAreaEntity newRentAreaEntity = rentAreaConverter.convertToEntity(newRentArea);
                    newRentAreaEntity.setBuilding(building);
                    rentAreasToAdd.add(newRentAreaEntity);
                }
            }

            for (RentAreaEntity oldRentArea : oldRentAreas) {
                boolean exists = newRentAreas.stream()
                        .anyMatch(newRentArea -> Objects.equals(oldRentArea.getValue(), newRentArea.getValue()));

                if (!exists) {
                    rentAreasToDelete.add(oldRentArea);
                }
            }

            rentAreaRepository.deleteAll(rentAreasToDelete);
            rentAreaRepository.saveAll(rentAreasToAdd);
        }
    }
