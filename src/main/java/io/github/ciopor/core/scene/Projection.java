package io.github.ciopor.core.scene;

import org.joml.Matrix4f;

public class Projection {
    private static float fov = (float) Math.toRadians(80.0f);
    private static final float Z_FAR = 1000.f;
    private static final float Z_NEAR = 0.01f;

    private Matrix4f projMatrix;

    public Projection(int width, int height) {
        projMatrix = new Matrix4f();
        updateProjMatrix(width, height);
    }

    public Matrix4f getProjMatrix() {
        return projMatrix;
    }

    public void updateProjMatrix(int width, int height) {
        projMatrix.setPerspective(fov, (float) width / height, Z_NEAR, Z_FAR);
    }

    public static void updateFov(float fov) {
        Projection.fov = fov;
    }
}
