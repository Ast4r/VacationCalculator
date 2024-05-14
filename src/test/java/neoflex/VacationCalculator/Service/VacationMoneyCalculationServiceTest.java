package neoflex.VacationCalculator.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class VacationMoneyCalculationServiceTest {
    private VacationMoneyCalculationService vacationMoneyCalculationService;

    @BeforeEach
    void init(){
        vacationMoneyCalculationService = new VacationMoneyCalculationService();
    }
    @Test
    void vacationMoneyCalculationServiceTest() {
        BigDecimal averageSalary = new BigDecimal("100000");
        int vacationDays = 28;

        BigDecimal result;
        try {
            result = vacationMoneyCalculationService.Calculations(averageSalary, vacationDays);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(result.setScale(0, BigDecimal.ROUND_DOWN), BigDecimal.valueOf(83139));
    }
    @Test
    void vacationMoneyCalculationServiceFailureTest(){
        BigDecimal averageSalary = new BigDecimal("100");
        int vacationDays = 28;

        BigDecimal result;
        try {
            result = vacationMoneyCalculationService.Calculations(averageSalary, vacationDays);
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), "Зарплата не соответствует МРОТ");
        }

    }
}
