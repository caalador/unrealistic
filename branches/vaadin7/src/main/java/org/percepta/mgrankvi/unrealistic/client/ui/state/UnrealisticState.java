package org.percepta.mgrankvi.unrealistic.client.ui.state;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.shared.AbstractComponentState;

public class UnrealisticState extends AbstractComponentState {

	private static final long serialVersionUID = 1615128227442340685L;

	private int fps = 4;
	private String playString = "unrealistic/images/Play.png";
	private int playWidth = 75;
	private int playHeight = 50;

	private String path = null;

	private List<ConductorState> conductors = new LinkedList<ConductorState>();

	public int getFps() {
		return fps;
	}

	public void setFps(final int fps) {
		this.fps = fps;
	}

	public String getPlayString() {
		return playString;
	}

	public void setPlayString(final String playString) {
		this.playString = playString;
	}

	public int getPlayWidth() {
		return playWidth;
	}

	public void setPlayWidth(final int playWidth) {
		this.playWidth = playWidth;
	}

	public int getPlayHeight() {
		return playHeight;
	}

	public void setPlayHeight(final int playHeight) {
		this.playHeight = playHeight;
	}

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	public List<ConductorState> getConductors() {
		return conductors;
	}

	public void setConductors(final List<ConductorState> conductors) {
		this.conductors = conductors;
	}

	public void addConductor(final ConductorState conductor) {
		conductors.add(conductor);
	}

}
