package io.github.ciopor.core.scene;

import io.github.ciopor.core.graphics.ShaderProgram;
import io.github.ciopor.core.graphics.UniformsMap;
import org.lwjgl.opengl.GL46;

import java.util.ArrayList;
import java.util.List;

public class SceneRender {

    private ShaderProgram shaderProgram;
    private UniformsMap uniformsMap;

    public SceneRender() {
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("/shaders/scene.vert", GL46.GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("/shaders/scene.frag", GL46.GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        uniformsMap = new UniformsMap(shaderProgram.getProgramId());
        createUniforms();
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(Scene scene) {
        shaderProgram.bind();
        uniformsMap.setUniform("projectionMatrix", scene.getProjection().getProjMatrix());

        scene.getMeshMap().values().forEach(mesh -> {
                    GL46.glBindVertexArray(mesh.getVaoId());
                    GL46.glDrawElements(GL46.GL_TRIANGLES, mesh.getNumVertices(), GL46.GL_UNSIGNED_INT, 0);
                }
        );

        GL46.glBindVertexArray(0);
        shaderProgram.unbind();
    }

    private void createUniforms() {
        uniformsMap.createUniform("projectionMatrix");
    }
}