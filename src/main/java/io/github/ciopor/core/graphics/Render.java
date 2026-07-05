package io.github.ciopor.core.graphics;

import io.github.ciopor.core.scene.Scene;
import io.github.ciopor.core.scene.SceneRender;
import org.lwjgl.opengl.GL46;

import org.lwjgl.opengl.GL;

public class Render {
    private SceneRender sceneRender;

    public Render() {
        GL.createCapabilities();

        //GL46.glEnable(GL46.GL_DEPTH_TEST);
        GL46.glEnable(GL46.GL_CULL_FACE);
        GL46.glCullFace(GL46.GL_BACK);
        GL46.glFrontFace(GL46.GL_CCW);

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
