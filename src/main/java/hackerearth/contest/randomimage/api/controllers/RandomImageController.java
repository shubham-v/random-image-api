package hackerearth.contest.randomimage.api.controllers;

import hackerearth.contest.randomimage.api.beans.models.response.AllImagesResponse;
import hackerearth.contest.randomimage.api.beans.models.response.GetImageResponse;
import hackerearth.contest.randomimage.api.beans.models.request.GetImageRequest;
import hackerearth.contest.randomimage.api.convertors.request.GetImageRequestConvertor;
import hackerearth.contest.randomimage.api.core.entities.Image;
import hackerearth.contest.randomimage.api.core.services.IImageService;
import hackerearth.contest.randomimage.api.exceptions.ImageNotFoundException;
import hackerearth.contest.randomimage.api.exceptions.InvalidRequestException;
import hackerearth.contest.randomimage.api.exceptions.RandomImageAPIException;
import hackerearth.contest.randomimage.api.utils.ImageDownloader;
import hackerearth.contest.randomimage.api.validators.GetImageRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class RandomImageController {

    private final IImageService imageService;
    private final GetImageRequestValidator requestValidator;

    @Autowired
    public RandomImageController(final IImageService imageService, final GetImageRequestValidator requestValidator) {
        this.imageService = imageService;
        this.requestValidator = requestValidator;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getImage(@PathVariable String id, final HttpServletResponse httpServletResponse) {
        Long startTime = System.currentTimeMillis();
        GetImageResponse response = GetImageResponse.builder().build();
        log.info("Received request: GET /{}", id);

        try {
            GetImageRequest request = GetImageRequest.builder().id(id).build();
            this.requestValidator.isValid(request)
                    .orElseThrow(() ->
                            InvalidRequestException.builder().build().putErrorMessages(this.requestValidator.getMessages()));
            Image image = this.imageService.getByIdOrLoadRandom(id);
            httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
            ImageDownloader.downloadToOutputStream(image, httpServletResponse.getOutputStream());
        } catch (InvalidRequestException ex) {
            log.error("Invalid Request Exception", ex);
            response.addErrorMessages(ex.getErrorMessages());
        } catch (RandomImageAPIException ex) {
            log.error("API Exception: ", ex);
            response.addErrorMessage("API Exception", ex.getMessage());
        } catch (Exception ex) {
            log.error("Exception: ", ex);
            response.addErrorMessage("Exception", ex.getMessage());
        }

        response.setResponseId(UUID.randomUUID().toString());
        response.setResponseTimestamp(System.currentTimeMillis());
        log.info("Responding to request: GET /{} ; Response Time: {} milliseconds", id,
                response.getResponseTimestamp() - startTime);
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public AllImagesResponse getAllImages() {
        Long startTime = System.currentTimeMillis();
        AllImagesResponse response = AllImagesResponse.builder().build();
        log.info("Received request: GET /images");

        try {
            List<String> imageUrls = this.imageService.getAll()
                    .stream()
                    .map(Image::getUrl)
                    .collect(Collectors.toList());
            response.setImageUrls(imageUrls);
        } catch (ImageNotFoundException ex) {
            log.error("Images Not Found Exception: ", ex);
            response.addErrorMessage("Image Not Found Exception", ex.getMessage());
        } catch (RandomImageAPIException ex) {
            log.error("API Exception: ", ex);
            response.addErrorMessage("API Exception", ex.getMessage());
        } catch (Exception ex) {
            log.error("Exception: ", ex);
            response.addErrorMessage("Exception", ex.getMessage());
        }

        response.setResponseId(UUID.randomUUID().toString());
        response.setResponseTimestamp(System.currentTimeMillis());
        log.info("Responding to request: GET /images ; Response: {} ;  Response Time: {} milliseconds", response,
                response.getResponseTimestamp() - startTime);
        return response;
    }

}
