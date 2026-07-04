package io.github.ciopor.core.graphics;

import io.github.ciopor.core.ResourceLoader;

import org.lwjgl.opengl.GL46;
//import static org.lwjgl.opengl.GL46.glCreateProgram;
//import static org.lwjgl.opengl.GL46.glCreateShader;
//import static org.lwjgl.opengl.GL46.glShaderSource;
//import static org.lwjgl.opengl.GL46.glCompileShader;
//import static org.lwjgl.opengl.GL46.glGetShaderi;
//import static org.lwjgl.opengl.GL46.glGetShaderInfoLog;
//import static org.lwjgl.opengl.GL46.glAttachShader;
//import static org.lwjgl.opengl.GL46.glLinkProgram;
import static org.lwjgl.opengl.GL46.*;

import java.io.IOException;

public class ShaderProgram {
    private final int programId;
    private int vertexShaderId;
    private int fragmentShaderId;
    
    public ShaderProgram() throws Exception {
        programId = glCreateProgram();
    }

    public void createVertexShader(String path) throws Exception, IOException {
        String vertexSource;
        vertexSource = ResourceLoader.loadText(path);
        vertexShaderId = createShader(vertexSource, GL46.GL_VERTEX_SHADER);
    }
    
    public void createFragmentShader(String path) throws Exception, IOException {
        String fragmentSource;
        fragmentSource = ResourceLoader.loadText(path);
        vertexShaderId = createShader(fragmentSource, GL46.GL_FRAGMENT_SHADER);
    }
    
    protected int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderId = glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new Exception("Error creating shader");
        }
        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);
        if (glGetShaderi(shaderId, GL46.GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }
        glAttachShader(programId, shaderId);
        
        return shaderId;
    }
    
    public void link() throws Exception {
        glLinkProgram(programId);
        if (glGetProgrami(programId , GL46.GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(programId, 1024));
        }
        
         if (vertexShaderId != 0) {
            glDetachShader(programId, vertexShaderId);
        }
        if (fragmentShaderId != 0) {
            glDetachShader(programId, fragmentShaderId);
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
        }
    }
    
    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if (programId != 0) {
            glDeleteProgram(programId);
        }
    }
}