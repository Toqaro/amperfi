package de.molaynoxx.amperfi.ui.controller.helper;

import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.ui.controller.HybridProgressbarController;
import de.molaynoxx.amperfi.ui.controller.skeleton.HybridProgressbarRunnable;

public class TimeBarExecutor implements HybridProgressbarRunnable {

    @Override
    public void valueManuallyChanged(HybridProgressbarController controller, double newValue) {
        controller.setValue(newValue);
        Amperfi.playbackController.jumpToPercent(newValue);
    }

}
