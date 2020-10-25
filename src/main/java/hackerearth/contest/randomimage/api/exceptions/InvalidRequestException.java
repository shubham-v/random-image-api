package hackerearth.contest.randomimage.api.exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@Builder
public class InvalidRequestException extends RuntimeException {

    private Map<String, List<String>> errorMessages = new LinkedHashMap<>();

    public InvalidRequestException putErrorMessage(final String errorKey, final String errorMessage) {
        List<String> errorMessages = this.errorMessages.get(errorKey);
        if (Objects.isNull(errorMessages)) {
            errorMessages = new ArrayList<>();
            this.errorMessages.put(errorKey, errorMessages);
        }
        errorMessages.add(errorMessage);
        return get();
    }

    public InvalidRequestException putErrorMessages(final Map<String, List<String>> errorMessages) {
        errorMessages.forEach((k, v) -> v.forEach(errorMessage -> this.putErrorMessage(k, errorMessage)));
        return get();
    }

    private InvalidRequestException get() {
        return this;
    }

}
