package hackerearth.contest.randomimage.api.beans.models.request.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class CoreRequest {

    private String requestId;
    private String requestTimestamp;

}
