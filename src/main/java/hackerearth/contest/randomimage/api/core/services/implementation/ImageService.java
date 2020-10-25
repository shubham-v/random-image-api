package hackerearth.contest.randomimage.api.core.services.implementation;

import hackerearth.contest.randomimage.api.core.entities.Image;
import hackerearth.contest.randomimage.api.core.repositories.ImageRepository;
import hackerearth.contest.randomimage.api.core.services.IImageService;
import hackerearth.contest.randomimage.api.exceptions.ImageNotFoundException;
import hackerearth.contest.randomimage.api.exceptions.RandomImageAPIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Slf4j
@Service
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;

    private static final String RANDOM_IMAGE_GENERATION_URL = "https://picsum.photos/200/300";

    @Autowired
    public ImageService(final ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> getAll() {
        log.info("Fetching all images");
        List<Image> images = this.imageRepository.findAll();
        log.info("Fetched all images: ", images);
        if (images == null || images.size() == 0) throw new ImageNotFoundException("No images were found");
        return images;
    }

    @Override
    public Image getByRequestId(final String id) {
        log.info("Fetching image by id: {}", id);
        Image image = this.imageRepository.findByRequestId(id)
                .orElseThrow(() -> new ImageNotFoundException(String.format("Image with Id: %s not present", id)));
        log.info("Fetched image: ", image);
        return image;
    }

    @Override
    public Image getByIdOrLoadRandom(final String id) throws IOException {
        Image image;
        try {
            image = this.getByRequestId(id);
        } catch (ImageNotFoundException ex) {
            image = Image.builder().requestId(id).url(getRandomImageUrlFromInternet()).build();
            image = this.imageRepository.save(image);
        }
        return image;
    }


    private static String getRandomImageUrlFromInternet() {
        String randomImageURL = null;
        try {
            URL obj = new URL(RANDOM_IMAGE_GENERATION_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == 302) {
                randomImageURL = con.getHeaderField("Location");
            }
        } catch (Exception  ex) {
            throw new RandomImageAPIException("Error getting random image from internet");
        }
        return randomImageURL;
    }
}
