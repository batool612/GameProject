package views;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Assets  {
	static ImageView view = new ImageView();
	public Assets() {

	}

	public static String ob() {
		return "ok.png";

	}
	
	public static Media back() {
		Media media = new Media(new File("back.mp3").toURI().toString());
		 return media;
	
	}
	public static Media win() {
		Media media = new Media(new File("winso.mp3").toURI().toString());
		 return media;
	
	}
	public static Media lose() {
		Media media = new Media(new File("gameover.mp3").toURI().toString());
		 return media;
	
	}
	
	/*public static Media begin() {
		Media media = new Media(new File("start.mp3").toURI().toString());
		 return media;
	
	}*/
	
	public static AudioClip click() {
		AudioClip media = new AudioClip(new File("click2.mp3").toURI().toString());
		 return media;
	
	}
	public static AudioClip att() {
		AudioClip media = new AudioClip(new File("attack.mp3").toURI().toString());
		 return media;
	
	}
	public static AudioClip dz() {
		AudioClip media = new AudioClip(new File("bloood.mp3").toURI().toString());
		 return media;
	
	}
	public static AudioClip cure() {
		AudioClip media = new AudioClip(new File("cure.mp3").toURI().toString());
		 return media;
	
	}
	public static AudioClip heal() {
		AudioClip media = new AudioClip(new File("heal.mp3").toURI().toString());
		 return media;
	
	}
	public static AudioClip exp() {
		AudioClip media = new AudioClip(new File("sp e.mp3").toURI().toString());
		 return media;
	
	}
	public static AudioClip az() {
		AudioClip media = new AudioClip(new File("zombie attack.mp3").toURI().toString());
		 return media;
	
	}
	public static AudioClip hel() {
		AudioClip media = new AudioClip(new File("help.mp3").toURI().toString());
		 return media;
	
	}
	public static Media intro() throws MalformedURLException {
		 Media media = new Media(new File("intro.wav").toURI().toString());
		 return media;
	}
	public static Media err() throws MalformedURLException {
		 Media media = new Media(new File("error.wav").toURI().toString());
		 return media;
	}
	//60 100
	public static ImageView joel(int x,int y) {
		Image img = new Image("joel.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView ellie(int x,int y) {
		Image img = new Image("ellie.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView tess(int x,int y) {
		Image img = new Image("tess.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView riley(int x,int y) {
		Image img = new Image("riley.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView tommy(int x,int y) {
		Image img = new Image("tommy.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView bill(int x,int y) {
		Image img = new Image("bill.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView david(int x,int y) {
		Image img = new Image("david.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView henery(int x,int y) {
		Image img = new Image("henery.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView z1(int x,int y) {
		Image img = new Image("zombie1.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView z2(int x,int y) {
		Image img = new Image("zombie2.png",50, 0, true, true);
	       view = new ImageView(img);
	       view.setFitWidth(x);
		      view.setFitHeight(y);
	      return view;
	}
	public static ImageView z3(int x,int y) {
		Image img = new Image("zombie3.png",50, 0, true, true);
	       view = new ImageView(img);
	      view.setFitWidth(x);
	      view.setFitHeight(y);
	      return view;
	}
	public static ImageView supply(int x,int y) {
		Image img = new Image("Backpack.png",50, 0, true, true);
	       view = new ImageView(img);
	       view.setFitWidth(x);
		      view.setFitHeight(y);
	      return view;
	}
	public static ImageView win(int x,int y) {
		Image img = new Image("winn.gif",500, 500, true, true);
	       view = new ImageView(img);
	       view.setFitWidth(x);
	       view.setFitHeight(y);
	      return view;
	}
	public static ImageView lose(int x,int y) {
		Image img = new Image("game over.gif",500, 500, true, true);
	       view = new ImageView(img);
	       view.setFitWidth(x);
	       view.setFitHeight(y);
	      return view;
	}
	public static ImageView vaccine(int x,int y) {
		Image img = new Image("MedicKit.png",50, 0, true, true);
	       view = new ImageView(img);
	       view.setFitWidth(x);
		      view.setFitHeight(y);
	      return view;
	}
	public static ImageView help(int x,int y) {
		Image img = new Image("help.png",50, 0, true, true);
	       view = new ImageView(img);
	       view.setFitWidth(x);
		      view.setFitHeight(y);
	      return view;
	}
	public static String steel() {
		return "steel.png";


	}
	public static String move() {
		return "move.jpeg";


	}

	public static String wood() {
		return "wood.jpeg";


	}

	public static RadialGradient  rad1() {
	Stop[] stops = new Stop[] {
	         new Stop(0.0, Color.WHITE),
	         new Stop(0.3, Color.RED),
	         new Stop(1.0, Color.DARKRED)};

	      RadialGradient gradient = new RadialGradient(0, 0, 300, 178, 60, false, CycleMethod.NO_CYCLE, stops);
	return gradient;
	}
}
