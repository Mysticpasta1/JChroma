import com.razer.java.JChromaSDK;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * """"""""Unit tests""""""""
 */
public class TestCChromaEditorLibrary {

    private JChromaSDK chroma;

    @Before
    public void init() {
        chroma = JChromaSDK.getInstance();
        int result = chroma.init();
        if (result != 0) {
            System.err.println("Init unexpected result="+result);
        } else {
            System.err.println("Init Success");
        }
    }

    @After
    public void dispose() {
        chroma = JChromaSDK.getInstance();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        chroma.stopAll();
        chroma.closeAll();

        int result = chroma.uninit();
        if (result != 0) {
            System.err.println("Uninit unexpected result="+result);
        } else {
            System.err.println("Uninit Success");
        }
    }

    private String getAnimationPath() {
        return System.getProperty("user.dir")+"\\src\\main\\resources\\";
    }


    @Test
    public void testPlayAnimation() {
        chroma = JChromaSDK.getInstance();
        chroma.playAnimationName(getAnimationPath()+"animation_rainbow_chroma_link.chroma", true);
        chroma.playAnimationName(getAnimationPath()+"animation_rainbow_headset.chroma", true);
        chroma.playAnimationName(getAnimationPath()+"animation_rainbow_keyboard.chroma", true);
        chroma.playAnimationName(getAnimationPath()+"animation_rainbow_keypad.chroma", true);
        chroma.playAnimationName(getAnimationPath()+"animation_rainbow_mouse.chroma", true);
        chroma.playAnimationName(getAnimationPath()+"animation_rainbow_mousepad.chroma", true);
    }

}
