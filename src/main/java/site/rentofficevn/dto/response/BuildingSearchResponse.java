package site.rentofficevn.dto.response;

import site.rentofficevn.dto.AbstractDTO;

public class BuildingSearchResponse extends AbstractDTO{
    private String name;            // tên sản phẩm
    private String address;         // địa chỉ
    private String managerName;     // tên quản lí
    private Integer managerPhone;   // số điện thoại
    private String floorArea;       // diện tích sàn
    private String emptyArea;       // diện tích trống
    private Integer rentPrice;     // giá thuê
    private String serviceFee;      // phí dịch vụ
    private String brokerageFee;    // phí môi giới
    private String rentAreaDescription;

    public String getRentAreaDescription() {
        return rentAreaDescription;
    }

    public void setRentAreaDescription(String rentAreaDescription) {
        this.rentAreaDescription = rentAreaDescription;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(Integer managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(String floorArea) {
        this.floorArea = floorArea;
    }

    public String getEmptyArea() {
        return emptyArea;
    }

    public void setEmptyArea(String emptyArea) {
        this.emptyArea = emptyArea;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(String brokerageFee) {
        this.brokerageFee = brokerageFee;
    }
}
