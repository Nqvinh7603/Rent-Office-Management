package site.rentofficevn.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.rentofficevn.dto.CustomerDTO;
import site.rentofficevn.dto.request.AssignmentCustomerRequest;
import site.rentofficevn.dto.response.AssignmentStaffResponse;
import site.rentofficevn.service.impl.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    CustomerService customerService;

    /*@PostMapping
    public ResponseEntity<CustomerDTO> saveBuilding(@RequestBody CustomerDTO customerDTO) throws IllegalArgumentException {
        return ResponseEntity.ok(customerService.save(customerDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuilding(@RequestBody List<Long> ids) {
        customerService.delete(ids);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/assignment-customer")
    public ResponseEntity<Void> assignmentCustomer(@RequestBody AssignmentCustomerRequest assignmentCustomer){
        customerService.assignmentCustomerToStaffs(assignmentCustomer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{buildingId}/staffs")
    public ResponseEntity<List<AssignmentStaffResponse>> loadStaffByBuilding(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.loadStaffByBuildingId(customerId));*/

}
