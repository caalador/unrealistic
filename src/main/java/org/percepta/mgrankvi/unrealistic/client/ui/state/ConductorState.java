package org.percepta.mgrankvi.unrealistic.client.ui.state;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.shared.AbstractComponentState;

public class ConductorState extends AbstractComponentState {

	private static final long serialVersionUID = 2791619481887110956L;

	private String image;
	private int componentWidth, componentHeight, columns, rows;
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

	private List<DelayPositionState> delays = new LinkedList<DelayPositionState>();
	private List<LimitChangeState> changes = new LinkedList<LimitChangeState>();

	public int getDelay() {
		return delay;
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	public int getFrameDelay() {
		return frameDelay;
	}

	public void setFrameDelay(final int frameDelay) {
		this.frameDelay = frameDelay;
	}

	public int getLoopStartColumn() {
		return loopStartColumn;
	}

	public void setLoopStartColumn(final int loopStartColumn) {
		this.loopStartColumn = loopStartColumn;
	}

	public int getLoopStartRow() {
		return loopStartRow;
	}

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

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(final boolean loop) {
		this.loop = loop;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(final boolean reverse) {
		this.reverse = reverse;
	}

	public Integer getEndColumn() {
		return endColumn;
	}

	public void setEndColumn(final Integer endColumn) {
		this.endColumn = endColumn;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(final Integer endRow) {
		this.endRow = endRow;
	}

	public boolean isHide() {
		return hide;
	}

	public void setHide(final boolean hide) {
		this.hide = hide;
	}

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

	public int getComponentWidth() {
		return componentWidth;
	}

	public void setComponentWidth(final int componentWidth) {
		this.componentWidth = componentWidth;
	}

	public int getComponentHeight() {
		return componentHeight;
	}

	public void setComponentHeight(final int componentHeight) {
		this.componentHeight = componentHeight;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(final int columns) {
		this.columns = columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(final int rows) {
		this.rows = rows;
	}

	public List<DelayPositionState> getDelays() {
		return delays;
	}

	public void setDelays(final List<DelayPositionState> delays) {
		this.delays = delays;
	}

	public void addDelay(final DelayPositionState delay) {
		delays.add(delay);
	}

	public List<LimitChangeState> getChanges() {
		return changes;
	}

	public void setChanges(final List<LimitChangeState> changes) {
		this.changes = changes;
	}

	public void addLimitChange(final LimitChangeState createLimitChange) {
		changes.add(createLimitChange);
	}

}
