package me.jujhar.plutonium.customfeatures;

import java.io.*;
import java.net.*;

import me.jujhar.plutonium.main.Interface;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.GameType;

public class PlutoniumAdditions {
	private static PlutoniumAdditions thisAdditions;
	public boolean reachEnabled = false;
	
	public String downloadString(String urlString) {
		try {
			TextURL url = new TextURL(urlString);
			String contents = url.read();
			return contents;
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	public PlutoniumAdditions() {
		thisAdditions = this;
	}
	
	public static PlutoniumAdditions getAdditions() {
		return thisAdditions;
	}
}