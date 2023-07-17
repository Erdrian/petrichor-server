package com.petrichor.sincerity.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class CaptchaUtil {

    @Autowired
    DefaultKaptcha getCaptcha;

    public String getCaptchaBase64(String text) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BufferedImage image = getCaptcha.createImage(text);
        ImageIO.write(image, "jpg", stream);
        String encoded = Base64.encodeBase64String(stream.toByteArray());
        return "data:image/png;base64," + encoded;
    }
}
