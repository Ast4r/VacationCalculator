package neoflex.VacationCalculator.Controller;

import neoflex.VacationCalculator.Model.ServiceResponse;
import neoflex.VacationCalculator.Service.VacationMoneyCalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
@RestController
public class VacationCalculatorController {
    private VacationMoneyCalculationService service;

    public VacationCalculatorController(VacationMoneyCalculationService service){
        this.service = service;
    }
@GetMapping("/calculate")
    public ServiceResponse getVacationMoney(@RequestParam("averageSalary") BigDecimal averageSalary,
                                            @RequestParam("vacationDays") int vacationDays){
        return service.Calculations(averageSalary, vacationDays);
}
}
