package com.razer.chroma.javachromasdk;

import org.jglr.jchroma.effects.CustomKeyboardEffect;

import java.util.ArrayList;

public abstract class AnimationBase {
    public abstract EChromaSDKDeviceTypeEnum getDeviceType();
    public abstract int getFrameCount();
    public abstract float getDuration(int frameId);
    public abstract void showFrame(CustomKeyboardEffect effect, int frameId);
}
