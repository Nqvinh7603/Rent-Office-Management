package site.rentofficevn.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.rentofficevn.model.dto.UserDTO;
import site.rentofficevn.service.UserService;

@RestController
public class UserAPI {
	@Autowired
	private UserService userService;
	@GetMapping("api/users")
	public List<UserDTO> getStaff(@RequestParam(value = "buildingid", required = false) Long buildingId,
			@RequestParam(value = "role", required = false) String roleCode) {
		List<UserDTO> results = new ArrayList<>();
		return results;
	}
	@GetMapping("api/user")
	public List<UserDTO> findByRole(@RequestParam(value = "rolecode", required = false) String roleCode) {
		List<UserDTO> results = new ArrayList<>();
		userService.findByRole(roleCode);
		return results;
	}
}
