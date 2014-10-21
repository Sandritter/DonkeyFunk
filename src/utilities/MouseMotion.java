package utilities;

import gui.DonkeyGUI;
import gui.ImageLabel;
import gui.MaskPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import builder.GuiBuilder;

/**
 * 
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 * 
 * MouseMotion KLasse regiert auf alle Drag-Events der Dragobjekte
 * und stoesst Methoden der Business-Logik und Gui an
 */
public class MouseMotion implements MouseMotionListener{
	private int imageX, imageY;
	private GuiBuilder guiBuilder;

	/**
	 * MouseMotion Konstruktor
	 * @param guiBuilder stellt Gui Elemente bereit
	 */
	public MouseMotion(GuiBuilder guiBuilder){
		this.guiBuilder = guiBuilder;
	}

	public void mouseDragged(MouseEvent e) {
		DonkeyGUI donkeyGui = DonkeyGUI.getInstance(guiBuilder);
		MaskPanel mask = guiBuilder.getMaskPanel();
		ImageLabel source = (ImageLabel) e.getSource();
		imageX = e.getX();
		imageY = e.getY();
		source.setLocation(imageX + source.getX() - source.getWidth() / 2, imageY + source.getY() - source.getHeight() / 2);
		
		// wenn Drag Objekt nicht im Bauch war
		if(!source.isInStomach()){
			// und sowohl Bauch voll ist und DragObjekt ueber dem Mund
			if(guiBuilder.getStomach().isFull() && mask.isblack(source.getX(), source.getY())){
				// aendere den Cursor auf full
				donkeyGui.setCurser("full");
			}
			// ansonsten wenn Drag Objekt ueber dem Mund
			else if(mask.isblack(source.getX(), source.getY())){
				// setzte Cursor auf drop
				donkeyGui.setCurser("drop");
			} else {
				// ansonsten setze Cursor auf drag
				donkeyGui.setCurser("drag");
			}
		// wenn Drag Objekt im Bauch war
		} else {
			// und aktuell nicht mehr im Bauch ist
			if(!mask.isred(source.getX(), source.getY())){
				// dann setze den Cursor auf delete
				donkeyGui.setCurser("delete");
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
