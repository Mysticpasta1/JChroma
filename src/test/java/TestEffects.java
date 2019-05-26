import com.razer.chroma.javachromasdk.Animation1D;
import com.razer.chroma.javachromasdk.Animation2D;
import com.razer.chroma.javachromasdk.AnimationBase;
import com.razer.chroma.javachromasdk.ChromaAnimationAPI;
import com.razer.chroma.javachromasdk.EChromaSDKDeviceTypeEnum;

import org.jglr.jchroma.JChroma;
import org.jglr.jchroma.effects.*;
import org.jglr.jchroma.utils.ColorRef;
import org.jglr.jchroma.utils.KeyboardKeys;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.razer.chroma.javachromasdk.EChromaSDKDevice1DEnum.DE_ChromaLink;
import static com.razer.chroma.javachromasdk.EChromaSDKDevice1DEnum.DE_Headset;
import static com.razer.chroma.javachromasdk.EChromaSDKDevice1DEnum.DE_Mousepad;
import static com.razer.chroma.javachromasdk.EChromaSDKDevice2DEnum.DE_Keyboard;
import static com.razer.chroma.javachromasdk.EChromaSDKDevice2DEnum.DE_Keypad;
import static com.razer.chroma.javachromasdk.EChromaSDKDevice2DEnum.DE_Mouse;
import static com.razer.chroma.javachromasdk.EChromaSDKDeviceTypeEnum.DE_1D;
import static com.razer.chroma.javachromasdk.EChromaSDKDeviceTypeEnum.DE_2D;
import static junit.framework.TestCase.assertSame;

/**
 * """"""""Unit tests""""""""
 */
public class TestEffects {

    private JChroma chroma;

    @Before
    public void init() {
        chroma = JChroma.getInstance();
        chroma.init();
    }

    @After
    public void dispose() {
        chroma.free();
    }


