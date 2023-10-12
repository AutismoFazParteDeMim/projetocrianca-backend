package br.com.unisociesc.projetocrianca.Utils;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class ImageUtils {

    public static byte[] toByteArray(BufferedImage logo) throws IOException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(logo, "png", stream);
        byte[] logoFormat = stream.toByteArray();

        return logoFormat;
    }

    public static BufferedImage toBufferedImage(byte[] logo) throws IOException {
        InputStream is = new ByteArrayInputStream(logo);
        BufferedImage logoFormat = ImageIO.read(is);

        return logoFormat;
    }
}
