package de.molaynoxx.amperfi.ui.controls;

import ddf.minim.analysis.FFT;
import javafx.scene.canvas.Canvas;

public abstract class AbstractVisualizer extends Canvas {

    public abstract void drawVisualizerFFT(FFT fftData);

}
