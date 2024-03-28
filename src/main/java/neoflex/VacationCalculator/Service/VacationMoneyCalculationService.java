package neoflex.VacationCalculator.Service;

import neoflex.VacationCalculator.Model.ServiceResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Service
public class VacationMoneyCalculationService {
    private static double AVERAGE_DAYS_IN_MOUNTH = 29.3;
    private static double NDFL = 0.13;
    public VacationMoneyCalculationService(){}

    public ServiceResponse Calculations(BigDecimal averageSalary, int vacationDays){
        BigDecimal totalPay = averageSalary.divide(BigDecimal.valueOf(AVERAGE_DAYS_IN_MOUNTH),2, RoundingMode.HALF_DOWN);

        totalPay = totalPay.multiply(BigDecimal.valueOf(vacationDays).setScale(0, RoundingMode.HALF_UP));

        BigDecimal totalPayWithNDFL = totalPay.multiply(BigDecimal.valueOf(NDFL).setScale(2, RoundingMode.HALF_UP));

        totalPayWithNDFL = totalPay.subtract(totalPayWithNDFL);

        return new ServiceResponse("Сумма отпускных", totalPayWithNDFL);
    }
}
