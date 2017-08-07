package me.moosecanswim.resumegen;

import me.moosecanswim.resumegen.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/addemployee")
    public String addemployee(Model toSend){
        toSend.addAttribute("newEmployee", new Employee());
        return "addemployee";
    }
    @PostMapping("/addemployee")
    public String addemployee2(@Valid @ModelAttribute("newEmployee") Employee thisGuy, BindingResult bindingResult, Model toSend){
        if(bindingResult.hasErrors()){
            String messageToSend = "There was an issue with the employee added.  Please try again";
            toSend.addAttribute("message",messageToSend);
            System.out.println("**Here is an issue**"  + bindingResult.toString());
            return "addemployee";
        }
        thisGuy.setDaysWorking(thisGuy.getStartDate(),thisGuy.getEndDate());
        employeeRepository.save(thisGuy);
        return"displayemployee";
    }
    @GetMapping("/showallemployees")
    public String showAll(Model model){
        Iterable<Employee> employeeList = employeeRepository.findAll();
        model.addAttribute("listOfEmployee",employeeList);


        return "showallemployees";
    }

}
