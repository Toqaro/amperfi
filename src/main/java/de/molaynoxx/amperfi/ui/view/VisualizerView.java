package de.molaynoxx.amperfi.ui.view;


import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.player.PlayerAPI;
import de.molaynoxx.amperfi.ui.controls.AbstractVisualizer;
import de.molaynoxx.amperfi.ui.controls.visualizer.TextVisualizer;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class VisualizerView extends StackPane implements Viewable {

    public AbstractVisualizer activeVisualizer;

    public VisualizerView(Pane parent) {
        this.getStyleClass().add("visualizer-view");
        this.prefWidthProperty().bind(parent.prefWidthProperty());
        this.prefHeightProperty().bind(parent.prefHeightProperty());

        activeVisualizer = new TextVisualizer();

        activeVisualizer.widthProperty().bind(prefWidthProperty());
        activeVisualizer.heightProperty().bind(prefHeightProperty());

        getChildren().add(activeVisualizer);

        Thread thVisExecutor = new Thread(new VisualizerExecutor());
        thVisExecutor.setDaemon(true);
        thVisExecutor.setName("Visualizer Executor");
        thVisExecutor.start();
    }

    private class VisualizerExecutor implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                FFT fft = Amperfi.playbackController.getPlayerAPI().getFFT();
                AudioPlayer currentPlayer = Amperfi.playbackController.getPlayerAPI().getCurrentPlayer();
                if (activeVisualizer != null && fft != null && currentPlayer != null && Amperfi.playbackController.getStatus() == PlayerAPI.PlayerStatus.PLAYING) {
                    fft.forward(currentPlayer.left);
                    Platform.runLater(() -> activeVisualizer.drawVisualizerFFT(fft));
                }
                try {
                    Thread.sleep(17);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

}
