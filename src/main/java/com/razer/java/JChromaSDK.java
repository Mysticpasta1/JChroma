package com.razer.java;

import com.sun.jna.Native;
import org.jglr.jchroma.JChromaException;

/**
 * Entry point of the API, allows to create effects for the device and query Razer devices
 */
public class JChromaSDK {

    private static JChromaSDK instance;
    private final JChromaLib wrapper;

    private JChromaSDK() {
        String libName = "CChromaEditorLibrary";
        if(System.getProperty("os.arch").contains("64")) {
            libName += "64";
        }
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\";
        String fullPath = path + "\\" + libName;
        //System.out.println("fullPath: "+fullPath);
        wrapper = (JChromaLib) Native.loadLibrary(fullPath, JChromaLib.class);
    }

    /**
     * Returns the <code>JChroma</code> singleton. One must be warned that this method performs
     * lazy initialisation and that loading the native files is done at initialisation
     * @return
     *          The JChroma singleton
     */
    public static JChromaSDK getInstance() {
        if(instance == null)
            instance = new JChromaSDK();
        return instance;
    }

    private void throwIfError(int err, String method) {
        if(err != 0)
            throw new JChromaException(method, err);
    }

    /**
     * Initialises the ChromaSDK.
     * @throws JChromaException
     *          If there is any error while initialisation
     */
    public int init() {
        int result = wrapper.PluginInit();
        throwIfError(result, "pluginInit()");
        return result;
    }

    /**
     * Initialises the ChromaSDK.
     * @throws JChromaException
     *          If there is any error while initialisation
     */
    public int uninit() {
        int result = wrapper.PluginUninit();
        throwIfError(result, "pluginUninit()");
        return result;
    }

    public void playAnimation(String name, boolean loop) {
        wrapper.PluginPlayAnimationName(name, loop);
    }

    public void stopAll() {
        wrapper.PluginStopAll();
    }

    public void closeAll() {
        wrapper.PluginCloseAll();
    }
}
