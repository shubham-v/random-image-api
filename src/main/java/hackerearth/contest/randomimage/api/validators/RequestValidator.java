package hackerearth.contest.randomimage.api.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public abstract class RequestValidator {

    private Map<String, List<String>> validationMessages = new LinkedHashMap<>();
    private int messagesCount;

    protected void addMessage(final String validationKey, final String validationMessage) {
        List<String> validationMessages = this.validationMessages.get(validationKey);
        if (Objects.isNull(validationMessages)) {
            validationMessages = new ArrayList<>();
            this.validationMessages.put(validationKey, validationMessages);
        }
        validationMessages.add(validationMessage);
        this.messagesCount++;
    }

    public Map<String, List<String>> getMessages() {
        return new LinkedHashMap<>(this.validationMessages);
    }

    protected int getMessagesCount() {
        return this.messagesCount;
    }

    protected boolean isValid() {
        return this.getMessagesCount() == 0;
    }

}
