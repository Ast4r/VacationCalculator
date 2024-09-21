package neoflex.VacationCalculator.Controller;

import lombok.NonNull;
import neoflex.VacationCalculator.Model.ServiceResponse;
import neoflex.VacationCalculator.Service.VacationMoneyCalculationService;
import neoflex.VacationCalculator.Service.VacationMoneyWithDaysCalculationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class VacationCalculatorController {

    @NonNull
    private final VacationMoneyCalculationService SimpleService;
    @NonNull
    private final VacationMoneyWithDaysCalculationService CalculationWithDaysService;

    public VacationCalculatorController(@Qualifier("VacationMoneyCalculationService") VacationMoneyCalculationService simpleService,
                                        @Qualifier("VacationMoneyWithDaysCalculationService") VacationMoneyWithDaysCalculationService calculationWithDaysService) {
        SimpleService = simpleService;
        CalculationWithDaysService = calculationWithDaysService;
    }


    //API передающее результат отпускных и сообщение в формате json
    @GetMapping("/calculate")
    @ResponseBody
    public ResponseEntity<ServiceResponse> getVacationMoney(@RequestParam("averageSalary") BigDecimal averageSalary,
                                                            @RequestParam("vacationDays") int vacationDays,
                                                            @RequestParam("startOfVacation") Optional<String> startDate){
        //Проверка на ошибки при работе сервиса
        try {
            //Если был передан дополнительный аргумент, то используется другой сервис
            if(startDate.isPresent()){
                BigDecimal money = CalculationWithDaysService.Calculations(averageSalary, vacationDays, startDate.get());
                return new ResponseEntity<>(new ServiceResponse("Отпускные", money), HttpStatus.OK);
            }
            else {
                BigDecimal money = SimpleService.Calculations(averageSalary, vacationDays);
                return new ResponseEntity<>(new ServiceResponse("Отпускные", money), HttpStatus.OK);
            }
        }
        //При ошибке передает код ответа 500(ошибка сервера)
        catch (Error | Exception e){
            return new ResponseEntity<>(new ServiceResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
