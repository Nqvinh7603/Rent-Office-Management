package site.rentofficevn.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignmentbuilding")
public class AssignmentBuildingEntity extends BaseEntity {
    
    @ManyToOne
    @JoinColumn(name="buildingid", nullable=false)
    private BuildingEntity building;

    @ManyToOne
    @JoinColumn(name="staffid", nullable=false)
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
