package de.molaynoxx.amperfi.ui.controls.visualizer;

import ddf.minim.analysis.FFT;
import de.molaynoxx.amperfi.ui.controls.AbstractVisualizer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.MotionBlur;
import javafx.scene.paint.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class SphereVisualizer extends AbstractVisualizer {

    private final Color radialInner = Color.web("120000");
    private final Color radialOuter = Color.web("000000");

    private final Random rand = new Random();

    private final MotionBlur motionBlur = new MotionBlur();
    private final HashSet<Particle> particles = new HashSet<>();

    private long lastExec = 0;

    public SphereVisualizer() {
        super();
        this.setOpacity(1);
    }

    @Override
    public void drawVisualizerFFT(FFT fftData) {
        long frameTime = System.currentTimeMillis() - lastExec;
        lastExec = System.currentTimeMillis();

        getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());

        // Calculation
        double centerX = this.getWidth() / 2;
        double centerY = this.getHeight() / 2;

        double bass = 0;
        for (int i = 0; i < fftData.specSize() / 5; i++) {
            double a = fftData.getBand(i) / 4.7;
            bass += Math.pow(a, 2);
        }

        double volume = 0;
        for (int i = fftData.specSize() / 5; i < fftData.specSize(); i++) {
            volume += fftData.getBand(i);
        }
        volume = volume / fftData.specSize();

        volume = 0.95D / (1 + 10 * Math.exp(-1.25 * volume)) + 0.05;

        bass = volume * (175 / (1 + 50 * Math.exp(-0.014 * bass)));

        //bass = bass;
        double r = Math.min(this.getWidth() / 3, this.getHeight() / 3) + 0.5 * bass;

        // Particles
        double newParticles = (1 + rand.nextInt(3)) * (bass * 0.005);
        for (int i = 0; i < newParticles; i++) {
            particles.add(new Particle(1, Math.random() * this.getHeight(), 1 + rand.nextInt(3), 1 + (Math.random()) * 3, (0.5 - Math.random()) * 2, Color.WHITE.deriveColor(0, 1, 1 - (Math.random() / 2), 1)));
        }
        if (newParticles < 1 && rand.nextInt(12) == 0) {
            for (int i = 0; i < rand.nextInt(2); i++) {
                particles.add(new Particle(1, Math.random() * this.getHeight(), 1 + rand.nextInt(3), 1 + (Math.random()) * 3, (0.5 - Math.random()) * 2, Color.WHITE.deriveColor(0, 1, 1 - (Math.random() / 2), 1)));
            }
        }

        Iterator<Particle> itParticle = particles.iterator();
        while (itParticle.hasNext()) {
            Particle p = itParticle.next();
            p.update(bass * 0.025);
            if (!p.draw(this.getGraphicsContext2D())) {
                itParticle.remove();
            }
        }

        // Circle
        double offsetX = (Math.random() - 0.5) * (bass * 0.5) * 0.6;
        double offsetY = (Math.random() - 0.5) * (bass * 0.5) * 0.6;
        centerX += offsetX;
        centerY += offsetY;

        motionBlur.setAngle(Math.atan2(offsetX, offsetY) * 180 / Math.PI);
        motionBlur.setRadius(Math.max(10, 15 * (((Math.abs(offsetX) + Math.abs(offsetY)) / 2))));

        this.getGraphicsContext2D().setEffect(motionBlur);

        RadialGradient circle = new RadialGradient(90, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop(0, radialInner.deriveColor(1, bass * 0.01, bass * 0.01, 1)), new Stop(1, radialOuter));

        this.getGraphicsContext2D().setFill(circle);

        this.getGraphicsContext2D().fillOval(centerX - r, centerY - r, r * 2, r * 2);

        this.getGraphicsContext2D().setEffect(null);

        this.getGraphicsContext2D().setFill(Color.RED);
        this.getGraphicsContext2D().fillText("FPS: " + (1000 / frameTime) + "\n" + "Particles: " + particles.size(), 0, 10);
    }

    class Particle {

        private final double size;
        private final double velX;
        private final double velY;
        private final Paint baseColor;
        private double x;
        private double y;
        private double factor = 1;

        public Particle(double x, double y, double size, double velX, double velY, Paint baseColor) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.velX = velX;
            this.velY = velY;
            this.baseColor = baseColor;
        }

        public void update(double factor) {
            this.factor = factor;
            this.x += Math.max(0.3, this.velX * factor * 0.6);
            this.y += this.velY > 0 ? Math.max(0.2, this.velY * factor * 0.5) : Math.min(-0.2, this.velY * factor * 0.5);
        }

        public boolean draw(GraphicsContext gc) {
            gc.setFill(baseColor);
            gc.fillOval(x, y, size * Math.max(1, factor * 0.5), size * Math.max(1, factor * 0.5));
            return x > 0 && x < gc.getCanvas().getWidth() && y > 0 && y < gc.getCanvas().getHeight();
        }

    }

}
