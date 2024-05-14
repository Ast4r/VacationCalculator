package neoflex.VacationCalculator.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
@AllArgsConstructor
public class VacationMoneyCalculationService {
    //Среднее количество в месяце не високосного года
    private static final double AVERAGE_DAYS_IN_MOUNTH = 29.3;
    //Налог на доход в РФ
    private static final double NDFL = 0.13;

    public BigDecimal Calculations(BigDecimal averageSalary, int vacationDays) throws Exception {
        //Проверка на допустимость значений,
        //зарплата не может быть 0 или отрицательной,
        //отпуск не может быть меньше 1 дня и больше 31
        if(!CheckValidValues(averageSalary, vacationDays))
            throw new Exception("Используются недопустимые значения");

        //Проверка соответствует ли значение средней зарплаты МРОТ на 2024 год
        if(!CheckMinimalWage(averageSalary))
            throw new Exception("Зарплата не соответствует МРОТ");

        //Подсчет отпускных без учета выходных и празников
        BigDecimal totalPay = averageSalary.divide(BigDecimal.valueOf(AVERAGE_DAYS_IN_MOUNTH), 2, RoundingMode.HALF_DOWN);

        totalPay = totalPay.multiply(BigDecimal.valueOf(vacationDays).setScale(0, RoundingMode.HALF_UP));

        BigDecimal totalPayWithNDFL = totalPay.multiply(BigDecimal.valueOf(NDFL)).setScale(2, RoundingMode.HALF_UP);

        totalPayWithNDFL = totalPay.subtract(totalPayWithNDFL);

        return totalPayWithNDFL;
    }

    private boolean CheckMinimalWage(BigDecimal averageSalary){
        return averageSalary.intValue() >= 19242;
    }
    private boolean CheckValidValues(BigDecimal averageSalary, int vacationDays){
        if(averageSalary.intValue() <= 0 || (vacationDays > 31 || vacationDays < 1))
            return false;
        return true;
    }
}
