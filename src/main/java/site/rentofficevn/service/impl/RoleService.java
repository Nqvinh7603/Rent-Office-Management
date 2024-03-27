package site.rentofficevn.service.impl;

import site.rentofficevn.converter.RoleConverter;
import site.rentofficevn.dto.RoleDTO;
import site.rentofficevn.entity.RoleEntity;
import site.rentofficevn.repository.RoleRepository;
import site.rentofficevn.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;

	public List<RoleDTO> findAll() {
		List<RoleEntity> roleEntities = roleRepository.findAll();
		List<RoleDTO> list = new ArrayList<>();
		roleEntities.forEach(item -> {
			RoleDTO roleDTO = roleConverter.convertToDto(item);
			list.add(roleDTO);
		});
		return list;
	}

	@Override
	public Map<String, String> getRoles() {
		Map<String,String> roleTerm = new HashMap<>();
		List<RoleEntity> roleEntity = roleRepository.findAll();
		roleEntity.forEach(entity ->{
			//RoleDTO roleDTO = roleConverter.convertToDto(entity);
			RoleDTO roleDTO = roleConverter.convertToDto(entity);
			roleTerm.put(roleDTO.getCode(), roleDTO.getName());
		});
		return roleTerm;
	}
}
