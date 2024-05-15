package site.rentofficevn.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.RentAreaDTO;
import site.rentofficevn.entity.RentAreaEntity;
import site.rentofficevn.repository.BuildingRepository;
import java.util.*;

@Component
public class RentAreaConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingRepository buildingRepository;

    // convert entity -> dto
    public RentAreaDTO convertToDto(RentAreaEntity entity) {
        RentAreaDTO result = modelMapper.map(entity, RentAreaDTO.class);
        return result;
    }

    // convert dto -> entity
    public RentAreaEntity convertToEntity(RentAreaDTO dto) {
        RentAreaEntity result = modelMapper.map(dto, RentAreaEntity.class);
        result.setBuilding(buildingRepository.findById(dto.getBuildingid()).get());
        return result;
    }

    public List<RentAreaDTO> convertRentAreaDto(Long buildingIdAfter, BuildingDTO buildingDTO) {
        List<RentAreaDTO> result = new ArrayList<>();

        BuildingDTO buildingDTORentArea = buildingConverter.toDTO(buildingRepository.findById(buildingDTO.getId()).get());
        if (buildingDTORentArea.getRentArea().equals(buildingDTO.getRentArea())) {
            return new ArrayList<>();
        }

        String[] rentArea = buildingDTO.getRentArea() != null ? buildingDTO.getRentArea().trim().split(",") : null;
        if (rentArea != null) {
            for (String item : rentArea) {
                RentAreaDTO rentAreaDTO = new RentAreaDTO();
                rentAreaDTO.setValue(Integer.parseInt(item));
                rentAreaDTO.setBuildingid(buildingIdAfter);
                result.add(rentAreaDTO);
            }
            return result;
        } else {
            return new ArrayList<>();
        }
    }
    // convert building qua rentarea dto
    public List<RentAreaDTO> convertToRentArea(Long idBuilding, BuildingDTO buildingDTO) {
        List<RentAreaDTO> listRentArea = new ArrayList<>();

        if (buildingDTO.getRentArea() != null && !buildingDTO.getRentArea().isEmpty()) {
            String[] stringRentArea = buildingDTO.getRentArea().trim().split(",");
            for (String item : stringRentArea) {
                RentAreaDTO rentAreaDTO = new RentAreaDTO();
                rentAreaDTO.setValue(Integer.parseInt(item));
                rentAreaDTO.setBuildingid(idBuilding);
                listRentArea.add(rentAreaDTO);
            }
        }
        return listRentArea;
    }
}
