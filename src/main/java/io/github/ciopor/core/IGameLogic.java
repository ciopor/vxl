package io.github.ciopor.core;

import io.github.ciopor.core.graphics.Render;
import io.github.ciopor.core.graphics.Window;

public interface IGameLogic {

    void init(Window window, Scene scene, Render render);

    void input(Window window, Scene scene, long diffTimeMills);

    void update(Window window, Scene scene, long diffTimeMillis);

    void cleanup();
}
