package it.alecata.sagra.service.printer;
import java.awt.Font;

public class LineaScontrino {
	
	String text;
	Font font;
	int align;
	int alignHeigh;
	byte[] img;
	
	
	public LineaScontrino(String text,byte[] img, Font font, int align,int alignHeign) {
		super();
		this.text = text;
		this.img = img;
		this.font = font;
		this.align = align;
		this.alignHeigh = alignHeign;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public int getAlign() {
		return align;
	}
	public void setAlign(int align) {
		this.align = align;
	}
	public int getAlignHeigh() {
		return alignHeigh;
	}
	public void setAlignHeigh(int alignHeigh) {
		this.alignHeigh = alignHeigh;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	
	
	

}
