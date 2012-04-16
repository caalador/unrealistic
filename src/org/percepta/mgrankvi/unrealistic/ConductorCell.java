package org.percepta.mgrankvi.unrealistic;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.unrealistic.client.ui.data.DelayPosition;
import org.percepta.mgrankvi.unrealistic.client.ui.data.LimitChange;

public class ConductorCell {

	private long id;

	private final String image;
	private final int width, height, columns, rows;
	private int delay = 0;
	private int frameDelay = 0;
	private int loopStartColumn = 1;
	private int loopStartRow = 1;
	private int offsetX = 0;
	private int offsetY = 0;
	private boolean loop = true;
	private boolean reverse = false;
	private Integer endColumn = null;
	private Integer endRow = null;
	private boolean hide = false;

	private final List<DelayPosition> delays = new LinkedList<DelayPosition>();
	private final List<LimitChange> changes = new LinkedList<LimitChange>();

	public ConductorCell(final String image, final int width, final int height, final int columns, final int rows) {
		this.image = image;
		this.width = width;
		this.height = height;
		this.columns = columns;
		this.rows = rows;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	/**
	 * Image with cells
	 * 
	 * @return
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Cell width
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Cell height
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Amount of cell columns
	 * 
	 * @return
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Amount of cell rows
	 * 
	 * @return
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Frames to delay drawing of cells
	 * 
	 * @return
	 */
	public int getDelay() {
		return delay;
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	/**
	 * Delay between each drawing of a cell (frames in between)
	 * 
	 * @return
	 */
	public int getFrameDelay() {
		return frameDelay;
	}

	public void setFrameDelay(final int frameDelay) {
		this.frameDelay = frameDelay;
	}

	/**
	 * Column looping starts from (reverse ends)
	 * 
	 * @return
	 */
	public int getLoopStartColumn() {
		return loopStartColumn;
	}

	/**
	 * Set the column looping should start from.
	 * 
	 * Note that first column is 1 for user convenience.
	 * 
	 * @param loopStartColumn
	 */
	public void setLoopStartColumn(final int loopStartColumn) {
		this.loopStartColumn = loopStartColumn;
	}

	/**
	 * Row looping starts from (reverse ends)
	 * 
	 * @return
	 */
	public int getLoopStartRow() {
		return loopStartRow;
	}

	/**
	 * Set the row that looping should start from.
	 * 
	 * Note that first row is 1 for user convenience.
	 * 
	 * @param loopStartRow
	 */
	public void setLoopStartRow(final int loopStartRow) {
		this.loopStartRow = loopStartRow;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(final int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(final int offsetY) {
		this.offsetY = offsetY;
	}

	/**
	 * loop image frames
	 * 
	 * @return
	 */
	public boolean shouldLoop() {
		return loop;
	}

	public void setLoop(final boolean loop) {
		this.loop = loop;
	}

	/**
	 * hide image at end
	 * 
	 * @return
	 */
	public boolean shouldHide() {
		return hide;
	}

	public void setHide(final boolean hide) {
		this.hide = hide;
	}

	/**
	 * When at end of frames start animating backward
	 * 
	 * @return
	 */
	public boolean reverseAnimate() {
		return reverse;
	}

	public void setReverse(final boolean reverse) {
		this.reverse = reverse;
	}

	/**
	 * Special frame delays
	 * 
	 * @return
	 */
	public List<DelayPosition> getDelays() {
		return new LinkedList<DelayPosition>(delays);
	}

	public void addDelay(final DelayPosition delayPositon) {
		delays.add(delayPositon);
	}

	public void removeDelay(final DelayPosition delayPosition) {
		delays.remove(delayPosition);
	}

	/**
	 * Special limit changes
	 * 
	 * @return
	 */
	public List<LimitChange> getChanges() {
		return new LinkedList<LimitChange>(changes);
	}

	public void addLimit(final LimitChange limitChange) {
		changes.add(limitChange);
	}

	public void removeLimit(final LimitChange limitChange) {
		changes.remove(limitChange);
	}

	/**
	 * End column and row if other than last column+row wanted.
	 * 
	 * @param column
	 * @param row
	 */
	public void setEnd(final Integer column, final Integer row) {
		endColumn = column;
		endRow = row;
	}

	public Integer getEndColumn() {
		return endColumn;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public boolean hasEnd() {
		return endRow != null && endColumn != null;
	}
}
