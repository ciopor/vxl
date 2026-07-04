package io.github.ciopor.core.graphics;

import io.github.ciopor.core.Scene;
import org.lwjgl.opengl.GL46;

import java.util.ArrayList;
import java.util.List;

public class SceneRender {

    private ShaderProgram shaderProgram;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("/shaders/scene.vert", GL46.GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("/shaders/scene.frag", GL46.GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(Scene scene) {
        shaderProgram.bind();

        scene.getMeshMap().values().forEach(mesh -> {
                    GL46.glBindVertexArray(mesh.getVaoId());
                    GL46.glDrawArrays(GL46.GL_TRIANGLES, 0, mesh.getNumVertices());
                }
        );

        GL46.glBindVertexArray(0);
        shaderProgram.unbind();
    }
}
