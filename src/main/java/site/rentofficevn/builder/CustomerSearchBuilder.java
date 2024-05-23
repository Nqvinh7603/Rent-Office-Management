package site.rentofficevn.builder;

import javax.persistence.Column;

public class CustomerSearchBuilder {

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "staffid")
    private Long staffId;

    private CustomerSearchBuilder(Builder builder) {
        this.fullName = builder.fullName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.staffId = builder.staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public static final class Builder{
        private String fullName;
        private String phone;
        private String email;
        private Long staffId;

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public CustomerSearchBuilder build(){
            return new CustomerSearchBuilder(this);
        }
    }
}
