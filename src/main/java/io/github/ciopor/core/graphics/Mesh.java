package io.github.ciopor.core.graphics;

import org.lwjgl.opengl.GL46;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Mesh {

    private int numVertices;
    private int vaoId;
    private List<Integer> vboIdList;

    public Mesh(float[] positions, float[] colors, int[] indices) {
        numVertices = indices.length;
        vboIdList = new ArrayList<>();

        vaoId = GL46.glGenVertexArrays();
        GL46.glBindVertexArray(vaoId);

        int vboId;

        // Positions VBO
        vboId = GL46.glGenBuffers();
        vboIdList.add(vboId);
        FloatBuffer positionsBuffer = MemoryUtil.memCallocFloat(positions.length);
        positionsBuffer.put(0, positions);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, vboId);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, positionsBuffer, GL46.GL_STATIC_DRAW);
        GL46.glEnableVertexAttribArray(0);
        GL46.glVertexAttribPointer(0, 3, GL46.GL_FLOAT, false, 0, 0);

        // Colors VBO
        vboId = GL46.glGenBuffers();
        vboIdList.add(vboId);
        FloatBuffer colorsBuffer = MemoryUtil.memCallocFloat(colors.length);
        colorsBuffer.put(0, colors);
        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, vboId);
        GL46.glBufferData(GL46.GL_ARRAY_BUFFER, colorsBuffer, GL46.GL_STATIC_DRAW);
        GL46.glEnableVertexAttribArray(1);
        GL46.glVertexAttribPointer(1, 3, GL46.GL_FLOAT, false, 0, 0);

        // Indices VBO
        vboId = GL46.glGenBuffers();
        vboIdList.add(vboId);
        IntBuffer indicesBuffer = MemoryUtil.memCallocInt(indices.length);
        indicesBuffer.put(0, indices);
        GL46.glBindBuffer(GL46.GL_ELEMENT_ARRAY_BUFFER, vboId);
        GL46.glBufferData(GL46.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL46.GL_STATIC_DRAW);

        GL46.glBindBuffer(GL46.GL_ARRAY_BUFFER, 0);
        GL46.glBindVertexArray(0);

        MemoryUtil.memFree(positionsBuffer);
        MemoryUtil.memFree(colorsBuffer);
        MemoryUtil.memFree(indicesBuffer);
    }

    public void cleanup() {
        vboIdList.forEach(GL46::glDeleteBuffers);
        GL46.glDeleteVertexArrays(vaoId);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public final int getVaoId() {
        return vaoId;
    }
}