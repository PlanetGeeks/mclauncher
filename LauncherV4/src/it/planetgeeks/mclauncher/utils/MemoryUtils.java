package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.LauncherLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author PlanetGeeks
 *
 */

public class MemoryUtils
{
	private static ArrayList<Memory> memories = new ArrayList<Memory>();
	private static File memoryFolder = new File(DirUtils.getLauncherDirectory() + File.separator + "memories");

	public static ArrayList<Memory> loadMemories()
	{
		memories = new ArrayList<Memory>();

		ArrayList<String> list = getFileNames();

		if (list == null || list.size() == 0)
		{
			createCustom("768 MB", "768");
			createCustom("1 GB", "1024");
			createCustom("1.5 GB", "1536");
			createCustom("2 GB", "2048");
			createCustom("3 GB", "3072");
			createCustom("4 GB", "4096");
			LauncherLogger.log(LauncherLogger.INFO, "Created default memories!");
		}
		else
		{
			int i;

			for (i = 0; i < list.size(); i++)
			{
				if (list.get(i).endsWith(".memory"))
				{
					File cur = new File(getMemoriesFolder() + File.separator + list.get(i));
					if (cur.exists())
					{
						Memory memory = loadMemoryFile(cur);

						if (memory != null)
						{
							memories.add(memory);
						}
					}
				}
			}
			if (i > 0)
			{
				LauncherLogger.log(LauncherLogger.INFO, "Loaded " + i + " memory files!");
			}
			else
			{
				LauncherLogger.log(LauncherLogger.INFO, "No memory file loaded!");
			}
		}

		return memories;
    }
	
	public static boolean deleteCustom(String name)
	{
		for(int i = 0; i < memories.size(); i++)
		{
			if(memories.get(i).name.equals(name))
			{
				memories.remove(i);
				File file = new File(getMemoriesFolder() + File.separator + name + ".memory");
				if(file.exists())
				{
					file.delete();
					writeListNames();
					return true;
				}
			}
		}
		return false;
	}

	public static boolean createCustom(String name, String size)
	{
		for (Memory current : memories)
		{
			if (current.name.equals(name))
			{
				return false;
			}
		}
		memories.add(new Memory(name, Integer.valueOf(size)));
		writeMemoryFile(name, size);
		writeListNames();
		return true;
	}

	public static boolean modifyCustom(String name, String size)
	{
		for (Memory current : memories)
		{
			if (current.name.equals(name))
			{
				current.size = Integer.valueOf(size);
				writeMemoryFile(name, size);
				writeListNames();
				return true;
			}
		}

		return false;
	}

	public static int moveCustom(boolean up, int pos)
	{
		ArrayList<Memory> newMemories = new ArrayList<Memory>();
		
		int newPos = up ? pos - 1 : pos + 1;
		
		if(newPos < 0)
		{
			newPos = memories.size() - 1;
		}
		
		if(newPos == memories.size())
		{
			newPos = 0;
		}
		
		Memory memory1 = memories.get(pos);
		Memory memory2 = memories.get(newPos);
		
		for(int i = 0; i < memories.size(); i++)
		{
			if(i == newPos)
			{
				newMemories.add(memory1);
			}
			else if(i == pos)
			{
				newMemories.add(memory2);
			}
			else
			{
				newMemories.add(memories.get(i));
			}
		}
		
		memories = newMemories;
		writeListNames();
		return newPos;
	}
	
	public static Memory getMem(String name)
	{
		for (Memory mem : memories)
		{
			if (mem.name.equals(name))
			{
				return mem;
			}
		}
		return null;
	}

	public static String[] getMemoryNames()
	{
		String[] list = new String[memories.size()];
		for (int i = 0; i < list.length; i++)
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

	private static Memory loadMemoryFile(File memoryFile)
	{
		BufferedReader br;
		Memory mem = new Memory("", 512);
		try
		{
			br = new BufferedReader(new FileReader(memoryFile));
			String readed = br.readLine();
			while (readed != null)
			{
				if (readed.startsWith("memoryName"))
				{
					for (int i = 1; i < readed.split(":").length; i++)
					{
						mem.name += readed.split(":")[i];
					}
				}
				else if (readed.startsWith("memorySize"))
				{
					String memorySize = "";
					for (int i = 1; i < readed.split(":").length; i++)
					{
						memorySize += readed.split(":")[i];
					}
					try
					{
						mem.size = Integer.valueOf(memorySize);
					}
					catch (NumberFormatException e)
					{
					}

				}
				readed = br.readLine();
			}
			br.close();
		}
		catch (IOException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on loading '" + memoryFile.getName() + "' file!");
			e.printStackTrace();
			mem = null;
		}
		return mem;
	}

	private static ArrayList<String> getFileNames()
	{
		try
		{
			File file = new File(getMemoriesFolder() + File.separator + "memories.list");
			if (!file.exists())
			{
				return null;
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String readed = br.readLine();
			ArrayList<String> returned = new ArrayList<String>();
			while (readed != null)
			{
				returned.add(readed);
				readed = br.readLine();
			}
			br.close();
			return returned;
		}
		catch (IOException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on reading memories list!");
			e.printStackTrace();
		}
		return null;
	}

	private static void writeListNames()
	{
		try
		{
			File file = new File(getMemoriesFolder() + File.separator + "memories.list");
			if (file.exists())
			{
				file.delete();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for (int i = 0; i < memories.size(); i++)
			{
				if (i > 0)
				{
					bw.newLine();
				}
				bw.write(memories.get(i).name + ".memory");
			}
			bw.close();
		}
		catch (IOException e)
		{
			LauncherLogger.log(LauncherLogger.GRAVE, "Error on writing memories list!");
			e.printStackTrace();
		}
	}

	private static File getMemoriesFolder()
	{
		if (!memoryFolder.exists())
		{
			memoryFolder.mkdirs();
		}
		return memoryFolder;
	}
}