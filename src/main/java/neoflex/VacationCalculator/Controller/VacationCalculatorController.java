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

    //API передающее результат отпускных и сообщение в формате json
    @GetMapping("/calculate")
    @ResponseBody
    public ResponseEntity<ServiceResponse> getVacationMoney(@RequestParam("averageSalary") BigDecimal averageSalary,
                                                            @RequestParam("vacationDays") int vacationDays){
        //Проверка на ошибки при работе сервиса
        try {
            BigDecimal money = service.Calculations(averageSalary, vacationDays);
            return new ResponseEntity<>(new ServiceResponse("Отпускные", money), HttpStatus.OK);
        }
        //При ошибке передает код ответа 500(ошибка сервера)
        catch (Error | Exception e){
            return new ResponseEntity<>(new ServiceResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
