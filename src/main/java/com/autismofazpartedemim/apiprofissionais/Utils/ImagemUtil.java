package com.autismofazpartedemim.apiprofissionais.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImagemUtil {

    public static byte[] toByteArray(BufferedImage logo) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(logo, "png", baos);
        byte[] logoFormat = baos.toByteArray();

        return logoFormat;
    }

    public static BufferedImage toBuferedImage(byte[] logo) throws IOException {
        InputStream is = new ByteArrayInputStream(logo);
        BufferedImage logoFormat = ImageIO.read(is);

        return logoFormat;
    }
}
