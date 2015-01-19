/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MakeVideo;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * 
 * @author arvind
 */
public class CreateImages extends Thread {
	Robot robo;
	private static ImageIcon screenShot;
	int imgCount;

	public void run() {
		CreateImages.screenShot = this.capture(imgCount++);
		CreateImages reCapture = new CreateImages();
		reCapture.start();
	}

	public static ImageIcon getScreenShot() {
		return CreateImages.screenShot;
	}

	private ImageIcon capture(int name) {
		ImageIcon screenShot = null;
		try {
			robo = new Robot();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle scrnRect = new Rectangle(dim);
			BufferedImage image = robo.createScreenCapture(scrnRect);

			screenShot = new ImageIcon(image);
			// File img = new File("images/"+name+".jpg");
			// ImageIO.write(image, "jpg", img);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		/*
		 * catch (IOException ex) {
		 * Logger.getLogger(CreateVideo.class.getName()).log(Level.SEVERE, null,
		 * ex); }
		 */
		return screenShot;
	}

}