package hackerearth.contest.randomimage.api.convertors.request;

import hackerearth.contest.randomimage.api.exceptions.InvalidRequestException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetImageRequestConvertor {

    private String id;

    public Long convertId() {
        Long converted;
        try {
            converted = Long.valueOf(id);
        } catch (Exception ex) {
            throw InvalidRequestException.builder().build().putErrorMessage("id", "id must not be null");
        }
        return converted;
    }

}
