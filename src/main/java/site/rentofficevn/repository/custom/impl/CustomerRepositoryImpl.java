package site.rentofficevn.repository.custom.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import site.rentofficevn.builder.CustomerSearchBuilder;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.entity.CustomerEntity;
import site.rentofficevn.enums.SpecialSearchParamsEnum;
import site.rentofficevn.repository.custom.CustomerRepositoryCustom;
import site.rentofficevn.utils.QueryBuilderUtils;
import site.rentofficevn.utils.ValidateUtils;

import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRepositoryImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity> findCustomer(CustomerSearchBuilder customerSearchBuilder, Pageable pageable) {
        try {
            StringBuilder finalQuery = new StringBuilder(
                    "SELECT c.* from customer c\n");
            StringBuilder joinQuery = new StringBuilder();
            StringBuilder whereQuery = new StringBuilder();
            buildQuery(customerSearchBuilder, whereQuery, joinQuery);
            finalQuery.append(joinQuery)
                    .append(SystemConstant.WHERE_ONE_EQUAL_ONE)
                    .append(whereQuery)
                    .append(" GROUP BY c.id");
            LOGGER.info("Query: " + finalQuery);
            Query query = entityManager.createNativeQuery(finalQuery.toString(), CustomerEntity.class).setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                    .setMaxResults(pageable.getPageSize());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int countByCondition(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder finalQuery = new StringBuilder(
                "SELECT count(distinct c.id) from customer c\n");
        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();
        buildQuery(customerSearchBuilder, whereQuery, joinQuery);
        finalQuery.append(joinQuery)
                .append(SystemConstant.WHERE_ONE_EQUAL_ONE)
                .append(whereQuery);
        Query query = entityManager.createNativeQuery(finalQuery.toString());
        return query.getSingleResult() != null ? Integer.parseInt(query.getSingleResult().toString()) : 0;
    }

    private void buildQuery(CustomerSearchBuilder customerSearchBuilder, StringBuilder whereQuery, StringBuilder joinQuery) {
        try {
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
            List<String> specialSearchParams = getSpecialSearchParams();

            for (Field field : fields) {
                field.setAccessible(true);

                buildQueryForNormalCase(field, whereQuery, specialSearchParams, customerSearchBuilder);
                buildQueryForSpecialClass(field, whereQuery, joinQuery, customerSearchBuilder);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private List<String> getSpecialSearchParams() {
        return Arrays.stream(SpecialSearchParamsEnum.values())
                .map(SpecialSearchParamsEnum::getValue)
                .collect(Collectors.toList());
    }

    private void buildQueryForNormalCase(Field field, StringBuilder whereQuery, List<String> specialSearchParams, CustomerSearchBuilder customerSearchBuilder) throws IllegalAccessException {
        String fieldSearch = field.getName();
        Column column = field.getAnnotation(Column.class);
        String columnNameWithAlias = SystemConstant.CUSTOMER_ALIAS + column.name();
        Object fieldValue = field.get(customerSearchBuilder);
        if (!specialSearchParams.contains(fieldSearch) && ValidateUtils.isValid(fieldValue)) {
            if (field.getType().isAssignableFrom(String.class)) {
                whereQuery.append(QueryBuilderUtils.withLike(columnNameWithAlias, fieldValue.toString()));
            } else if (field.getType().isAssignableFrom(Integer.class) || field.getType().isAssignableFrom(Long.class)) {
                whereQuery.append(QueryBuilderUtils.withOperator(columnNameWithAlias, fieldValue, SystemConstant.EQUAL_OPERATOR));
            }
        }
    }

    private void buildQueryForSpecialClass(Field field, StringBuilder whereQuery, StringBuilder joinQuery, CustomerSearchBuilder customerSearchBuilder) throws IllegalAccessException {
        String fieldSearch = field.getName();
        Column column = field.getAnnotation(Column.class);
        Object fieldValue = field.get(customerSearchBuilder);

        buildQueryForStaffs(fieldSearch, fieldValue, column, whereQuery, joinQuery);
    }

    private void buildQueryForStaffs(String fieldSearch, Object fieldValue, Column column, StringBuilder whereQuery, StringBuilder joinQuery) {
        if (SystemConstant.STAFF_SEARCH_PARAM.equals(fieldSearch) && ValidateUtils.isValid(fieldValue)) {
            joinQuery.append(" INNER JOIN assignmentcustomer as ac ON ac.customerid = c.id\n");
            whereQuery.append(QueryBuilderUtils.withOperator("ac." + column.name(), fieldValue, SystemConstant.EQUAL_OPERATOR));
        }
    }
}
