package com.findServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class DSender
{
	public static void main(String[] args) throws Exception
	{
		DatagramSocket ds = new DatagramSocket();
		
		String ipad = getIpAddress();
		
		String serverIP = ipad;
		System.out.println(ipad);
		String ipParts[] = ipad.split("[.]");
		String inetName = ipParts[0] + "." + ipParts[1] + "." + ipParts[2] + ".255";
		
		InetAddress ip = InetAddress.getByName(inetName);

		DatagramPacket dp = new DatagramPacket(serverIP.getBytes(), serverIP.length(),
				ip, 3000);
		ds.send(dp);
		ds.close();
	}

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
}
