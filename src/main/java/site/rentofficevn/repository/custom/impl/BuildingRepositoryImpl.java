package site.rentofficevn.repository.custom.impl;

import org.springframework.stereotype.Repository;
import site.rentofficevn.builder.BuildingSearchBuilder;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.entity.BuildingEntity;
import site.rentofficevn.enums.SpecialSearchParamsEnum;
import site.rentofficevn.repository.custom.BuildingRepositoryCustom;
import site.rentofficevn.utils.QueryBuilderUtils;
import site.rentofficevn.utils.ValidateUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

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
        Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
        List<String> specialSearchParams = getSpecialSearchParams();

        for(Field field : fields){
            try {
                field.setAccessible(true);
                buildQueryForNormalCase(field, whereQuery, specialSearchParams, buildingSearchBuilder);
                buildQueryForSpecialClass(field, whereQuery, joinQuery, buildingSearchBuilder);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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
        if(fieldSearch.equals(SystemConstant.STAFF_SEARCH_PARAM) && ValidateUtils.isValid(fieldValue)){
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

}
