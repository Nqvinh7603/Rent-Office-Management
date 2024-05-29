package site.rentofficevn.converter;

import site.rentofficevn.dto.UserDTO;
import site.rentofficevn.dto.response.AssignmentStaffResponse;
import site.rentofficevn.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto (UserEntity entity){
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        return result;
    }

    public UserEntity convertToEntity (UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }

    public AssignmentStaffResponse toAssignmentStaffResponse(UserEntity userEntity) {
        AssignmentStaffResponse staffResponse = new AssignmentStaffResponse();
        staffResponse.setId(userEntity.getId());
        staffResponse.setFullName(userEntity.getFullName());
        staffResponse.setChecked("");
        return staffResponse;
    }

}
