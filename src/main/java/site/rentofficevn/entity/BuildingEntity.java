package site.rentofficevn.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

    private static final long serialVersionUID = -6525302831793188081L;

    @Column(name = "name")
    private String name;
    @Column(name = "street")
    private String street;
    @Column(name = "ward")
    private String ward;
    @Column(name = "district")
    private String district;
    @Column(name = "floorarea")
    private Integer floorArea;
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
    @Column(name = "rentprice")
    private Integer rentPrice;
    @Column(name = "rentpricedescription")
    private String rentPriceDescription;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "managerphone")
    private String managerPhone;
    @Column(name = "servicefee")
    private String serviceFee;
    @Column(name = "brokeragefee")
    private BigDecimal brokerageFee;
    @Column(name = "type")
    private String types;
    @Column(name="structure")
    private String structure;
    @Column(name="direction")
    private String direction;
    @Column(name="level")
    private String level;
    @Column(name="carfee")
    private String carFee;
    @Column(name="motofee")
    private String motoFee;
    @Column(name="overtimefee")
    private String overTimeFee;
    @Column(name="electricityfee")
    private String electricityFee;
    @Column(name="waterfee")
    private String waterFee;
    @Column(name="deposit")
    private String deposit;
    @Column(name="payment")
    private String payment;
    @Column(name="decorationtime")
    private String decorateTime;
    @Column(name="note")
    private String note;
    @Column(name="linkofbuilding")
    private String linkOfBuilding;
    @Column(name="map")
    private String map;
    @Column(name="avatar")
    private String avatar;
    @Column(name="image")
    private String image;

    
    @OneToMany(mappedBy = "building")
    private List<RentAreaEntity> rentArea;

    // 1 building - n assignmentBuiding
    @OneToMany(mappedBy = "building")
    private List<AssignmentBuildingEntity> assignBuildings;



    public List<AssignmentBuildingEntity> getAssignBuildings() {
        return assignBuildings;
    }

    public void setAssignBuildings(List<AssignmentBuildingEntity> assignBuildings) {
        this.assignBuildings = assignBuildings;
    }

    public List<RentAreaEntity> getRentAreas() {
        return rentArea;
    }

    public void setRentAreas(List<RentAreaEntity> rentAreas) {
        this.rentArea = rentAreas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(BigDecimal brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setDistrictCode(String district) {
        this.district = district;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCarFee() {
        return carFee;
    }

    public void setCarFee(String carFee) {
        this.carFee = carFee;
    }

    public String getMotoFee() {
        return motoFee;
    }

    public void setMotoFee(String motoFee) {
        this.motoFee = motoFee;
    }

    public String getOverTimeFee() {
        return overTimeFee;
    }

    public void setOverTimeFee(String overTimeFee) {
        this.overTimeFee = overTimeFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDecorateTime() {
        return decorateTime;
    }

    public void setDecorateTime(String decorateTime) {
        this.decorateTime = decorateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLinkOfBuilding() {
        return linkOfBuilding;
    }

    public void setLinkOfBuilding(String linkOfBuilding) {
        this.linkOfBuilding = linkOfBuilding;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<RentAreaEntity> getRentArea() {
        return rentArea;
    }

    public void setRentArea(List<RentAreaEntity> rentArea) {
        this.rentArea = rentArea;
    }
}
