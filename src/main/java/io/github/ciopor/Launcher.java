package io.github.ciopor;

import io.github.ciopor.core.Consts;
import io.github.ciopor.core.Engine;
import io.github.ciopor.core.IGameLogic;
import io.github.ciopor.core.Scene;
import io.github.ciopor.core.graphics.Mesh;
import io.github.ciopor.core.graphics.Render;
import io.github.ciopor.core.graphics.Window;

public class Launcher implements IGameLogic {
        public static void main(String[] args) {
            Launcher launcher = new Launcher();
            Engine game = new Engine(Consts.TITLE, new Window.WindowOptions(), launcher);
            game.start();
        }

        @Override
        public void cleanup() {

        }

        @Override
        public void init(Window window, Scene scene, Render render) {
                float[] positions = new float[]{
                        0.0f, 0.5f, 0.0f,
                        -0.5f, -0.5f, 0.0f,
                        0.5f, -0.5f, 0.0f
                };
                Mesh mesh = new Mesh(positions, 3);
                scene.addMesh("triangle", mesh);
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
