package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.LauncherLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MemoryUtils
{
	private static ArrayList<Memory> memories = new ArrayList<Memory>();
	private static int selected = 0;
	private static File memoryFolder = new File(DirUtils.getLauncherDirectory() + File.separator + "memories");
	
	public static ArrayList<Memory> loadMemories()
	{
		memories = new ArrayList<Memory>();
		memories.add(new Memory("258 MB", "258m"));
		memories.add(new Memory("512 MB", "512m"));
		memories.add(new Memory("768 MB", "768m"));
		memories.add(new Memory("1 GB", "1024m"));
		memories.add(new Memory("1.5 GB", "1536m"));
		memories.add(new Memory("2 GB", "2048m"));
		memories.add(new Memory("3 GB", "3072m"));
		memories.add(new Memory("4 GB", "4096m"));	
		
		File[] memoryFiles = getMemoriesFolder().listFiles();
		
		int i;
		for(i = 0; i < memoryFiles.length; i++)
		{
			if(memoryFiles[i].getName().endsWith(".memory"))
			{
				Memory memory = loadMemoryFile(memoryFiles[i]);
				if(memory != null)
				{
					memories.add(memory);
				}
			}
		}
		
		if(i > 0)
		{
			LauncherLogger.log(LauncherLogger.INFO, "Loaded " + i + " memory files!");
		}
		else
		{
			LauncherLogger.log(LauncherLogger.INFO, "No memory file loaded!");
		}
		return memories;
	}
	
	public static boolean createCustom(String name, String size)
	{
		for(Memory current : memories)
		{
			if(current.name.equals(name))
			{
				return false;
			}
		}
		memories.add(new Memory(name, size));
		writeMemoryFile(name, size);
		return true;
	}
	
	public static void setCurrentMem(String name)
	{
		for(int i = 0; i < memories.size(); i++)
		{
			if(memories.get(i).name.equals(name))
			{
				selected = i;
				break;
			}
		}
	}
	
	public static Memory getMem(String name)
	{
		for(Memory mem : memories)
		{
			if(mem.name.equals(name))
			{
				return mem;
			}
		}
		return null;
	}
	
	public static Memory getCurrentMem()
	{
		if(memories.get(selected) != null)
		{
			return memories.get(selected);
		}
		return null;
	}
	
    public static String[] getMemoryNames()
    {
    	String[] list = new String[memories.size()];
    	for(int i = 0; i < list.length; i++)
    	{
    		list[i] = memories.get(i).name;
    	}
    	
    	return list;
    }
	
	private static void writeMemoryFile(String memoryName, String memorySize)
	{
		File file = new File(getMemoriesFolder() + File.separator + memoryName + ".memory");
	    BufferedWriter bw;
		try
		{
			bw = new BufferedWriter(new FileWriter(file));
			bw.write("memoryName:" + memoryName);
			bw.newLine();
			bw.write("memorySize:" + memorySize); 
			bw.close();
		}
		catch (IOException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on creating '" + memoryName + ".memory' file!");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	private static Memory loadMemoryFile(File memoryFile)
	{
		BufferedReader br;
		Memory mem = new Memory("","");
		try
		{
			br = new BufferedReader(new FileReader(memoryFile));
            String readed = br.readLine();
            while(readed != null)
            {
            	if(readed.startsWith("memoryName"))
            	{
            		for(int i = 1; i < readed.split(":").length; i++)
            		{
            			mem.name += readed.split(":")[i];
            		}
            	}
            	else if(readed.startsWith("memorySize"))
            	{
            		for(int i = 1; i < readed.split(":").length; i++)
            		{
            			mem.size += readed.split(":")[i];
            		}
            	}
            }
		}
		catch(IOException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on loading '" + memoryFile.getName() + "' file!");
			e.printStackTrace();
			mem = null;
		}
		return mem;
	}
	
	private static File getMemoriesFolder()
	{
		if(!memoryFolder.exists())
		{
			memoryFolder.mkdirs();
		}
		return memoryFolder;
	}
}

class Memory
{
    public String name;
    public String size;
    
    public Memory(String name, String size)
    {
    	this.name = name;
    	this.size = size;
    }
}
