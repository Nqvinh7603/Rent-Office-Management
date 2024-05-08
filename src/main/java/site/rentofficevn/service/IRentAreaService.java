package site.rentofficevn.service;

import site.rentofficevn.dto.BuildingDTO;
import site.rentofficevn.dto.RentAreaDTO;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.RentAreaEntity;

import java.util.*;
public interface IRentAreaService {
    void saveAllRentArea(List<RentAreaDTO> listRentAreaDTO, BuildingEntity buildingEntity);
}