package neoflex.VacationCalculator.Service;

import java.math.BigDecimal;

public abstract class AbstractVacationCalculationService {

    //Среднее количество дней в месяце невисокосного года
    protected static final double AVERAGE_DAYS_IN_MOUNTH = 29.3;
    //Налог на доход в РФ
    protected static final double NDFL = 0.13;
    //МРОТ на момент 2024 года
    protected static final int MINIMAL_SALARY = 19242;

    //Абстрактный метод для вычисления отпускных
    public abstract BigDecimal Calculations(BigDecimal averageSalary, int vacationDays) throws Exception;
    //Проверка зарплаты на соответствие законодательству
    protected abstract boolean CheckMinimalWage(BigDecimal averageSalary);
    //Проверка правильности данных для рассчета отпускных
    protected abstract boolean CheckValidValues(BigDecimal averageSalary, int vacationDays);

}
