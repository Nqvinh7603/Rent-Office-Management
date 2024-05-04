package site.rentofficevn.service;

import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.response.DistrictResponse;

import java.util.List;

public interface IDistrictService {
    List<DistrictResponse> getAllDistrict();  // hiển thị
    List<DistrictResponse> getDistrictByBuilding(BuildingDTO buildingDTO);  // search building by district
}
