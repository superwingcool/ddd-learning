package parkinglot;

import java.util.Date;

public class TicketRule {

    private TicketRuleType ticketRuleType = TicketRuleType.TIME_RULE;

    private Long content = 24 * 60 * 1000l;

    public boolean isValid(Date startTime) {
        if(ticketRuleType == TicketRuleType.TIME_RULE) {
            if(System.currentTimeMillis() - startTime.getTime() > content) {
                return false;
            }
        }
        return true;
    }

}
