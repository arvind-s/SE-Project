package rtspWithMultipleClients;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Client
{
	Socket server;
	static int picCount;
	ArrayList<ImageIcon> imageListBuffer;
	ClientImageReciever startBuffer;

	public Client()
	{
		imageListBuffer = new ArrayList<ImageIcon>();
		try
		{
			this.server = new Socket("localhost", 1234);
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.bufferImageIconList();
	}

	private void bufferImageIconList()
	{
		ObjectInputStream inStream = null;
		try
		{
			inStream = new ObjectInputStream(this.server.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		startBuffer = new ClientImageReciever(this.imageListBuffer, inStream);
		startBuffer.start();
	}

	public void play()
	{
		// Method to start or continue playing
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(dm);
		frame.setUndecorated(true);
		ImageIcon icon = imageListBuffer.get(0);
		JLabel image = new JLabel(icon);
		frame.add(image);
		frame.setVisible(true);
		for (int i = Client.picCount; i < imageListBuffer.size(); i++)
		{
			try
			{
				Thread.sleep(140);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			icon = imageListBuffer.get(i);
			image.setIcon(icon);
			frame.repaint();
		}
		
	}

	public void pause()
	{
		// Method to pause the video
		startBuffer.reset();
	}

	public void stop()
	{
		try
		{
			this.server.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
