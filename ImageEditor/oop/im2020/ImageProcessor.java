package oop.im2020;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author childm
 */
public class ImageProcessor extends JFrame{
	private static final long serialVersionUID = 1L;

	private final JFileChooser chooser = new JFileChooser();
	private final ImagePanel imagePanel = new ImagePanel();
	private BufferedImage image;
	private File loadedImage;

	private final JMenu fileMenu = new JMenu("File");
	private final JMenu opMenu = new JMenu("Operations");
	private final ImageOperator imageOperator = new ImageOperator(this);
	public ImageProcessor() {
		this.chooser.setMultiSelectionEnabled(false);
		this.chooser.setCurrentDirectory(new File(".")); // set current directory
		this.setTitle("Graphic designer V1");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		final JMenuBar menuBar = new JMenuBar();
		menuBar.add(this.fileMenu);
		menuBar.add(this.opMenu);
		setJMenuBar(menuBar);
		loadImage(new File("beach.jpg"));
		add(this.imagePanel, BorderLayout.CENTER);
		pack();
		menuAddOperator();
		this.setVisible( true);
	}
	private void addFileMenuOperation(final String identifier) {
		final JMenuItem item = new JMenuItem(identifier);
		item.addActionListener(ev -> doOperation(identifier));
		this.fileMenu.add(item);
	}
	private void addOpMenuOperation(final String identifier) {
		final JMenuItem item = new JMenuItem(identifier);
		item.addActionListener(ev -> doOperation(identifier));
		this.opMenu.add(item);
	}
	private void menuAddOperator(){
		addOpMenuOperation( "Grayscale");
		addOpMenuOperation( "Negative");
		addOpMenuOperation( "Tint");
		addOpMenuOperation( "Extract");
		addOpMenuOperation( "Threshold");
		addOpMenuOperation( "Difference");
		addOpMenuOperation( "Blend");
		addOpMenuOperation( "Chromakey");
		addFileMenuOperation( "Open");
		addFileMenuOperation( "Revert");

	}
	private File chooseImageFile() {
		if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			return this.chooser.getSelectedFile();
		} else {
			return null;
		}
	}
	private void doOpenImage() {
		final File file = chooseImageFile();
		if (file != null) {
			loadImage(file);
		}
	}
	private void loadImage(final File file) {
		try {
			this.image = ImageIO.read(file);
			this.loadedImage = file;
			setImage( this.image);
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}
	private void setImage(final BufferedImage image) {
		this.image = image;
		this.imagePanel.setImage(image);
		pack();
	}
	private void revert() {
		if (this.loadedImage != null) {
			loadImage(this.loadedImage);
		}
	}
	private void doOperation( final String identifier) {
		if ( identifier.equals( "Grayscale")) {
			setImage( imageOperator.doGrayscale( this.image));
		} else if ( identifier.equals( "Tint")) {
			setImage( imageOperator.doTint( this.image));
		} else if ( identifier.equals( "Chromakey")) {
			setImage( imageOperator.doChromaKey( this.image));
		} else if (identifier.equals("Negative")){
			setImage( imageOperator.doNegative( this.image));
		} else if (identifier.equals("Threshold")){
			setImage (imageOperator.doThreshold(this.image));
		} else if (identifier.equals("Difference")){
			setImage( imageOperator.doDifference(this.image));
		} else if (identifier.equals("Blend")){
			setImage(imageOperator.doBlend(this.image));
		} else if (identifier.equals("Extract")){
			setImage( imageOperator.doExtract(this.image));
		}else if (identifier.equals("Open")){
			doOpenImage();
		}else if (identifier.equals("Revert")){
			revert();
		}
	}
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new ImageProcessor());
	}

}
