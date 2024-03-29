package neoflex.VacationCalculator.Controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import neoflex.VacationCalculator.Service.VacationMoneyCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class VacationCalculatorController {

    @NonNull
    private VacationMoneyCalculationService service;

    @GetMapping("/calculate")
    @ResponseBody
    public ResponseEntity<?> getVacationMoney(@RequestParam("averageSalary") BigDecimal averageSalary,
                                              @RequestParam("vacationDays") int vacationDays){
        BigDecimal money = service.Calculations(averageSalary, vacationDays);
        return ResponseEntity.ok(money);

}
}
