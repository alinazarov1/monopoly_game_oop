import java.awt.Graphics;
import java.awt.Image;
import java.util.function.IntSupplier;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardBackgroundPanel extends JPanel {
    private final Image backgroundImage;
    private final IntSupplier boardPixelSupplier;

    public BoardBackgroundPanel(String imagePath, IntSupplier boardPixelSupplier) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        this.boardPixelSupplier = boardPixelSupplier;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int boardPixels = boardPixelSupplier.getAsInt();
            g.drawImage(backgroundImage, 0, 0, boardPixels, boardPixels, this);
        }
    }
}
