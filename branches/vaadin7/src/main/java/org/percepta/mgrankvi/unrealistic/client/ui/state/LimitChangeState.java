package org.percepta.mgrankvi.unrealistic.client.ui.state;

import com.vaadin.shared.AbstractComponentState;

public class LimitChangeState extends AbstractComponentState {

	private static final long serialVersionUID = 358418923809944905L;

	private int delay;

	private Integer loopStartColumn;
	private Integer loopStartRow;

	private Integer endColumn;
	private Integer endRow;

	private Boolean loop;
	private Boolean reverse;

	private Boolean remove;
	private Boolean hide;

	private Integer flicker;
	private Integer fadeIn;
	private Integer fadeOut;

	public int getDelay() {
		return delay;
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	public Integer getLoopStartColumn() {
		return loopStartColumn;
	}

	public void setLoopStartColumn(final Integer loopStartColumn) {
		this.loopStartColumn = loopStartColumn;
	}

	public Integer getLoopStartRow() {
		return loopStartRow;
	}

	public void setLoopStartRow(final Integer loopStartRow) {
		this.loopStartRow = loopStartRow;
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

	public Boolean getLoop() {
		return loop;
	}

	public void setLoop(final Boolean loop) {
		this.loop = loop;
	}

	public Boolean getReverse() {
		return reverse;
	}

	public void setReverse(final Boolean reverse) {
		this.reverse = reverse;
	}

	public Boolean getRemove() {
		return remove;
	}

	public void setRemove(final Boolean remove) {
		this.remove = remove;
	}

	public Boolean getHide() {
		return hide;
	}

	public void setHide(final Boolean hide) {
		this.hide = hide;
	}

	public Integer getFlicker() {
		return flicker;
	}

	public void setFlicker(final Integer flicker) {
		this.flicker = flicker;
	}

	public Integer getFadeIn() {
		return fadeIn;
	}

	public void setFadeIn(final Integer fadeIn) {
		this.fadeIn = fadeIn;
	}

	public Integer getFadeOut() {
		return fadeOut;
	}

	public void setFadeOut(final Integer fadeOut) {
		this.fadeOut = fadeOut;
	}

}
