package site.rentofficevn.enums;

public enum SpecialSearchParamsEnum {
    STAFF_ID("staffID"),
    RENT_PRICE_FROM("rentPriceFrom"),
    RENT_PRICE_TO("rentPriceTo"),
    RENT_AREA_FROM("rentAreaFrom"),
    RENT_AREA_TO("rentAreaTo"),
    BUILDING_TYPES("types");
    private final String value;

    SpecialSearchParamsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
