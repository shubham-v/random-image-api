package hackerearth.contest.randomimage.api.beans.models.request;

import hackerearth.contest.randomimage.api.beans.models.request.core.CoreRequest;
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
public class GetImageRequest extends CoreRequest {

    private String id;

}
