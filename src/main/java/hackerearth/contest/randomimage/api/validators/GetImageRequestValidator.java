package hackerearth.contest.randomimage.api.validators;

import hackerearth.contest.randomimage.api.beans.models.request.GetImageRequest;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetImageRequestValidator extends RequestValidator {

    public Optional<Boolean> isValid(GetImageRequest request) {
        try {
            Long.valueOf(request.getId());
        } catch (Exception ex) {
            this.addMessage("id", "id must be a number");
        }
        return Optional.ofNullable(isValid());
    }

}
