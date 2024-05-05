package site.rentofficevn.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.rentofficevn.model.dto.RentAreaDTO;
import site.rentofficevn.repository.entity.RentAreaEntity;

@Component
public class RentAreaConverter {
    @Autowired
    private ModelMapper modelMapper;

    public RentAreaDTO convertFromEntitytoRentAreaDTO(RentAreaEntity rentAreaEntity) {
        return modelMapper.map(rentAreaEntity, RentAreaDTO.class);
    }
}
