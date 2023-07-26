package employee.mngement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import employee.mngement.Repository.EmployeeRepository;
import employee.mngement.entity.Employee;

@Controller
public class EmployeeController {
@Autowired	  
EmployeeRepository  employeeRepo;
@GetMapping({"/showEmployees","/list","/"})
public ModelAndView showEmployees() {
	ModelAndView m=new ModelAndView("list_employees");
	List<Employee> list = employeeRepo.findAll();
	m.addObject("employees", list);
//	 System.out.println(list.get(0)); 
	return m;
}
@GetMapping({"/addEmployeeForm","/add","/addEmployee"})
public ModelAndView addEmployeeForm() {
	ModelAndView m1=new ModelAndView("add-employee-form");
	Employee employees =new Employee();
	m1.addObject("employees", employees);
	return m1;
	
}
@PostMapping("/saveEmployee")
public String saveEmployee(@ModelAttribute Employee employees) {
	employeeRepo.save(employees);
	return "redirect:/list";
}
@GetMapping("/showUpdateForm")
public ModelAndView showUpdateForm(@RequestParam Long eId) {
	ModelAndView m=new ModelAndView("add-employee-form");
	Employee employee = employeeRepo.findById(eId).get();
	m.addObject("employees", employee);
	return m;
}
@GetMapping("/deleteEmployee")
public String deletedetails(@RequestParam Long eId) {
	//ModelAndView m=new ModelAndView("add-employee-form");
      employeeRepo.deleteById(eId);
      return "redirect:/list";      
}

}





