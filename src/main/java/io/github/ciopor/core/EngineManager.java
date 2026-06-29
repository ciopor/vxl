package io.github.ciopor.core;

import io.github.ciopor.Vxl;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class EngineManager {
    public static double dt = 0.0f;
    private static int fps;
    private boolean isRunning;

    private WindowManager window;
    private GLFWErrorCallback errorCallback;

    private void init() throws Exception {
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        window = Vxl.getWindow();
        window.init();
    }

    public void start() throws Exception {
        init();
        if (isRunning) {
            return;
        }
        isRunning = true;
        run();
    }
    private void run() {
        double lastFrameTime = 0.0;

        while (isRunning) {
            double time = Time.getTime();
            dt = time - lastFrameTime;
            lastFrameTime = time;

            if(window.windowsSouldClose()) stop();
            fps = (int) (1 / dt);

            input();
            update();
            render();

            //System.out.println(getFps());
        }
        cleanup();
    }
    private void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
    }

    private void input() {

    }

    private void render() {
        window.update();
    }

    private void update() {

    }

    private void cleanup() {
        window.cleanup();
        errorCallback.free();
        GLFW.glfwTerminate();
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        EngineManager.fps = fps;
    }
}
