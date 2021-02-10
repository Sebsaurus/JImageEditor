package oop.im2020;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageOperator {

    private final GrayscaleUI grayscaleUI = new GrayscaleUI();
    private final ThresholdUI thresholdUI = new ThresholdUI();
    private final ExtractUI extractUI = new ExtractUI();
    private final BlendUI blendUI = new BlendUI();
    private final DifferenceUI differenceUI = new DifferenceUI();
    private final NegativeUI negativeUI = new NegativeUI();
    private final TintUI tintUI = new TintUI();
    private final ChromaKeyUI chromaKeyUI = new ChromaKeyUI();
    private final ImageProcessor frame;

    public ImageOperator(ImageProcessor frame){
        this.frame=frame;

    }

    protected BufferedImage doGrayscale(final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, grayscaleUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.grayscale(inputRGB);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }

    protected BufferedImage doTint( final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, tintUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            final ColourComponent band = tintUI.getChosenColor();
            final double alpha = tintUI.getAlpha() / 100.0;
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.tint(inputRGB, alpha, band);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }

    protected BufferedImage doChromaKey( final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, chromaKeyUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            try {
                double sensitivity = chromaKeyUI.getSensitivity();
                BufferedImage otherImage = ImageIO.read(chromaKeyUI.getOtherImagePath());

                int targetRGB = chromaKeyUI.getTargetColor().getRGB();

                BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
                for (int x = 0; x < output.getWidth(); x++) {
                    for (int y = 0; y < output.getHeight(); y++) {
                        int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                        int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                        int outputRGB = OperationUtilities.chromaKey(inputRGB, otherRGB, targetRGB, sensitivity);
                        OperationUtilities.setRGB(x, y, outputRGB, output);
                    }
                }
                return output;
            } catch (IOException ex) {
                ex.printStackTrace();
                return inputImage;
            }
        } else {
            return inputImage;
        }
    }

    protected BufferedImage doNegative(final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, negativeUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.negative(inputRGB);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }

    protected BufferedImage doThreshold(final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, thresholdUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.threshold(inputRGB,thresholdUI.ThresholdNumber());
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }

    protected BufferedImage doDifference( final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, differenceUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            try {
                BufferedImage otherImage = ImageIO.read(differenceUI.getOtherImagePath());

                BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
                for (int x = 0; x < output.getWidth(); x++) {
                    for (int y = 0; y < output.getHeight(); y++) {
                        int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                        int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                        int outputRGB = OperationUtilities.difference(inputRGB, otherRGB);
                        OperationUtilities.setRGB(x, y, outputRGB, output);
                    }
                }
                return output;
            } catch (IOException ex) {
                ex.printStackTrace();
                return inputImage;
            }
        } else {
            return inputImage;
        }
    }

    protected BufferedImage doBlend( final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, blendUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            try {
                BufferedImage otherImage = ImageIO.read(blendUI.getOtherImagePath());
                double sensitivity = blendUI.getSensitivity();

                BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
                for (int x = 0; x < output.getWidth(); x++) {
                    for (int y = 0; y < output.getHeight(); y++) {
                        int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                        int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                        int outputRGB = OperationUtilities.blend(inputRGB, otherRGB,sensitivity);
                        OperationUtilities.setRGB(x, y, outputRGB, output);
                    }
                }
                return output;
            } catch (IOException ex) {
                ex.printStackTrace();
                return inputImage;
            }
        } else {
            return inputImage;
        }
    }

    protected BufferedImage doExtract( final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( frame, extractUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            final ColourComponent band = extractUI.getChosenColor();
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.extract(inputRGB, band);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }
}
