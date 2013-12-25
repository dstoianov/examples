package autoitx4java;

import java.io.File;

import autoitx4java.AutoItX;
import com.jacob.com.ComThread;
import com.jacob.com.LibraryLoader;

public class AutoItHelper {

    static {
        File jacob = new File("lib", "jacob-1.17-x64.dll");
        System.setProperty(LibraryLoader.JACOB_DLL_PATH, jacob.getAbsolutePath());
    }

    private String winTitle;
    private String winText;
    private final AutoItX aux;

    public AutoItHelper(String winTitle) {
        this.winTitle = winTitle;
        aux = new AutoItX();
    }

    public AutoItHelper winWaitAndActivate(String winTitle, String winText, int timeout) {
        this.winTitle = winTitle;
        this.winText = winText;
        aux.winWait(winTitle, winText, timeout);
        aux.winActivate(winTitle, winText);
        aux.winWaitActive(winTitle, winText, timeout);
        System.out.println("Window activated: " + winTitle);
        System.out.println("Title of modal window is: " + getTitle());
        return this;
    }
    
    public AutoItHelper setText(String controlID, String text) {
        aux.ControlSetText(winTitle, winText, controlID, text);
        //aux.send("{ENTER}", false); // if you need press enter after typed text
        return this;
    }

    public AutoItHelper send(String text) {
        aux.send(text);
        return this;
    }
    
    public AutoItHelper send(String text, boolean isRaw) {
        aux.send(text, isRaw);
        return this;
    }
    
    public AutoItHelper click(String controlID) {
        aux.controlClick(winTitle, winText, controlID);
        System.out.println("Control clicked: " + controlID);
        return this;
    }

    public void processClose(String process, int timeout) {
        aux.processClose(process);
        aux.processWaitClose(process, timeout);
    }

	public AutoItHelper focus(String controlID) {
		aux.controlFocus(winTitle, winText, controlID);
		return this;
	}

	public String getText(String controlID) {
		return aux.controlGetText(winTitle, winText, controlID);		
	}

    public String getTitle() {
        //return aux.winGetTitle("[active]");
        return aux.winGetTitle(winTitle, winText);
    }

    
}
