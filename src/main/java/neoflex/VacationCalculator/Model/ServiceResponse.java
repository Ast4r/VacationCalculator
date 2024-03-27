package neoflex.VacationCalculator.Model;

import java.math.BigDecimal;

public class ServiceResponse {
    private String outputMessage;
    private BigDecimal money;

    public ServiceResponse(String outputMessage, BigDecimal money){
        this.outputMessage = outputMessage;
        this.money = money;
    }
    public String getOutputMessage(){
        return outputMessage;
    }
    public void setOutputMessage(String outputMessage){
        this.outputMessage = outputMessage;
    }
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
