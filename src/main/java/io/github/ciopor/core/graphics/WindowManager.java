package io.github.ciopor.core.graphics;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL46;
import org.lwjgl.system.MemoryUtil;

public class WindowManager {
    private String title;
    private int windowWidth, windowHeight;
    private long window;
    private boolean resize, vSync;

    public WindowManager(String title, int windowWidth, int windowHeight, boolean vSync) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.title = title;
        this.vSync = vSync;
    }

    public void init() {

        // Init windowing
        GLFWErrorCallback.createPrint(System.err);
        if(!GLFW.glfwInit()) {
            throw new IllegalStateException("GLFW could not be initialized.");
        }

        // Take care of OpenGL preferences
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL46.GL_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL46.GL_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 6);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);

        // Set window to be maximized if size is 0x0
        boolean maximized = false;
        if (windowWidth == 0 && windowHeight == 0) {
            windowWidth = 1280;
            windowHeight = 720;
            GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
            maximized = true;
        }

        // Create a window
        window = GLFW.glfwCreateWindow(windowWidth, windowHeight, title, MemoryUtil.NULL, MemoryUtil.NULL);

        // Check for success
        if (window == MemoryUtil.NULL) throw new RuntimeException("Could not create a window.");

        // Set a reaction to window size changing
        GLFW.glfwSetFramebufferSizeCallback(window,(window, windowHeight, windowWidth) -> {
            this.windowWidth = windowWidth;
            this.windowHeight = windowHeight;
            this.setResize(true);
        });

        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) GLFW.glfwSetWindowShouldClose(window, true);
        });

        // Take care of window position
        if (maximized) {
            GLFW.glfwMaximizeWindow(window);
        } else {
            GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            GLFW.glfwSetWindowPos(window, (vidMode.width() - windowWidth) / 2, (vidMode.height() - windowHeight) / 2);
        }

        GLFW.glfwMakeContextCurrent(window);

        // Set vSync on if the user wants to
        if (getVSync()) GLFW.glfwSwapInterval(1);

        GLFW.glfwShowWindow(window);

        GL.createCapabilities();
        GL46.glClearColor(0.0f,0.0f,0.0f,1.0f);
        GL46.glEnable(GL46.GL_DEPTH_TEST);
        GL46.glEnable(GL46.GL_STENCIL_TEST);
        GL46.glEnable(GL46.GL_CULL_FACE);
        GL46.glCullFace(GL46.GL_BACK);
    }

    public boolean getResize() {
        return resize;
    }
    public void setResize(boolean resize) {
        this.resize = resize;
    }

    public boolean getVSync() {
        return vSync;
    }
    public void setVSync(boolean vSync) {
        this.vSync = vSync;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        GLFW.glfwSetWindowTitle(window, title);
        this.title = title;
    }

    public void update() {
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
    }

    public void cleanup() {
        GLFW.glfwDestroyWindow(window);
    }

    public boolean isKeyPressed(int keycode) {
        return GLFW.glfwGetKey(window, keycode) == GLFW.GLFW_PRESS;
    }

    public boolean windowSouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public long getWindow() {
        return window;
    }
}
