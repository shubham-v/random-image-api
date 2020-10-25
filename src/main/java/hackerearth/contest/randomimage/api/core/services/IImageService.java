package hackerearth.contest.randomimage.api.core.services;

import hackerearth.contest.randomimage.api.core.entities.Image;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    List<Image> getAll();
    Image getByRequestId(String id);
    Image getByIdOrLoadRandom(String id) throws IOException;
}
