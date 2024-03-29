package neoflex.VacationCalculator.Model;

import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class ServiceResponse {
    private String outputMessage;
    private BigDecimal money;
}
