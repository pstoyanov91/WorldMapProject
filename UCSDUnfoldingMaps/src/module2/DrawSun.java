package module2;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawSun extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url = "C:\\Users\\cpt2pes\\Pictures\\beach.jpg";
	private PImage backgroudImg;
	
	public void setup(){
		size(1280,800);
		this.backgroudImg = loadImage(url, "jpg");
		
	}
	
	public void draw(){
		this.backgroudImg.resize(0, height);
		image (this.backgroudImg, 0 ,0);	
		
		int[] color = sunColorSec(second());
		fill(color[0], color[1], color[2]);
		ellipse(width/4, height/5, 200, 200);
	}
	
	public int[] sunColorSec(float seconds){
		int[] rgb = new int[3];
		
		float diffFrom30 = Math.abs(30 - seconds);
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
	}
	
}
