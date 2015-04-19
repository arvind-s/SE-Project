package utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Addressing
{
	public static String getIpAddress()
	{
		String ip = "";
		try
		{
			Enumeration<NetworkInterface> interfaces = NetworkInterface
					.getNetworkInterfaces();
			while (interfaces.hasMoreElements())
			{
				NetworkInterface iface = interfaces.nextElement();
				// filters out 127.0.0.1 and inactive interfaces
				if (iface.isLoopback() || !iface.isUp())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while (addresses.hasMoreElements())
				{
					InetAddress addr = addresses.nextElement();
					ip = addr.getHostAddress();
					break;
				}
				break;
			}
			return ip;
		}
		catch (SocketException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static String getMacAddress()
	{
		try
		{
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();

//			System.out.print("The mac address is : ");

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++)
			{
				//System.out.println(mac[i]);
				sb.append(String.format("%02X%s", mac[i],
						(i < mac.length - 1) ? "-" : ""));
			}

			return sb.toString();
		}
		catch (Exception ex)
		{
			System.out.println("Exception caught:"+ex.getMessage());
		}
		return null;
	}

}