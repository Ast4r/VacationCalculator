package neoflex.VacationCalculator.Controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import neoflex.VacationCalculator.Model.ServiceResponse;
import neoflex.VacationCalculator.Service.VacationMoneyCalculationService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ServiceResponse> getVacationMoney(@RequestParam("averageSalary") BigDecimal averageSalary,
                                                            @RequestParam("vacationDays") int vacationDays){
        try {
            BigDecimal money = service.Calculations(averageSalary, vacationDays);
            return new ResponseEntity<>(new ServiceResponse("Отпускные", money), HttpStatus.OK);
        }
        catch (Error | Exception e){
            return new ResponseEntity<>(new ServiceResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
