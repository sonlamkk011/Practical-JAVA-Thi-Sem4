package com.example.practical.api;

import com.example.practical.entity.Employee;
import com.example.practical.service.EmlployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/employees")
public class EmployeeApi {

    @Autowired
    EmlployeeService emlployeeService;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getList() {
        return ResponseEntity.ok(emlployeeService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<Employee> optionalEmployee = emlployeeService.findById(id);
        if (!optionalEmployee.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalEmployee.get());
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(emlployeeService.save(employee));
    }


//    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
//    public ResponseEntity<District> update(@PathVariable Integer id, @RequestBody District district) {
//        Optional<District> optionalDistrict = districtService.findById(id);
//        if (!optionalDistrict.isPresent()) {
//            ResponseEntity.badRequest().build();
//        }
//        District existDistrict = optionalDistrict.get();
//        // map object
//        existDistrict.setName(district.getName());
//        return ResponseEntity.ok(districtService.save(existDistrict));
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//        if (!districtService.findById(id).isPresent()) {
//            ResponseEntity.badRequest().build();
//        }
//        districtService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
}
