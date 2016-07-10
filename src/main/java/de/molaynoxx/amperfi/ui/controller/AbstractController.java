package de.molaynoxx.amperfi.ui.controller;

import javafx.scene.Node;

public abstract class AbstractController<T extends Node> {

    protected final T control;

    public AbstractController(T control) {
        this.control = control;
    }

}
