package br.com.unisociesc.projetocrianca;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public final class Utils {

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

  /**
   * Get all null properties in a DTO.
   * 
   * @param source - Must be no null.
   * @return A list with all null properties.
   * @author Brian Duarte
   */
  public static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] propertyDescriptorList = src.getPropertyDescriptors();
    List<String> nullProperties = new ArrayList<>();
    for (java.beans.PropertyDescriptor pd : propertyDescriptorList) {
      if (src.getPropertyValue(pd.getName()) == null) {
        System.out.println(pd.getName());
        System.out.println("==========================");
        nullProperties.add(pd.getName());
      }
    }
    return nullProperties.toArray(new String[0]);
  }
}
