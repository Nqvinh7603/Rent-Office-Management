package site.rentofficevn.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.entity.AssignBuildingEntity;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.entity.UserEntity;
import site.rentofficevn.enums.SpecialSearchParamsEnum;
import site.rentofficevn.repository.AssignmentBuildingRepository;
import site.rentofficevn.repository.UserRepository;
import site.rentofficevn.repository.custom.BuildingRepositoryCustom;
import site.rentofficevn.utils.CheckInputSearchUtils;
import site.rentofficevn.utils.QueryBuilderUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.rentofficevn.utils.ValidateUtils;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

   @PersistenceContext
   private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingRepositoryImpl.class);

    @Override
    public List<BuildingEntity> findBuilding(BuildingSearchBuilder buildingSearchBuilder) {
        try {
            StringBuilder finalQuery = new StringBuilder(
                    "SELECT b.* from building b\n");
            StringBuilder joinQuery = new StringBuilder();
            StringBuilder whereQuery = new StringBuilder();
            buildQuery(buildingSearchBuilder, whereQuery, joinQuery);
            finalQuery.append(joinQuery)
                    .append(SystemConstant.WHERE_ONE_EQUAL_ONE)
                    .append(whereQuery)
                    .append(" GROUP BY b.id");
            LOGGER.info("Query: " + finalQuery);
            Query query = entityManager.createNativeQuery(finalQuery.toString(), BuildingEntity.class);
            return query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private void buildQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder whereQuery, StringBuilder joinQuery) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            List<String> specialSearchParams = getSpecialSearchParams();

            for (Field field : fields) {
                field.setAccessible(true);

                buildQueryForNormalCase(field, whereQuery, specialSearchParams, buildingSearchBuilder);
                buildQueryForSpecialClass(field, whereQuery, joinQuery, buildingSearchBuilder);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    private List<String> getSpecialSearchParams(){
        return Arrays.stream(SpecialSearchParamsEnum.values())
                .map(SpecialSearchParamsEnum::getValue)
                .collect(Collectors.toList());
    }
    private void buildQueryForNormalCase(Field field, StringBuilder whereQuery, List<String> specialSearchParams, BuildingSearchBuilder buildingSearchBuilder) throws IllegalAccessException {
        String fieldSearch = field.getName();
        Column column = field.getAnnotation(Column.class);
        String columnNameWithAlias = SystemConstant.BUILDING_ALIAS + column.name();
        Object fieldValue = field.get(buildingSearchBuilder);
        if(!specialSearchParams.contains(fieldSearch) && ValidateUtils.isValid(fieldValue)){
            if(field.getType().isAssignableFrom(String.class)) {
                whereQuery.append(QueryBuilderUtils.withLike(columnNameWithAlias, fieldValue.toString()));
            }else if(field.getType().isAssignableFrom(Integer.class) || field.getType().isAssignableFrom(Long.class)){
                whereQuery.append(QueryBuilderUtils.withOperator(columnNameWithAlias, fieldValue, SystemConstant.EQUAL_OPERATOR));
            }
        }
    }
    private void buildQueryForSpecialClass(Field field, StringBuilder whereQuery, StringBuilder joinQuery, BuildingSearchBuilder buildingSearchBuilder) throws IllegalAccessException {
        String fieldSearch = field.getName();
        Column column = field.getAnnotation(Column.class);
        String columnNameWithAlias = SystemConstant.BUILDING_ALIAS + column.name();
        Object fieldValue = field.get(buildingSearchBuilder);

        bulidQueryForRentArea(fieldSearch, fieldValue, column, whereQuery, joinQuery);
        bulidQueryForRentPrice(fieldSearch, fieldValue, columnNameWithAlias, whereQuery);
        buildQueryForBuildingTypes(fieldSearch, fieldValue, columnNameWithAlias, whereQuery, field);
        buildQueryForStaffs(fieldSearch, fieldValue, column, whereQuery, joinQuery);

    }

    private void buildQueryForStaffs(String fieldSearch, Object fieldValue,Column column, StringBuilder whereQuery, StringBuilder joinQuery) {
        if(SystemConstant.STAFF_SEARCH_PARAM.equals(fieldSearch) && ValidateUtils.isValid(fieldValue)){
            joinQuery.append(" INNER JOIN assignmentbuilding as ab ON ab.buildingid = b.id\n");
            whereQuery.append(QueryBuilderUtils.withOperator("ab." + column.name(), fieldValue, SystemConstant.EQUAL_OPERATOR));
        }
    }

    private void buildQueryForBuildingTypes(String fieldSearch, Object fieldValue, String columnNameWithAlias, StringBuilder whereQuery, Field field) {
        if(fieldSearch.equals(SystemConstant.BUILDING_TYPE_SEARCH_PARAM) && field.getType().isAssignableFrom(List.class) && fieldValue != null){
            List<String> buildingTypes = (List<String>) fieldValue;
            if(!buildingTypes.isEmpty()){
                whereQuery.append(QueryBuilderUtils.withOrAndLike(columnNameWithAlias, buildingTypes));
            }
        }
    }


    private void bulidQueryForRentPrice(String fieldSearch, Object fieldValue, String columnNameWithAlias, StringBuilder whereQuery) {
        if ((SystemConstant.RENT_PRICE_FROM_SEARCH_PARAM.equals(fieldSearch) || SystemConstant.RENT_PRICE_TO_SEARCH_PARAM.equals(fieldSearch)) && ValidateUtils.isValid(fieldValue)) {
            if (SystemConstant.RENT_PRICE_FROM_SEARCH_PARAM.equals(fieldSearch)) {
                whereQuery.append(QueryBuilderUtils.withOperator(columnNameWithAlias, fieldValue, SystemConstant.GREATER_THAN_OPERATOR));
            }
            if (SystemConstant.RENT_PRICE_TO_SEARCH_PARAM.equals(fieldSearch)) {
                whereQuery.append(QueryBuilderUtils.withOperator(columnNameWithAlias, fieldValue, SystemConstant.LESS_THAN_OPERATOR));
            }

        }
    }

    private void bulidQueryForRentArea(String fieldSearch, Object fieldValue, Column column, StringBuilder whereQuery, StringBuilder joinQuery) {
        if ((SystemConstant.RENT_AREA_FROM_SEARCH_PARAM.equals(fieldSearch) || SystemConstant.RENT_AREA_TO_SEARCH_PARAM.equals(fieldSearch)) && ValidateUtils.isValid(fieldValue)) {
            if(!joinQuery.toString().contains("join rentarea")){
                joinQuery.append("JOIN rentarea ra ON ra.buildingid = b.id\n");
            }
            if(SystemConstant.RENT_AREA_FROM_SEARCH_PARAM.equals(fieldSearch)){
                whereQuery.append(QueryBuilderUtils.withOperator("ra." + column.name(), fieldValue, SystemConstant.GREATER_THAN_OPERATOR));
            }
            if(SystemConstant.RENT_AREA_TO_SEARCH_PARAM.equals(fieldSearch)){
                whereQuery.append(QueryBuilderUtils.withOperator("ra." + column.name(), fieldValue, SystemConstant.LESS_THAN_OPERATOR));
            }
        }
    }
    @Transactional
    @Override
    public void assignmentBuilding(List<UserEntity> userEntities, BuildingEntity buildingEntity) {

        // Xóa những người dùng không nằm trong danh sách userEntities
        userRepository.getAllStaffByBuilding(buildingEntity.getId()).stream()
                .filter(item -> userEntities.stream().noneMatch(item1 -> item.getId() == item1.getId()))
                .map(item -> assignmentRepo.findByBuildingAndUser(buildingEntity, item))
                .forEach(entityManager::remove);

        // Thêm những người dùng mới vào danh sách
        userEntities.stream()
                .filter(item -> userRepository.getAllStaffByBuilding(buildingEntity.getId()).stream()
                        .noneMatch(item2 -> item.getId() == item2.getId()))
                .map(item -> {
                    AssignBuildingEntity assignmentBuildingEntity = new AssignBuildingEntity();
                    assignmentBuildingEntity.setBuilding(buildingEntity);
                    assignmentBuildingEntity.setUser(item);
                    return assignmentBuildingEntity;
                })
                .forEach(entityManager::persist);
    }

    @Override
    public List<BuildingEntity> pageBuilding(Pageable pageable, BuildingSearchBuilder builder) {
            try {
                StringBuilder finalQuery = new StringBuilder(
                        "SELECT b.* from building b\n");
                finalQuery.append(buildJoiningClause(builder))
                        .append(SystemConstant.WHERE_ONE_EQUAL_ONE)
                        .append(buildCommonClause(builder))
                        .append(buildSpecialClause(builder));
                Query query = entityManager.createNativeQuery(finalQuery.toString(), BuildingEntity.class);
                return query.getResultList();
            }catch(Exception e){
                e.printStackTrace();
                return new ArrayList<>();
            }
    }
    private String buildJoiningClause(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sqlJoining = new StringBuilder();
        Long staffId = buildingSearchBuilder.getStaffID();
        if (!CheckInputSearchUtils.isNullLong(staffId)) {
            sqlJoining.append(" INNER JOIN assignmentbuilding as ab ON ab.buildingid = b.id INNER JOIN user as u ON ab.staffid = u.id");
        }
        return sqlJoining.toString();
    }

    private String buildCommonClause(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sqlCommon = new StringBuilder();
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!fieldName.equals("types") &&
                        !fieldName.startsWith("rentArea")
                        && !fieldName.equals("staffID")
                        && !fieldName.startsWith("rentPrice")) {
                    Object fieldValue = field.get(buildingSearchBuilder);
                    if (fieldValue != null && fieldValue != "") {
                        if (fieldValue instanceof String) {
                            sqlCommon.append(" and b." + fieldName.toLowerCase() + " LIKE '%" + fieldValue+ "%'");
                        } else if (fieldValue instanceof Integer) {
                            sqlCommon.append(" and b." + fieldName + " = " + fieldValue);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sqlCommon.toString();
    }

    private String buildSpecialClause(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sqlSpecial = new StringBuilder();
        Long staffId = buildingSearchBuilder.getStaffID();
        if (!CheckInputSearchUtils.isNullLong(staffId)) {
            sqlSpecial.append(" AND u.id = ").append(staffId);
        }
        Integer rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Integer rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (!CheckInputSearchUtils.isNullInteger(rentPriceTo) || !CheckInputSearchUtils.isNullInteger(rentPriceFrom)) {
            if (!CheckInputSearchUtils.isNullInteger(rentPriceFrom)) {
                sqlSpecial.append(" AND b.rentprice >= ").append(rentPriceFrom);
            }
            if (!CheckInputSearchUtils.isNullInteger(rentPriceTo)) {
                sqlSpecial.append(" AND b.rentprice <= ").append(rentPriceTo);
            }
        }
        Integer rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
        Integer rentAreaTo = buildingSearchBuilder.getRentAreaTo();
        if (!CheckInputSearchUtils.isNullInteger(rentAreaFrom) || !CheckInputSearchUtils.isNullInteger(rentAreaTo)) {
            sqlSpecial.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE ra.buildingid = b.id");
            if (!CheckInputSearchUtils.isNullInteger(rentAreaFrom)) {
                sqlSpecial.append(" AND ra.value >= ").append(rentAreaFrom);
            }
            if (!CheckInputSearchUtils.isNullInteger(rentAreaTo)) {
                sqlSpecial.append(" AND ra.value <= ").append(rentAreaTo);
            }
            sqlSpecial.append(")");
        }
        if (buildingSearchBuilder.getTypes() != null && buildingSearchBuilder.getTypes().size() > 0) {
            sqlSpecial.append(" AND (");
            sqlSpecial.append(buildingSearchBuilder.getTypes().stream()
                    .map(type -> "b.type LIKE '%" + type + "%'")
                    .collect(Collectors.joining(" OR ")));
            sqlSpecial.append(")");
        }
        return sqlSpecial.toString();
    }
    /* @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentRepo;

    @Override
    public List<BuildingEntity> findBuilding(BuildingSearchBuilder buildingSearchBuilder) {
        try {
            StringBuilder finalQuery = new StringBuilder(
                    "SELECT b.* from building b\n");
            finalQuery.append(buildJoiningClause(buildingSearchBuilder))
                    .append(SystemConstant.WHERE_ONE_EQUAL_ONE)
                    .append(buildCommonClause(buildingSearchBuilder))
                    .append(buildSpecialClause(buildingSearchBuilder))
                    .append(" GROUP BY b.id");
            Query query = entityManager.createNativeQuery(finalQuery.toString(), BuildingEntity.class);
            return query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private String buildJoiningClause(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sqlJoining = new StringBuilder();
        Long staffId = buildingSearchBuilder.getStaffID();
        if (!CheckInputSearchUtils.isNullLong(staffId)) {
            sqlJoining.append(" INNER JOIN assignmentbuilding as ab ON ab.buildingid = b.id INNER JOIN user as u ON ab.staffid = u.id");
        }
        return sqlJoining.toString();
    }

    private String buildCommonClause(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sqlCommon = new StringBuilder();
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!fieldName.equals("types") &&
                        !fieldName.startsWith("rentArea")
                        && !fieldName.equals("staffID")
                        && !fieldName.startsWith("rentPrice")) {
                    Object fieldValue = field.get(buildingSearchBuilder);
                    if (fieldValue != null && fieldValue != "") {
                        if (fieldValue instanceof String) {
                            sqlCommon.append(" and b." + fieldName.toLowerCase() + " LIKE '%" + fieldValue+ "%'");
                        } else if (fieldValue instanceof Integer) {
                            sqlCommon.append(" and b." + fieldName + " = " + fieldValue);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sqlCommon.toString();
    }

    private String buildSpecialClause(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sqlSpecial = new StringBuilder();
        Long staffId = buildingSearchBuilder.getStaffID();
        if (!CheckInputSearchUtils.isNullLong(staffId)) {
            sqlSpecial.append(" AND u.id = ").append(staffId);
        }
        Integer rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Integer rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (!CheckInputSearchUtils.isNullInteger(rentPriceTo) || !CheckInputSearchUtils.isNullInteger(rentPriceFrom)) {
            if (!CheckInputSearchUtils.isNullInteger(rentPriceFrom)) {
                sqlSpecial.append(" AND b.rentprice >= ").append(rentPriceFrom);
            }
            if (!CheckInputSearchUtils.isNullInteger(rentPriceTo)) {
                sqlSpecial.append(" AND b.rentprice <= ").append(rentPriceTo);
            }
        }
        Integer rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
        Integer rentAreaTo = buildingSearchBuilder.getRentAreaTo();
        if (!CheckInputSearchUtils.isNullInteger(rentAreaFrom) || !CheckInputSearchUtils.isNullInteger(rentAreaTo)) {
            sqlSpecial.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE ra.buildingid = b.id");
            if (!CheckInputSearchUtils.isNullInteger(rentAreaFrom)) {
                sqlSpecial.append(" AND ra.value >= ").append(rentAreaFrom);
            }
            if (!CheckInputSearchUtils.isNullInteger(rentAreaTo)) {
                sqlSpecial.append(" AND ra.value <= ").append(rentAreaTo);
            }
            sqlSpecial.append(")");
        }
        if (buildingSearchBuilder.getTypes() != null && buildingSearchBuilder.getTypes().size() > 0) {
            sqlSpecial.append(" AND (");
            sqlSpecial.append(buildingSearchBuilder.getTypes().stream()
                    .map(type -> "b.type LIKE '%" + type + "%'")
                    .collect(Collectors.joining(" OR ")));
            sqlSpecial.append(")");
        }
        return sqlSpecial.toString();
    }*/
}
