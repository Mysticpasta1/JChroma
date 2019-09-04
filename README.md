JChroma - ChromaSDK for Java [WIP]
==================================

JChroma allows to use the Razer Chroma SDK for Razer devices with Java

Requirements
====

In order to use the library, one must:

1. Install [Synapse 3](https://www.razer.com/synapse-3) and install the `Chroma Connect module`

2. Have a Razer device or `Chroma Connect Device` to test it on

3. Open this sample project in Android Studio


Quick example
====

Showing the wave animation from left to right:

```java
chroma = JChroma.getInstance();
chroma.init();
chroma.createKeyboardEffect(new WaveKeyboardEffect(WaveDirection.LEFT_TO_RIGHT));

// ...
// Other awesome code
// ...

chroma.free();
```