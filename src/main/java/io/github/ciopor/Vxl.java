package io.github.ciopor;

public class Vxl {
    static void main() {
        WindowManager wm = new WindowManager("VXL", 1280, 720, false);
        wm.init();
        while (!wm.windowsSouldClose()) {
            wm.update();
        }
        wm.cleanup();
    }
}
