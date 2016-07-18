package de.molaynoxx.amperfi.ui.controls.visualizer;

import ddf.minim.analysis.FFT;
import de.molaynoxx.amperfi.Amperfi;
import de.molaynoxx.amperfi.ui.controller.TextVisualizerController;
import de.molaynoxx.amperfi.ui.controls.AbstractVisualizer;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.MotionBlur;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class TextVisualizer extends AbstractVisualizer {

    private final Color radialInner = Color.web("120000");
    private final Color radialInner2 = Color.web("001212");
    private final Color radialOuter = Color.web("000000");

    private final Random rand = new Random();

    private final MotionBlur motionBlur = new MotionBlur();
    private final HashSet<Particle> particles = new HashSet<>();
    private final HashSet<Fog> fogs = new HashSet<>();

    private char[] line1 = new char[]{'T', 'I', 'T', 'L', 'E'};
    private char[] line2 = new char[]{'A', 'R', 'T', 'I', 'S', 'T'};
    private final static Color[] colors = new Color[]{Color.DODGERBLUE, Color.DARKRED, Color.LIGHTCORAL, Color.LIGHTGREEN, Color.WHITE, Color.PEACHPUFF, Color.STEELBLUE};

    private long lastExec = 0;

    private int t = 0;
    private long[] frameTimes = new long[60];

    public final TextVisualizerController controller = new TextVisualizerController(this);

    public TextVisualizer() {
        super();
        this.setOpacity(1);

        for (int x = -200; x < 1920; x += 10) {
            for (int y = -200; y < 1080; y += 10) {
                if (rand.nextInt(8) == 0) {
                    int w = 120 + rand.nextInt(50);
                    fogs.add(new Fog(x, y, w, 120 + rand.nextInt(50), Math.random() * 3, 0));
                }
            }
        }
    }

    @Override
    public void drawVisualizerFFT(FFT fftData) {
        long frameTime = System.nanoTime() - lastExec;
        lastExec = System.nanoTime();
        t++;
        if (t >= frameTimes.length) t = 0;
        frameTimes[t] = frameTime;

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

        // Fog
        double newFog = rand.nextInt(2) == 0 ? 0 : 1;
        newFog += bass * 0.003;
        for (int i = 0; i < newFog; i++) {
            int w = 120 + rand.nextInt(50);
            int h = 120 + rand.nextInt(50);
            fogs.add(new Fog(1 - w, Math.random() * (getHeight() + h / 2) - h / 2, w, h, Math.random() * 3, 0));
        }

        Iterator<Fog> itFog = fogs.iterator();
        while (itFog.hasNext()) {
            Fog f = itFog.next();
            f.update(1 + (0.033D * bass));
            if (!f.draw(this.getGraphicsContext2D())) {
                itFog.remove();
            }
        }

        // Particles
        double newParticles = (1 + rand.nextInt(3)) * (bass * 0.005);
        for (int i = 0; i < newParticles; i++) {
            particles.add(new Particle(centerX, centerY, 1 + rand.nextInt(3), (-0.5 + Math.random()) * 3, (0.5 - Math.random()) * 3));
        }
        if (newParticles < 1 && rand.nextInt(12) == 0) {
            for (int i = 0; i < rand.nextInt(2); i++) {
                particles.add(new Particle(centerX, centerY, 1 + rand.nextInt(3), (-0.5 + Math.random()) * 3, (0.5 - Math.random()) * 3));
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

        // Text
        RadialGradient circle = new RadialGradient(90, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop(0, radialInner.deriveColor(1, bass * 0.01, bass * 0.01, 1)), new Stop(1, radialOuter));
        this.getGraphicsContext2D().setFill(circle);

        Font f = Font.font("sans-serif", FontWeight.BLACK, FontPosture.REGULAR, (75 + bass) / 1.75D + 10);
        this.getGraphicsContext2D().setFont(f);

        this.getGraphicsContext2D().setTextBaseline(VPos.CENTER);
        this.getGraphicsContext2D().setTextAlign(TextAlignment.CENTER);

        int lineLength = (int) ((getWidth() - 100) / 90);

        ArrayList<char[]> titleLine = new ArrayList<>();
        for (int offset = 0; offset < line1.length; offset += lineLength) {
            int i;
            for (i = Math.min(line1.length - 1, offset + lineLength - 1); i >= offset; i--) {
                if (line1[i] == ' ') {
                    break;
                }
            }
            if (i <= offset) i = Math.min(line1.length, offset + lineLength);
            char[] newLine = new char[i - offset];
            System.arraycopy(line1, offset, newLine, 0, i - offset);
            titleLine.add(newLine);
            offset = i - lineLength + 1;
        }

        double offsetX = (Math.random() - 0.5) * (bass * 0.5) * 0.4;
        double offsetY = (Math.random() - 0.5) * (bass * 0.5) * 0.4;

        motionBlur.setAngle(Math.atan2(offsetY, offsetX) * 180 / Math.PI);
        motionBlur.setRadius(Math.max(5, 15 * (((Math.abs(offsetX) + Math.abs(offsetY)) / 2))));

        this.getGraphicsContext2D().setEffect(motionBlur);

        for (int i = 0; i < titleLine.size(); i++) {
            char[] line = titleLine.get(i);
            double lineX = (getWidth() - line.length * 90) / 2D + 45;
            lineX += offsetX;
            double lineY = 100 + (centerY - getHeight() / 4) - (50 * titleLine.size()) + i * 100;
            lineY += offsetY;

            for (int j = 0; j < line.length; j++) {
                double x = j * 90 + lineX;
                this.getGraphicsContext2D().fillText("" + line[j], x, lineY);
            }
        }

        circle = new RadialGradient(90, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop(0, radialInner2.deriveColor(1, bass * 0.01, bass * 0.01, 1)), new Stop(1, radialOuter));
        this.getGraphicsContext2D().setFill(circle);

        ArrayList<char[]> artistLine = new ArrayList<>();
        for (int offset = 0; offset < line2.length; offset += lineLength) {
            int i;
            for (i = Math.min(line2.length - 1, offset + lineLength - 1); i >= offset; i--) {
                if (line2[i] == ' ') {
                    break;
                }
            }
            if (i <= offset) i = Math.min(line2.length, offset + lineLength);
            char[] newLine = new char[i - offset];
            System.arraycopy(line2, offset, newLine, 0, i - offset);
            artistLine.add(newLine);
            offset = i - lineLength + 1;
        }

        offsetX = (Math.random() - 0.5) * (bass * 0.5) * 0.4;
        offsetY = (Math.random() - 0.5) * (bass * 0.5) * 0.4;

        motionBlur.setAngle(Math.atan2(offsetY, offsetX) * 180 / Math.PI);
        motionBlur.setRadius(Math.max(5, 15 * (((Math.abs(offsetX) + Math.abs(offsetY)) / 2))));

        this.getGraphicsContext2D().setEffect(motionBlur);

        for (int i = 0; i < artistLine.size(); i++) {
            char[] line = artistLine.get(i);
            double lineX = (getWidth() - line.length * 90) / 2D + 45;
            lineX += offsetX;
            double lineY = (centerY + getHeight() / 4) - (50 * artistLine.size()) + i * 100;
            lineY += offsetY;

            for (int j = 0; j < line.length; j++) {
                double x = j * 90 + lineX;
                this.getGraphicsContext2D().fillText("" + line[j], x, lineY);
            }
        }

        this.getGraphicsContext2D().setEffect(null);

        long timeAvg = 0;
        for (int i = 0; i < frameTimes.length; i++) {
            timeAvg += frameTimes[i];
        }
        timeAvg = timeAvg / frameTimes.length;

        Amperfi.primaryStage.setTitle("Amperfi | FPS: " + (1000000000L / timeAvg));
    }

    public void setText(String str1, String str2) {
        line1 = str1.toCharArray();
        line2 = str2.toCharArray();

        for (int i = 0; i < line1.length; i++) {
            line1[i] = Character.toUpperCase(line1[i]);
        }

        for (int i = 0; i < line2.length; i++) {
            line2[i] = Character.toUpperCase(line2[i]);
        }
    }

    private final static Color fogColor = Color.color(1, 1, 1, 0.004);

    class Fog {

        private final double w;
        private final double h;
        private final double velX;
        private final double velY;
        private double x;
        private double y;

        public Fog(double x, double y, double w, double h, double velX, double velY) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.velX = velX;
            this.velY = velY;
        }

        public void update(double factor) {
            this.x += this.velX * factor * 0.3;
            this.y += this.velY * factor * 0.3;
        }

        public boolean draw(GraphicsContext gc) {
            gc.setFill(fogColor);
            gc.fillOval(x, y, w, h);
            return x + w >= 0 && x < gc.getCanvas().getWidth() && y + h >= 0 && y < gc.getCanvas().getHeight();
        }

    }

    class Particle {

        private final double size;
        private final double velX;
        private final double velY;
        private final Color baseColor = colors[rand.nextInt(colors.length)];
        private Paint color = baseColor;
        private double x;
        private double y;
        private double factor = 1;

        public Particle(double x, double y, double size, double velX, double velY) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.velX = velX;
            this.velY = velY;
        }

        public void update(double factor) {
            this.factor = factor;
            this.x += this.velX * factor * 0.9;
            this.y += this.velY * factor * 0.9;

            double p = Math.min(Math.max(0, (4 - factor) / 4), 1);
            color = Color.color(baseColor.getRed() * (1 - p) + 1 * p, baseColor.getGreen() * (1 - p) + 1 * p, baseColor.getBlue() * (1 - p) + 1 * p);
        }

        public boolean draw(GraphicsContext gc) {
            gc.setFill(color);
            gc.fillOval(x, y, size * Math.max(1, factor * 0.5), size * Math.max(1, factor * 0.5));
            return x > 0 && x < gc.getCanvas().getWidth() && y > 0 && y < gc.getCanvas().getHeight();
        }

    }

}
