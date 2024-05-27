package site.rentofficevn.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.web.bind.annotation.*;
import site.rentofficevn.dto.CustomerDTO;
import site.rentofficevn.dto.TransactionDTO;
import site.rentofficevn.dto.request.AssignmentCustomerRequest;
import site.rentofficevn.dto.response.AssignmentStaffResponse;
import site.rentofficevn.service.impl.CustomerService;
import site.rentofficevn.service.impl.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {

    private final CustomerService customerService;
    private final TransactionService transactionService;

    @Autowired
    public CustomerAPI(CustomerService customerService, TransactionService transactionService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) throws IllegalArgumentException {
        return ResponseEntity.ok(customerService.save(customerDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomer(@RequestBody List<Long> ids) {
        customerService.delete(ids);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assignment-customer")
    public ResponseEntity<Void> assignmentCustomer(@RequestBody AssignmentCustomerRequest assignmentCustomer){
        customerService.assignmentCustomerToStaffs(assignmentCustomer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{customerId}/staffs")
    public ResponseEntity<List<AssignmentStaffResponse>> loadStaffByBuilding(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.loadStaffByCustomerId(customerId));
    }

    @PostMapping("/transaction")
    private ResponseEntity<TransactionDTO> saveTransaction(@RequestBody TransactionDTO transaction) throws NotFoundException {
        return ResponseEntity.ok(transactionService.save(transaction));
    }
}
