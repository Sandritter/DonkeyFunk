package utilities;

import gui.DonkeyGUI;
import gui.ImageLabel;
import gui.MaskPanel;
import gui.Stomach;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import enums.CategoryType;
import builder.GuiBuilder;

import businessLogic.Player;

/**
 * 
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 * 
 * MouseMotion KLasse regiert auf alle Click- und Release-Events der Dragobjekte
 * und stoesst Methoden der Business-Logik und Gui an
 */
public class MouseListen implements MouseListener {
	private final long loopEnd = 8000; // Laenge der Soundspuren

	private ArrayList<Player> plList; 
	GuiBuilder guiBuilder;

	private boolean[] animationArray;
	private int[] animationCountArray;

	/**
	 * MouseListen Konstruktor
	 * @param plList Arraylist, die die Player enthaelt
	 * @param guiBuilder enthaelt Gui Elemente 
	 */
	public MouseListen(ArrayList<Player> plList, GuiBuilder guiBuilder) {
		this.animationArray = new boolean[5];
		this.animationCountArray = new int[5];
		this.plList = plList;
		this.guiBuilder = guiBuilder;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Timer timer = new Timer(); // Timer dient als Verzoegerung des Donkey Cyclus
		DonkeyGUI donkeyGui = DonkeyGUI.getInstance(guiBuilder); 
		donkeyGui.setCurser("drag");
		Stomach stomach = guiBuilder.getStomach();
		MaskPanel maske = guiBuilder.getMaskPanel();
		ImageLabel source = (ImageLabel) e.getSource();
		final String name = source.getName();

		// wenn Drag Objekt im Bauch war
		if (source.isInStomach()) {
			// und aktuell nicht mehr dann
			if (!maske.isred(source.getX(), source.getY())) {
				//setze Drag Objekt auf Startposition und update Mageninhalt
				source.setLocation(source.getStartX(), source.getStartY());
				stomach.deleteDragObject(name);
				updateDeleteDragObject(name);
				source.setColorMask(false);
				for (int i = 0; i < plList.size(); i++) {
					if (plList.get(i) != null) {
						String title = plList.get(i).getTrack().getTitle();
						if (title.compareTo(name) == 0) {
							plList.get(i).stop(); // stoppe den durch DragObjekt abgespielten Song
							plList.set(i, null);  
						}
					}
				}
			} else {
				// ansonsten zurueck in den Magen mit DragObjekt
				int[] pos = stomach.getDragObjectPos(source.getName());
				source.setLocation(pos[0], pos[1]);
				source.setColorMask(true);
			}
		// ansonsten wenn Drag Objekt nicht im Magen war
		} else if (!source.isInStomach()) {
			// und das Drag Objekt sich nicht ueber dem Mund befindet
			if (!maske.isblack(source.getX(), source.getY())) {
				// dann setze Drag Objekt zurueck auf die Startposition
				source.setColorMask(false);
				source.setLocation(source.getStartX(), source.getStartY());
			} else {
				// ansonsten wenn Esel noch nicht satt
				if (!stomach.isFull()) {
					// wandert Drag Objekt in den Magen und Esel spielt dessn Song
					source.setColorMask(true);
					stomach.addDragObject(source);
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							updateAddDragObject(name);
						}
					}, loopEnd - donkeyGui.getWatchPos());
					Player actPlayer = (Player) guiBuilder.getDragObjectByName(
							name).getPlayer();

					for (int i = 0; i < plList.size(); i++) {
						if (plList.get(i) == null) {
							plList.set(i, actPlayer);
							break;
						}
					}
				} else {
					// ansonsten gehe zurueck auf Startposition
					source.setLocation(source.getStartX(), source.getStartY());
				}
			}
		}

	}

	/**
	 * setzt das AnimationArray, sodass Esel sich dementsprechend bewegt
	 * setzt den Count from AnimationCountArray
	 * @param name Name des DragObjekts
	 */
	private void updateAddDragObject(String name) {
		CategoryType actCategory = guiBuilder.getDragObjectByName(name)
				.getCategory();
		if (actCategory == CategoryType.BASS) {
			this.animationCountArray[0] += 1;
			this.animationArray[0] = true;
		} else if (actCategory == CategoryType.BEATS) {
			this.animationCountArray[1] += 1;
			this.animationArray[1] = true;
		} else if (actCategory == CategoryType.HARMONY) {
			this.animationCountArray[2] += 1;
			this.animationArray[2] = true;
		} else if (actCategory == CategoryType.MELODY) {
			this.animationCountArray[3] += 1;
			this.animationArray[3] = true;
		} else if (actCategory == CategoryType.EFFECTS) {
			this.animationCountArray[4] += 1;
			this.animationArray[4] = true;
		}
		guiBuilder.getDonkey().setAnimationArray(this.animationArray);
	}

	/**
	 * setzt das AnimationArray, sodass Esel dementsprechend aufhoert sich zu bewegen
	 * setzt den Count from AnimationCountArray
	 * @param name des DragObjekts
	 */
	private void updateDeleteDragObject(String name) {
		CategoryType actCategory = guiBuilder.getDragObjectByName(name)
				.getCategory();
		if (actCategory == CategoryType.BASS) {
			this.animationCountArray[0] -= 1;
			this.animationArray[0] = checkDragObjectCount(0);
		} else if (actCategory == CategoryType.BEATS) {
			this.animationCountArray[1] -= 1;
			this.animationArray[1] = checkDragObjectCount(1);
		} else if (actCategory == CategoryType.HARMONY) {
			this.animationCountArray[2] -= 1;
			this.animationArray[2] = checkDragObjectCount(2);
		} else if (actCategory == CategoryType.MELODY) {
			this.animationCountArray[3] -= 1;
			this.animationArray[3] = checkDragObjectCount(3);
		} else if (actCategory == CategoryType.EFFECTS) {
			this.animationCountArray[4] -= 1;
			this.animationArray[4] = checkDragObjectCount(4);
		}
		guiBuilder.getDonkey().setAnimationArray(this.animationArray);
	}

	/**
	 * checkt ob Animation true oder false gesetzt werden soll
	 * @param i, count from AnimationCountArray
	 * @return true wenn Count groesser 0 else false
	 */
	private boolean checkDragObjectCount(int i) {
		if (animationCountArray[i] > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

}