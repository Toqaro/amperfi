package de.molaynoxx.amperfi.ui.controller;

public abstract class AbstractController<T> {

    protected final T control;

    public AbstractController(T control) {
        this.control = control;
    }

}
