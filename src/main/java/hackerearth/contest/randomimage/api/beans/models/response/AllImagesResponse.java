package hackerearth.contest.randomimage.api.beans.models.response;

import hackerearth.contest.randomimage.api.beans.models.response.core.CoreResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AllImagesResponse extends CoreResponse {

    private List<String> imageUrls;

}
