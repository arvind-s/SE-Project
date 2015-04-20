package rtspWithMultipleClients;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MultipleClientHandler implements Runnable
{
	Socket client;
	int count = 0;

	public MultipleClientHandler(Socket client)
	{
		this.client = client;
	}

	public void run()
	{
		ObjectOutputStream clientStream = null;
		try
		{
			clientStream = new ObjectOutputStream(this.client.getOutputStream());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// change true to a condition variable
		try
		{
			// ImageIcon icon = null;
			ImageIcon image = null;
			while (true)
			{
				// icon = CreateImages.getScreenShot();
				// clientStream.writeObject(icon);
				// icon = null;
				// Read from file and write to stream
				for (int name = 0;; name++)
				{
					File imageFile = new File(
							"E://TEMP//"
									+ name + ".jpg");
					if (imageFile.exists())
					{
						image = new ImageIcon(ImageIO.read(imageFile));
					}
					else
					{
						name--;
					}
					clientStream.writeObject(image);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				clientStream.close();
				this.client.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
