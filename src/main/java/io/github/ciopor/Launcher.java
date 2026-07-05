package io.github.ciopor;

import io.github.ciopor.core.Consts;
import io.github.ciopor.core.Engine;
import io.github.ciopor.core.IGameLogic;
import io.github.ciopor.core.scene.Scene;
import io.github.ciopor.core.graphics.Mesh;
import io.github.ciopor.core.graphics.Render;
import io.github.ciopor.core.graphics.Window;
import org.lwjgl.opengl.GL46;

public class Launcher implements IGameLogic {
        public static void main(String[] args) {
            Launcher launcher = new Launcher();
            Window.WindowOptions windowOptions = new Window.WindowOptions();
            windowOptions.compatibleProfile = false;
            windowOptions.width = 1280;
            windowOptions.height = 720;
            Engine game = new Engine(Consts.TITLE, windowOptions, launcher);
            game.start();
        }

        @Override
        public void cleanup() {

        }

        @Override
        public void init(Window window, Scene scene, Render render) {
            float[] positions = new float[]{
                        -0.5f, 0.5f, -1.0f,
                        -0.5f, -0.5f, -1.0f,
                        0.5f, -0.5f, -1.0f,
                        0.5f, 0.5f, -1.0f,
                };
                float[] colors = new float[]{
                        0.5f, 0.0f, 0.0f,
                        0.0f, 0.5f, 0.0f,
                        0.0f, 0.0f, 0.5f,
                        1.0f, 1.0f, 1.0f,
                };
                int[] indices = new int[]{
                        0, 1, 3, 3, 1, 2,
                };
                Mesh mesh = new Mesh(positions, colors, indices);
                scene.addMesh("quad", mesh);
        }

        @Override
        public void input(Window window, Scene scene, long diffTimeMillis) {
            // Nothing to be done yet
        }

        @Override
        public void update(Window window, Scene scene, long diffTimeMillis) {
            // Nothing to be done yet
        }
}
