package org.percepta.mgrankvi.unrealistic.client.ui.data;

/**
 * 
 * @author m00se
 * 
 */
public class DelayPosition {

	private int delay, column, row;
	private boolean remove = false;
	private int repeat = 0;

	/**
	 * Construct delay component.
	 * 
	 * Note. Column and row numbering start at 1.
	 * 
	 * @param delay
	 * @param column
	 *            delay start column
	 * @param row
	 *            delay start row
	 */
	public DelayPosition(final int delay, final int column, final int row) {
		this.delay = delay;
		this.column = column;
		this.row = row;
	}

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

	public boolean removeFlag() {
		return remove;
	}

	public boolean removeDelay() {
		if (remove && repeat > 0) {
			repeat--;
			return false;
		}
		return remove;
	}

	public void setRemove(final boolean remove) {
		this.remove = remove;
	}

	public void setRepeat(final int repeat) {
		this.repeat = repeat;
	}

	public int getRepeat() {
		return repeat;
	}
}
