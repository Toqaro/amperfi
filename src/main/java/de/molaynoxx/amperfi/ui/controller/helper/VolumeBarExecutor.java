package de.molaynoxx.amperfi.ui.controller.helper;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.ui.controller.HybridProgressbarController;
import de.molaynoxx.amperfi.ui.controller.skeleton.HybridProgressbarRunnable;

public class VolumeBarExecutor implements HybridProgressbarRunnable {

    @Override
    public void valueManuallyChanged(HybridProgressbarController controller, double newValue) {
        Amperfi.playbackController.setVolume(newValue);
    }

}
