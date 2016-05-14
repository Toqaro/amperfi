package de.molaynoxx.amperfi.ui.controller;

public abstract class Controller<T> {

    protected final T control;

    public Controller(T control) {
        this.control = control;
    }

}
