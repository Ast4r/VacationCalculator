package neoflex.VacationCalculator.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

//Класс используется в ResponseEntity для передачи ответа пользователю
@Data
@AllArgsConstructor
public class ServiceResponse {
    @JsonProperty("outputMessage")
    private String outputMessage;
    @JsonProperty("money")
    private BigDecimal money;
}
