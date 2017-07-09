package me.jujhar.plutonium.main;

import java.awt.Color;

import org.fife.ui.rsyntaxtextarea.themes.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.Toolkit;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.minecraft.world.GameType;

import org.apache.commons.lang3.StringUtils;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.lwjgl.input.Keyboard;

import me.jujhar.plutonium.customfeatures.PlutoniumAdditions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.server.MinecraftServer;

public class Interface {
    private JFrame execF = new JFrame("Plutonium ALPHA");
    private JPanel execP = new JPanel();
    private JButton execButton = new JButton("EXECUTE");
    private JButton openButton = new JButton("OPEN");
    private JButton clearButton = new JButton("CLEAR");
    private JButton creditsButton = new JButton("CREDITS");
    private RSyntaxTextArea execText = new RSyntaxTextArea("// Welcome to the alpha release of Plutonium\r\n// Hope you enjoy!", 0, 0);
    private JScrollPane sp = new JScrollPane(execText);
    private JTextArea execConsole = new JTextArea("Info: Plutonium UI Loaded!\n", 0, 0);
    private JScrollPane sConsole = new JScrollPane(execConsole);
    private JLabel name = new JLabel("ÐΦGGΩ#0018");
    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine engine = manager.getEngineByName("JavaScript");
    private Bindings b = engine.createBindings();
    private static Interface theInterface;
    private int ColorRed;
    private int ColorGreen;
    private int ColorBlue;
    
