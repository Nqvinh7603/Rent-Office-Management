package site.rentofficevn.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.response.ResponseDTO;
import site.rentofficevn.dto.response.StaffResponseDTO;
import site.rentofficevn.service.impl.BuildingService;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "buildingAPIOfAmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    BuildingService buildingService;

    @PostMapping
    public BuildingDTO addBuilding(@RequestBody BuildingDTO newBuilding){
        buildingService.save(newBuilding);
        return newBuilding;
    }

    //api để load staff (staff đi theo building)
    @GetMapping("/{buildingid}/staffs")
    public ResponseDTO loadStaff(){
        ResponseDTO result = new ResponseDTO();
        List<StaffResponseDTO> staffs = new ArrayList<>();
        StaffResponseDTO staffResponseDTO1 = new StaffResponseDTO();
        StaffResponseDTO staffResponseDTO2 = new StaffResponseDTO();
        StaffResponseDTO staffResponseDTO3 = new StaffResponseDTO();
        staffResponseDTO1.setFullName("nguyen van b");
        staffResponseDTO1.setStaffId(1L);
        staffResponseDTO1.setChecked("checked");
        staffResponseDTO2.setFullName("nguyen van c");
        staffResponseDTO2.setStaffId(2L);
        staffResponseDTO2.setChecked("checked");
        staffResponseDTO3.setFullName("nguyen van d");
        staffResponseDTO3.setStaffId(3L);
        staffResponseDTO3.setChecked("");
        staffs.add(staffResponseDTO1);
        staffs.add(staffResponseDTO2);
        staffs.add(staffResponseDTO3);
        result.setMessage("success");
        result.setData(staffs);
        return result;
    }
}