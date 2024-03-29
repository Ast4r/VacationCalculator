package neoflex.VacationCalculator.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
@AllArgsConstructor
public class VacationMoneyCalculationService {
    private static final double AVERAGE_DAYS_IN_MOUNTH = 29.3;
    private static final double NDFL = 0.13;

    public BigDecimal Calculations(BigDecimal averageSalary, int vacationDays) throws Exception {
        BigDecimal totalPay = averageSalary.divide(BigDecimal.valueOf(AVERAGE_DAYS_IN_MOUNTH), 2, RoundingMode.HALF_DOWN);

        totalPay = totalPay.multiply(BigDecimal.valueOf(vacationDays).setScale(0, RoundingMode.HALF_UP));

        BigDecimal totalPayWithNDFL = totalPay.multiply(BigDecimal.valueOf(NDFL).setScale(2, RoundingMode.HALF_UP));

        totalPayWithNDFL = totalPay.subtract(totalPayWithNDFL);

        return totalPayWithNDFL;
    }
}