    private void applyTheme() {
    	try {
			Theme theme = Theme.load(getClass().getResourceAsStream(
			        "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
			theme.apply(execText);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public Interface(int colorRed, int colorGreen, int colorBlue) throws ScriptException
    {
    	theInterface = this;
    	
    	ColorRed = colorRed;
    	ColorGreen = colorGreen;
    	ColorBlue = colorBlue;
        execF.setVisible(true);
        execF.setSize(450, 555);
        execF.setAlwaysOnTop(true);
        execF.setResizable(false);
        execF.setDefaultCloseOperation(execF.EXIT_ON_CLOSE);
        
        execP.setBackground(new Color(ColorRed, ColorGreen, ColorBlue));
        execText.setCodeFoldingEnabled(true);
        sp.setPreferredSize(new Dimension(425, 330));
        execText.setBackground(new Color(8, 8, 9)); //(new Color(28, 185, 28));
        execText.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        sConsole.setPreferredSize(new Dimension(425, 115));
        execConsole.setBackground(new Color(206, 201, 198));
        execText.setEnabled(false);
        if(engine != null) { execConsole.append("Info: Nashorn (JS) Engine Loaded!\r\n"); } else { execConsole.append("Error: Nashorn (JS) Engine Failed to Load! (engine is null)\r\n"); }
        
        execButton.setPreferredSize(new Dimension(102, 40));
        execButton.setContentAreaFilled(false);
        execButton.setForeground(new Color(247, 247, 246));
        execButton.setBorder(BorderFactory.createLineBorder(new Color(247, 247, 246), 2));
        
        openButton.setPreferredSize(new Dimension(102, 40));
        openButton.setContentAreaFilled(false);
        openButton.setForeground(new Color(247, 247, 246));
        openButton.setBorder(BorderFactory.createLineBorder(new Color(247, 247, 246), 2));
        
        clearButton.setPreferredSize(new Dimension(102, 40));
        clearButton.setContentAreaFilled(false);
        clearButton.setForeground(new Color(247, 247, 246));
        clearButton.setBorder(BorderFactory.createLineBorder(new Color(247, 247, 246), 2));
        
        creditsButton.setPreferredSize(new Dimension(102, 40));
        creditsButton.setContentAreaFilled(false);
        creditsButton.setForeground(new Color(247, 247, 246));
        creditsButton.setBorder(BorderFactory.createLineBorder(new Color(247, 247, 246), 2));
        
        sp.setBounds(23, 40, 425, 115);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        sConsole.setBounds(23, 40, 425, 330);
        sConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sConsole.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        name.setForeground(new Color(247, 247, 246));
        
        execP.add(sp);
        execP.add(execButton);
        execP.add(openButton);
        execP.add(clearButton);
        execP.add(creditsButton);
        execF.add(execP);
        execP.add(sConsole);
        execP.add(name);
        try {
        	b.put("Minecraft", Minecraft.getMinecraft());
        	execConsole.append("Info: Minecraft API Loaded!\r\n");
        } catch(NullPointerException e) {
        	execConsole.append("Error: Minecraft API Failed to Load! (NullPointerException)\r\n");
        } catch(IllegalArgumentException e) {
        	execConsole.append("Error: Minecraft API Failed to Load! (IllegalArgumentException)\r\n");
        }
        
        try {
        	b.put("Plutonium", new PlutoniumAdditions());
        	b.put("print", null);
        	b.put("Interface", theInterface);
        	execConsole.append("Info: Custom Features Loaded!\r\n");
        } catch(NullPointerException e) {
        	execConsole.append("Error: Custom Features Failed to Load! (NullPointerException)\r\n");
        } catch(IllegalArgumentException e) {
        	execConsole.append("Error: Custom Features Failed to Load! (IllegalArgumentException)\r\n");
        }
        
        engine.setBindings(b, ScriptContext.ENGINE_SCOPE);
        new JFXPanel();
        execButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                	engine.eval("var Platform = Java.type(\"javafx.application.Platform\");\r\nvar Timer    = Java.type(\"java.util.Timer\");\r\nfunction setTimerRequest(handler, delay, interval, args) {\r\n    handler = handler || function() {};\r\n    delay = delay || 0;\r\n    interval = interval || 0;\r\n    var applyHandler = function() handler.apply(this, args);\r\n    var runLater = function() Platform.runLater(applyHandler);\r\n    var timer = new Timer(\"setTimerRequest\", true);\r\n    if (interval > 0) {\r\n        timer.schedule(runLater, delay, interval);\r\n    } else {\r\n        timer.schedule(runLater, delay);\r\n    }\r\n    return timer;\r\n}\r\nfunction clearTimerRequest(timer) {\r\n    timer.cancel();\r\n}\r\nfunction setInterval() {\r\n    var args = Array.prototype.slice.call(arguments);\r\n    var handler = args.shift();\r\n    var ms = args.shift();\r\n    return setTimerRequest(handler, ms, ms, args);\r\n}\r\nfunction clearInterval(timer) {\r\n    clearTimerRequest(timer);\r\n}\r\nfunction setTimeout() {\r\n    var args = Array.prototype.slice.call(arguments);\r\n    var handler = args.shift();\r\n    var ms = args.shift();\r\n\r\n    return setTimerRequest(handler, ms, 0, args);\r\n}\r\nfunction clearTimeout(timer) {\r\n    clearTimerRequest(timer);\r\n}\r\nfunction setImmediate() {\r\n    var args = Array.prototype.slice.call(arguments);\r\n    var handler = args.shift();\r\n\r\n    return setTimerRequest(handler, 0, 0, args);\r\n}\r\nfunction clearImmediate(timer) {\r\n    clearTimerRequest(timer);\r\n}\r\n"+execText.getText());
                }
                catch (ScriptException e)
                {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(execF, e.getStackTrace(), "Script Error!", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(execF, e.getStackTrace(), "Script Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        applyTheme();
    }
    
    // Get the singular interface
    public static Interface getInterface()
    {
		return theInterface;
    }
    
    public void setAllowed(boolean allowedBool)
    {
    	if (allowedBool == true) {
    		execConsole.append("Info: World Joined!\r\n");
    		execText.setEnabled(true);
    		JScrollBar vertical = sConsole.getVerticalScrollBar();
        	vertical.setValue( vertical.getMaximum() );
    	} else {
    		execConsole.append("Info: World Left!\r\n");
    		execText.setEnabled(false);
    		JScrollBar vertical = sConsole.getVerticalScrollBar();
        	vertical.setValue( vertical.getMaximum() );
    	}
    }
    
    public Boolean log(String text)
    {
    	execConsole.append("Info: "+text+"\r\n");
    	JScrollBar vertical = sConsole.getVerticalScrollBar();
    	vertical.setValue( vertical.getMaximum() );
    	return true;
    }
    
    public Boolean clear()
    {
    	execConsole.setText("");
    	return true;
    }
    
    public Boolean setBackColor(int colorRed, int colorGreen, int colorBlue)
    {
		try {
			execP.setBackground(new Color(colorRed, colorGreen, colorBlue));
			ColorRed = colorRed;
	    	ColorGreen = colorGreen;
	    	ColorBlue = colorBlue;
	    	return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
    	
    }
}
