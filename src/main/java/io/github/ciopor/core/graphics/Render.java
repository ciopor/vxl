package io.github.ciopor.core.graphics;

import io.github.ciopor.core.Scene;
import org.lwjgl.opengl.GL46;

import org.lwjgl.opengl.GL;

public class Render {
    private SceneRender sceneRender;

    public Render() {
        GL.createCapabilities();
        sceneRender = new SceneRender();
    }

    public void cleanup() {
        sceneRender.cleanup();
    }

    public void render(Window window, Scene scene) {
        GL46.glViewport(0, 0, window.getWidth(), window.getHeight());
        sceneRender.render(scene);
    }
}
