package io.github.ciopor;

import org.lwjgl.glfw.GLFW;
import io.github.ciopor.core.Consts;
import io.github.ciopor.core.graphics.WindowManager;

public class Vxl {
    private static WindowManager window;

    static void main() {
        window = new WindowManager(Consts.TITLE, 1600, 900, false);
        window.init();
        if (window == null) return;
        while (!window.windowSouldClose()) {
            window.update();
        }
        cleanup();
    }

    public static WindowManager getWindow() {
        return window;
    }
    private static void cleanup() {
        if (window != null) {
            window.cleanup();
        }
    }
}