    @Test
    public void combined() {
        CustomKeyboardEffect heartEffect = new CustomKeyboardEffect();
        ColorRef red = new ColorRef(255, 0, 0);
        heartEffect.setKeyColor(KeyboardKeys.RZKEY_V, red);
        heartEffect.setKeyColor(KeyboardKeys.RZKEY_F, red);
        heartEffect.setKeyColor(KeyboardKeys.RZKEY_G, red);
        heartEffect.setKeyColor(KeyboardKeys.RZKEY_E, red);
        heartEffect.setKeyColor(KeyboardKeys.RZKEY_R, red);
        heartEffect.setKeyColor(KeyboardKeys.RZKEY_T, red);
        heartEffect.setKeyColor(KeyboardKeys.RZKEY_Y, red);
        heartEffect.setKeyColor(KeyboardKeys.RZKEY_4, red);
        heartEffect.setKeyColor(KeyboardKeys.RZKEY_6, red);

        ProgressKeyboardEffect progressEffect = new ProgressKeyboardEffect(KeyboardKeys.RZKEY_F1, KeyboardKeys.RZKEY_F12);
        progressEffect.setMinimumValue(0);
        progressEffect.setMaximumValue(100);
        progressEffect.setCurrentValue(0);
        progressEffect.setInRangeColor(new ColorRef(255, 0, 0));
        progressEffect.setOutsideRangeColor(new ColorRef(0, 0, 128));

        long sleepTime = 10;
        int maxCount = 50;

        CustomKeyboardEffect combined = heartEffect.combine(progressEffect);
        for (int count = 0; count < maxCount; count++) {
            for (int i = 0; i < progressEffect.getMaximumValue()+1; i++) {
                progressEffect.setCurrentValue(i);
                chroma.createKeyboardEffect(combined);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < progressEffect.getMaximumValue()+1; i++) {
                progressEffect.setCurrentValue(progressEffect.getMaximumValue()-i);
                chroma.createKeyboardEffect(combined);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void heart() {
        CustomKeyboardEffect effect = new CustomKeyboardEffect();
        ColorRef red = new ColorRef(255, 0, 0);
        effect.setKeyColor(KeyboardKeys.RZKEY_V, red);
        effect.setKeyColor(KeyboardKeys.RZKEY_F, red);
        effect.setKeyColor(KeyboardKeys.RZKEY_G, red);
        effect.setKeyColor(KeyboardKeys.RZKEY_E, red);
        effect.setKeyColor(KeyboardKeys.RZKEY_R, red);
        effect.setKeyColor(KeyboardKeys.RZKEY_T, red);
        effect.setKeyColor(KeyboardKeys.RZKEY_Y, red);
        effect.setKeyColor(KeyboardKeys.RZKEY_4, red);
        effect.setKeyColor(KeyboardKeys.RZKEY_6, red);


        chroma.createKeyboardEffect(effect);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void progress() {
        int maxCount = 5;
        ProgressKeyboardEffect effect = new ProgressKeyboardEffect(KeyboardKeys.RZKEY_F1, KeyboardKeys.RZKEY_F12);
        effect.setMinimumValue(0);
        effect.setMaximumValue(100);
        effect.setCurrentValue(0);
        effect.setInRangeColor(new ColorRef(255, 0, 0));
        effect.setOutsideRangeColor(new ColorRef(0, 0, 128));
        long sleepTime = 10;
        for (int count = 0; count < maxCount; count++) {
            for (int i = 0; i < effect.getMaximumValue()+1; i++) {
                effect.setCurrentValue(i);
                chroma.createKeyboardEffect(effect);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < effect.getMaximumValue()+1; i++) {
                effect.setCurrentValue(effect.getMaximumValue()-i);
                chroma.createKeyboardEffect(effect);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void custom() {
        int step = 1000;
        for (int index = 0; index < step; index++) {
            ColorRef[][] colors = new ColorRef[6][22];
            for (int i = 0; i < colors.length; i++) {
                for (int j = 0; j < colors[0].length; j++) {
                    colors[i][j] = ColorRef.fromBGR((int)(Math.random()*0xFFFFFF));
                }
            }
            CustomKeyboardEffect effect = new CustomKeyboardEffect(colors);
            chroma.createKeyboardEffect(effect);
            try {
                Thread.sleep(8000L/step);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void breathingColors() {
        chroma.createKeyboardEffect(new BreathingKeyboardEffect(BreathingType.TWO_COLORS, new ColorRef(255,0,0), new ColorRef(255,255,255)));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void breathingRandom() {
        chroma.createKeyboardEffect(new BreathingKeyboardEffect(BreathingType.RANDOM, ColorRef.NULL, ColorRef.NULL));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void reactive() {
        chroma.createKeyboardEffect(new ReactiveKeyboardEffect(EffectDuration.MEDIUM, new ColorRef(0, 255, 0)));
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AnimationBase showAnimation(String animationName) {
        AnimationBase animation = null;
        System.out.println("Testing: " + animationName + " ...");
        String workingDir = System.getProperty("user.dir");
        File temp = new File(workingDir+"\\src\\main\\resources\\", animationName);

        String absolutePath = temp.getAbsolutePath();
        InputStream input = null;
        try {
            input = new FileInputStream(absolutePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try {
            if (null != input) {
                animation = ChromaAnimationAPI.OpenAnimation(input);
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (animation == null) {
            System.err.println("Animation could not be loaded! " + animationName);
            return null;
        }

        int frameCount = animation.getFrameCount();
        for (int frameId = 0; frameId < frameCount; ++frameId) {
            try {
                animation.showFrame(chroma, frameId);
                int duration = (int)Math.floor(1000 * animation.getDuration(frameId));
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return animation;
    }

    @Test
    public void showChromaLink() {
        AnimationBase animation = showAnimation("animation_rainbow_chroma_link.chroma");
        assertSame(animation.getDeviceType(), DE_1D);
        assertSame(((Animation1D)animation).getDevice(), DE_ChromaLink);
    }

    @Test
    public void showHeadset() {
        AnimationBase animation = showAnimation("animation_rainbow_headset.chroma");
        assertSame(animation.getDeviceType(), DE_1D);
        assertSame(((Animation1D)animation).getDevice(), DE_Headset);
    }

    @Test
    public void showKeyboard() {
        AnimationBase animation = showAnimation("animation_rainbow_keyboard.chroma");
        assertSame(animation.getDeviceType(), DE_2D);
        assertSame(((Animation2D)animation).getDevice(), DE_Keyboard);
    }

    @Test
    public void showKeypad() {
        AnimationBase animation = showAnimation("animation_rainbow_keypad.chroma");
        assertSame(animation.getDeviceType(), DE_2D);
        assertSame(((Animation2D)animation).getDevice(), DE_Keypad);
    }

    @Test
    public void showMouse() {
        AnimationBase animation = showAnimation("animation_rainbow_mouse.chroma");
        assertSame(animation.getDeviceType(), DE_2D);
        assertSame(((Animation2D)animation).getDevice(), DE_Mouse);
    }

    @Test
    public void showMousepad() {
        AnimationBase animation = showAnimation("animation_rainbow_mousepad.chroma");
        assertSame(animation.getDeviceType(), DE_1D);
        assertSame(((Animation1D)animation).getDevice(), DE_Mousepad);
    }

    @Test
    public void spectrum() {
        chroma.createKeyboardEffect(new SpectrumCyclingKeyboardEffect());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void staticEffect() {
        chroma.createKeyboardEffect(new StaticKeyboardEffect(new ColorRef(255, 128, 0)));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWave() {
        chroma.createKeyboardEffect(new WaveKeyboardEffect(WaveDirection.LEFT_TO_RIGHT));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
