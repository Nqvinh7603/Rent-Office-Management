package site.rentofficevn.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.converter.BuildingConverter;
import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.service.BuildingService;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.utils.MapUtils;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRespository;

    @Autowired
    private BuildingConverter buildingConverter;

	@Override
	public List<BuildingSearchResponse> getBuildingList(Map<String, Object> buildingSearch,
														List<String> buildingTypes)  {
		List<BuildingSearchResponse> results = new ArrayList<>();
		BuildingSearchBuilder buildingSearchBuilder = convertToBuildingSearchBuilder(buildingSearch, buildingTypes);

		try {
			List<BuildingEntity> buildingEntities = buildingRespository.findBuilding(buildingSearchBuilder);
			return buildingEntities.stream()
					.map(entity -> buildingConverter.convertFromEntitytoBuildingSearchResponse(entity))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	private BuildingSearchBuilder convertToBuildingSearchBuilder
        (Map < String, Object > buildingSearch, List < String > buildingTypes){
            try {
                BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                        .setName(MapUtils.getObject(buildingSearch, "name", String.class))
                        .setFloorArea(MapUtils.getObject(buildingSearch, "floorarea", Integer.class))
                        .setDistrict(MapUtils.getObject(buildingSearch, "district", String.class))
                        .setWard(MapUtils.getObject(buildingSearch, "ward", String.class))
                        .setStreet(MapUtils.getObject(buildingSearch, "street", String.class))
                        .setNumberOfBasement(MapUtils.getObject(buildingSearch, "numberofbasement", Integer.class))
                        .setDirection(MapUtils.getObject(buildingSearch, "direction", String.class))
                        .setLevel(MapUtils.getObject(buildingSearch, "level", String.class))
                        .setRentAreaFrom(MapUtils.getObject(buildingSearch, "rentareafrom", Integer.class))
                        .setRentAreaTo(MapUtils.getObject(buildingSearch, "rentareato", Integer.class))
                        .setRentPriceFrom(MapUtils.getObject(buildingSearch, "rentpricefrom", Integer.class))
                        .setRentPriceTo(MapUtils.getObject(buildingSearch, "rentpriceto", Integer.class))
                        .setManagerName(MapUtils.getObject(buildingSearch, "managername", String.class))
                        .setManagerPhone(MapUtils.getObject(buildingSearch, "managerphone", String.class))
                        .setTypes(buildingTypes)
                        .build();
                return buildingSearchBuilder;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }
