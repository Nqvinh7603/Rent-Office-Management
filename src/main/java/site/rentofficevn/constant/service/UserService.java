package site.rentofficevn.constant.service;

import site.rentofficevn.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findByRole(String roleCode);
}
