package neoflex.VacationCalculator.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

//Сервис, которые считает отпускные, учитывая праздничные дни
@Service("VacationMoneyWithDaysCalculationService")
@AllArgsConstructor
public class VacationMoneyWithDaysCalculationService extends VacationMoneyCalculationService{

    public BigDecimal Calculations(BigDecimal averageSalary, int numberOfDays, String startDate) throws Exception {
        //Преобразует переданную строку в дату
        LocalDate firstDay = ParseDateFromString(startDate);
        //Подсчет дней отпуска для рассчета отпускных с учетом праздников
        int vacationDays = FindNumberOfVacationDays(firstDay, numberOfDays);
        //После нахождения количества дней используется метод класса родителя для рассчета
        return this.Calculations(averageSalary, vacationDays);
    }

    //Метод пытается преобразовать строку в дату согласно российскому стандарту записи
    private LocalDate ParseDateFromString(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return LocalDate.from(dateTimeFormatter.parse(date));
    }
    private int FindNumberOfVacationDays(LocalDate startDate, int days){
        Set<LocalDate> holidays = new HashSet<>();
        LocalDate endDate = startDate.plusDays(days);

        //Собираем праздничные дни для всех лет в промежутке
        for (int year = startDate.getYear(); year <= endDate.getYear(); year++) {
            holidays.addAll(getRussianHolidays(year));
        }

        int vacationDays = 0;

        //Проходимся по дням между началом и концом отпуска
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            //Если день не праздничный, считаем его как отпускной
            if (!holidays.contains(currentDate)) {
                vacationDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return vacationDays;
    }
    private Set<LocalDate> getRussianHolidays(int year){
        Set<LocalDate> holidays = new HashSet<>();

        //Новый год
        holidays.add(LocalDate.of(year, 1, 1));
        holidays.add(LocalDate.of(year, 1, 2));
        holidays.add(LocalDate.of(year, 1, 3));
        holidays.add(LocalDate.of(year, 1, 4));
        holidays.add(LocalDate.of(year, 1, 5));
        holidays.add(LocalDate.of(year, 1, 6));
        //Рождество
        holidays.add(LocalDate.of(year, 1, 7));
        holidays.add(LocalDate.of(year, 1, 8));

        //День защитника Отечества
        holidays.add(LocalDate.of(year, 2, 23));

        //Международный женский день
        holidays.add(LocalDate.of(year, 3, 8));

        //День труда
        holidays.add(LocalDate.of(year, 5, 1));

        //День Победы
        holidays.add(LocalDate.of(year, 5, 9));

        //День России
        holidays.add(LocalDate.of(year, 6, 12));

        //День народного единства
        holidays.add(LocalDate.of(year, 11, 4));

        return holidays;
    }
}
