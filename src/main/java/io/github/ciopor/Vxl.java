package io.github.ciopor;

import io.github.ciopor.core.Consts;
import io.github.ciopor.core.EngineManager;
import io.github.ciopor.core.WindowManager;

public class Vxl {
    private static WindowManager window;
    private static EngineManager engine;

    static void main() {
        window = new WindowManager(Consts.TITLE, 1600, 900, false);
        engine = new EngineManager();

        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    public static WindowManager getWindow() {
        return window;
    }
}
