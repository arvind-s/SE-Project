package rtsp;

import java.io.*;

import constants.Constants;
public class RunRTSPClientPython 
{
	public static void runClient(String ip, String fileName)
	{
		try
		{
			StringBuffer output = new StringBuffer();
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec("python client.py "+ip+" "+Constants.videoClientPort+" "+fileName);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));//for error use pr.getErrorStream();
			String line = "";			
			while ((line = reader.readLine())!= null) 
			{
				output.append(line + "\n");
			}
			System.out.println(output.toString());
			
			reader = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			line = "";			
			output = new StringBuffer();
			while ((line = reader.readLine())!= null) 
			{
				output.append(line + "\n");
			}
			System.out.println(output.toString());
			
			
			pr.waitFor();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}