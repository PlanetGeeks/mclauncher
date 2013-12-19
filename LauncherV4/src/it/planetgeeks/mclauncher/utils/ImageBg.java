package it.planetgeeks.mclauncher.utils;

import it.planetgeeks.mclauncher.Launcher;

import java.awt.image.BufferedImage;

public class ImageBg
{
    public EnumBgPos enumPos;
    public BufferedImage img;
    public boolean resizable;
    public String desc;
    
    public ImageBg(String img, EnumBgPos enumPos, boolean resizable)
    {
    	this.img = Launcher.getResources().getResourceBuffered(img);
    	this.enumPos = enumPos;
    	this.resizable = resizable;
    }
    
    public void setDesc(String desc)
    {
        this.desc = desc;    	
    }
}


