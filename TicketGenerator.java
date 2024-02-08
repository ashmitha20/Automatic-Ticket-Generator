import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TicketGenerator {

    public static void main(String[] args) {
        String[] names = {}; // Replace with your list of names
        String[] qrCodePaths = {""}; // Replace with your QR code file paths
        // Load the certificate template image
        BufferedImage certificate = null;
        try {
            certificate = ImageIO.read(new File("")); // Replace with your certificate template image path
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (certificate != null && names.length == qrCodePaths.length) {
            Graphics2D g2d;

            // Coordinates where the name should be placed
            int x = 800; // Adjust according to your certificate template
            int y = 700; // Adjust according to your certificate template

            for (int i = 0; i < names.length; i++) {
                // Create a new BufferedImage for each certificate
                BufferedImage certificateWithText = new BufferedImage(certificate.getWidth(), certificate.getHeight(), certificate.getType());
                g2d = certificateWithText.createGraphics();

                // Copy the original certificate onto the new image
                g2d.drawImage(certificate, 0, 0, null);

                // Set the font for the name
                try {
                    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("")); // Replace with your font file path
                    g2d.setFont(customFont.deriveFont(Font.BOLD, 100)); // Set font and size
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Draw the name on the new image
                g2d.setColor(Color.BLACK);
                g2d.drawString(names[i], x, y);

                // Load QR code image from file
                BufferedImage qrCodeImage = null;
                try {
                    qrCodeImage = ImageIO.read(new File(qrCodePaths[i]));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Place the QR code on the certificate
                if (qrCodeImage != null) {
                    g2d.drawImage(qrCodeImage, x + 50, y + 150, null); // Adjust QR code placement
                }

                // Save each certificate with a unique name
                try {
                    ImageIO.write(certificateWithText, "png", new File(names[i] + "_certificate.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    g2d.dispose(); // Dispose of the graphics context to release resources
                }
            }
        }
    }
}
