package site.rentofficevn.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.rentofficevn.dto.TransactionDTO;
import site.rentofficevn.entity.TransactionEntity;

@Component
public class TransactionConverter {
    @Autowired
    ModelMapper modelMapper;

    public TransactionEntity toEntity(TransactionDTO dto){
        TransactionEntity result = modelMapper.map(dto, TransactionEntity.class);
        return result;
    }

    public TransactionDTO toDTO(TransactionEntity entity){
        TransactionDTO result = modelMapper.map(entity, TransactionDTO.class);
        return result;
    }


}
