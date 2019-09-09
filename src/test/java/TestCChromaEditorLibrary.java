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
        int result = chroma.pluginInit();
        if (result != 0) {
            System.err.println("Init unexpected result="+result);
        } else {
            System.err.println("Init Success");
        }
    }

    @After
    public void dispose() {
        chroma = JChromaSDK.getInstance();
        int result = chroma.pluginUninit();
        if (result != 0) {
            System.err.println("Uninit unexpected result="+result);
        } else {
            System.err.println("Uninit Success");
        }
    }


    @Test
    public void testPlayAnimation() {
    }

}
