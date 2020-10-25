package hackerearth.contest.randomimage.api.beans.models.response.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class CoreResponse {

    private String responseId;
    private Long responseTimestamp;

    private Map<String, List<String>> errorMessages = new LinkedHashMap<>();

    public void addErrorMessage(final String errorKey, final String errorMessage) {
        List<String> errorMessages = this.errorMessages.get(errorKey);
        if (Objects.isNull(errorMessages)) {
            errorMessages = new ArrayList<>();
            this.errorMessages.put(errorKey, errorMessages);
        }
        errorMessages.add(errorMessage);
    }

    public void addErrorMessages(final Map<String, List<String>> errorMessages) {
        errorMessages.forEach((k, v) -> v.forEach(errorMessage -> this.addErrorMessage(k, errorMessage)));
    }

}
