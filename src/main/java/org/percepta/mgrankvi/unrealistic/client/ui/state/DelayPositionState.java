package org.percepta.mgrankvi.unrealistic.client.ui.state;

import com.vaadin.shared.AbstractComponentState;

public class DelayPositionState extends AbstractComponentState {

	private static final long serialVersionUID = 7195001429522307818L;

	private int delay, column, row;
	private boolean remove = false;
	private int repeat = 0;

	public int getDelay() {
		return delay;
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(final int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(final int row) {
		this.row = row;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(final boolean remove) {
		this.remove = remove;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(final int repeat) {
		this.repeat = repeat;
	}

}
