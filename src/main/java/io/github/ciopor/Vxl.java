package io.github.ciopor;

import io.github.ciopor.core.Consts;
import io.github.ciopor.core.graphics.WindowManager;
import io.github.ciopor.core.graphics.ShaderProgram;

public class Vxl {
    private static WindowManager window;
    private static ShaderProgram shaderProgram;
    
    static void main() {  
        window = new WindowManager(Consts.TITLE, 1600, 900, false);
        window.init();
        if (window == null) return;
        
        float[] vertices = {
            0.0f, 0.5f, 0.0f,
            0.5f, -0.5f, 0.0f, 
            -0.5f, -0.5f, 0.0f,
        };

        try {
            shaderProgram = new ShaderProgram();
            shaderProgram.createFragmentShader("/shaders/shader.frag");
            shaderProgram.createVertexShader("/shaders/shader.vert");
            shaderProgram.link();
        } catch (Exception e) {
            System.err.println(e);
        }
        
        while (!window.windowSouldClose()) {
            shaderProgram.bind();
            window.update();
            shaderProgram.unbind();
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
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
