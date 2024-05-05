package site.rentofficevn.converter;

import site.rentofficevn.dto.UserDTO;
import site.rentofficevn.dto.response.StaffResponseDTO;
import site.rentofficevn.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.rentofficevn.repository.custom.impl.UserRepositoryImpl;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepositoryImpl userRepository;

    public UserDTO convertToDto (UserEntity entity){
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        return result;
    }

    public UserEntity convertToEntity (UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }
    public List<StaffResponseDTO> convertToDtoResponse(List<UserEntity> listUserEntity) {
        return userRepository.getAllStaff().stream()
                .map(staffAll -> {
                    StaffResponseDTO listStaff = modelMapper.map(staffAll, StaffResponseDTO.class);
                    listUserEntity.stream()
                            .filter(userList -> staffAll.getId().equals(userList.getId()))
                            .findFirst()
                            .ifPresent(user -> listStaff.setChecked("checked"));
                    return listStaff;
                })
                .collect(Collectors.toList());
    }

}
