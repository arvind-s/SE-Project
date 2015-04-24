package rtspWithMultipleClients;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;

public class MultipleClientHandler implements Runnable {
	Socket client;
	int count = 0;

	public MultipleClientHandler(Socket client) {
		this.client = client;
	}

	public void run() {
		DataOutputStream clientStream = null;
		try {
			clientStream = new DataOutputStream(this.client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// change true to a condition variable
		try {
			// ImageIcon icon = null;
			ImageIcon image = null;
			while (true) {
				// icon = CreateImages.getScreenShot();
				// clientStream.writeObject(icon);
				// icon = null;
				// Read from file and write to stream
				for (int name = 0;; name++) {
					Thread.sleep(50);
					File imageFile = new File("E:\\TEMP\\" + name + ".jpg");
					FileInputStream fi = new FileInputStream(imageFile);
					// Path path = Paths.get("E:\\TEMP\\"+ name + ".jpg");
					System.out.println(imageFile.length());
					if (imageFile.exists()) 
					{
//						int r;
//						do
//						{
//							byte img[] = new byte[65000];
//							r = fi.read(img);
//							clientStream.write(img);
//							System.out.println("R,i:"+r+","+name);
//							
//						}while(r==65000);
						while (true)
                        {
                            byte buffer[] = new byte[160000];
                            int read = fi.read(buffer);
                            if (read < 0)
                            {
                                break;
                            }
                            clientStream.write(buffer,0,read);
                        }
						fi.close();
					}

					 else 
					 {
						name--;
					}
					
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				clientStream.close();
				this.client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
