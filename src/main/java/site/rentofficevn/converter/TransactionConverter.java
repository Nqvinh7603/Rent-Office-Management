package site.rentofficevn.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.rentofficevn.dto.TransactionDTO;
import site.rentofficevn.entity.TransactionEntity;
import site.rentofficevn.repository.CustomerRepository;
import site.rentofficevn.utils.DateUtils;

@Component
public class TransactionConverter {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;

    public TransactionEntity toEntity(TransactionDTO dto){
        //TransactionEntity result = modelMapper.map(dto, TransactionEntity.class);
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setCode(dto.getCode());
        transactionEntity.setNote(dto.getNote());
        if (!transactionEntity.getCode().isEmpty() && transactionEntity.getNote() != null) {
            transactionEntity.setCustomer(customerRepository.findById(dto.getCustomerId()).get());
        }
        return transactionEntity;

    }

    public TransactionDTO toDTO(TransactionEntity entity){
        if (entity == null) {
            return null;
        }
        TransactionDTO result = modelMapper.map(entity, TransactionDTO.class);
        result.setNote(entity.getNote());
        result.setCustomerId(entity.getCustomer().getId());
        result.setCreatedDate(DateUtils.convertDateToString(entity.getCreatedDate()));
        return result;
    }
}
