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

    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class RentAreaService implements IRentAreaService {
        @Autowired
        private RentAreaConverter rentAreaConverter;

        @Autowired
        private RentAreaRepository rentAreaRepository;

        @Autowired
        private BuildingRepository buildingRepository;

        public void saveAllRentArea(List<RentAreaDTO> listRentAreaDTO, BuildingEntity buildingEntity) {
            // convert dto -> entity -> save
            List<RentAreaEntity> listRentAreaEntity = new ArrayList<>();
            for (RentAreaDTO item : listRentAreaDTO) {
                RentAreaEntity rentareaEntity = rentAreaConverter.convertToEntity(item);
                listRentAreaEntity.add(rentareaEntity);
            }

            BuildingEntity building = buildingRepository.findById(buildingEntity.getId()).get();
            rentAreaRepository.saveRentAreas(listRentAreaEntity, building);
        }
    }
