package hackerearth.contest.randomimage.api.utils;

import hackerearth.contest.randomimage.api.core.entities.Image;
import hackerearth.contest.randomimage.api.exceptions.RandomImageAPIException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class ImageDownloader {

    public static void downloadToOutputStream(final Image image, final OutputStream outputStream) {
        InputStream inputStream = null;
        try {
            inputStream = new URL(image.getUrl()).openStream();
            IOUtils.copy(inputStream, outputStream);
        } catch (MalformedURLException e) {
            throw new RandomImageAPIException("URL is not valid");
        } catch (IOException e) {
            throw new RandomImageAPIException("URL is not present");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RandomImageAPIException("Server exception");
                }
            }
        }
    }

}
