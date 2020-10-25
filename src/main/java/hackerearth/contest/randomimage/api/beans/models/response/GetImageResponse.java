package hackerearth.contest.randomimage.api.beans.models.response;

import hackerearth.contest.randomimage.api.beans.models.response.core.CoreResponse;
import hackerearth.contest.randomimage.api.core.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetImageResponse extends CoreResponse {
    private Image image;
}
