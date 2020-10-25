package hackerearth.contest.randomimage.api.exceptions;

public class ImageNotFoundException extends RandomImageAPIException {
    public ImageNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
