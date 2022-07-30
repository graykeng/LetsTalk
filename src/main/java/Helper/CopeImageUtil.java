package Helper;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class CopeImageUtil {
    public CopeImageUtil() {

    }

    public BufferedImage cutHeadImages(String filePath, String outputPath) {
        BufferedImage avatarImage = null;
        try {
            avatarImage = ImageIO.read(new File(filePath));
            avatarImage = scaleByPercentage(avatarImage, 60,  60);

            int width = avatarImage.getWidth();

            BufferedImage formatAvatarImage = new BufferedImage(width, width, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D graphics = formatAvatarImage.createGraphics();

            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int border = 1;

            Ellipse2D.Double shape = new Ellipse2D.Double(border, border, width - border * 2, width - border * 2);

            graphics.setClip(shape);
            graphics.drawImage(avatarImage, border, border, width - border * 2, width - border * 2, null);
            graphics.dispose();
            graphics = formatAvatarImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int border1 = 3;
            Stroke s = new BasicStroke(5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            graphics.setStroke(s);
            graphics.setColor(Color.WHITE);
            graphics.drawOval(border1, border1, width - border1 * 2, width - border1 * 2);
            graphics.dispose();
            OutputStream os = new FileOutputStream(outputPath);
            ImageIO.write(formatAvatarImage, "PNG", os);
            return formatAvatarImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Icon blobToIcon(Blob headshot){
        try{
            byte[] imageByte = headshot.getBytes(1, (int)headshot.length());
            ByteArrayInputStream in = new ByteArrayInputStream(imageByte);
            try{
                BufferedImage bImage = ImageIO.read(in);
                return new ImageIcon(bImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage blobToBufferedImage(Blob headshot){
        try{
            byte[] imageByte = headshot.getBytes(1, (int)headshot.length());
            ByteArrayInputStream in = new ByteArrayInputStream(imageByte);
            try{
                BufferedImage bImage = ImageIO.read(in);
                return bImage;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage scaleByPercentage(BufferedImage inputImage, int newWidth, int newHeight){
        try {
            int type = inputImage.getColorModel().getTransparency();
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();
            RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            BufferedImage img = new BufferedImage(newWidth, newHeight, type);
            Graphics2D graphics2d = img.createGraphics();
            graphics2d.setRenderingHints(renderingHints);
            graphics2d.drawImage(inputImage, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
            graphics2d.dispose();
            return img;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}