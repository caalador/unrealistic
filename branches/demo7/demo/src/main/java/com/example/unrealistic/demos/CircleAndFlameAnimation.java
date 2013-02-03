package com.example.unrealistic.demos;

import org.percepta.mgrankvi.unrealistic.ConductorCell;
import org.percepta.mgrankvi.unrealistic.UnrealisticComponent;
import org.percepta.mgrankvi.unrealistic.client.ui.data.LimitChange;

public class CircleAndFlameAnimation extends UnrealisticComponent {

	private static final long serialVersionUID = -3698813263032202763L;

	public CircleAndFlameAnimation() {
	}

	public void init() {
		setWidth("150px");
		setHeight("150px");
		setFps(15);
		final int BASE = 50;

		final ConductorCell cell = new ConductorCell("demo/images/CircleSprites.png", 90, 90, 29, 5);
		cell.setLoop(false);
		cell.setOffsetX(23);
		cell.setOffsetY(30);
		cell.setDelay(BASE);

		addImage(cell);

		addImage(createFlame(0, 81, 82, 136));

		addImage(createFlame(7, 17, 56, 145));

		addImage(createFlame(14, 81, 28, 136));

		addImage(createFlame(21, 54, 93, 145));

		addImage(createFlame(28, 28, 28, 122));

		addImage(createFlame(35, 92, 56, 128));

		addImage(createFlame(42, 28, 82, 136));

		addImage(createFlame(49, 54, 17, 145));

		//
		// addImage(createFlame(94, 81, 82));
		//
		// addImage(createFlame(101, 17, 56));
		//
		// addImage(createFlame(108, 81, 28));
		//
		// addImage(createFlame(116, 54, 93));
		//
		// addImage(createFlame(122, 28, 28));
		//
		// addImage(createFlame(128, 92, 56));
		//
		// addImage(createFlame(136, 28, 82));
		//
		// addImage(createFlame(145, 54, 17));
	}

	private ConductorCell createFlame(final int delay, final int offsetX, final int offsetY) {
		final ConductorCell flame = new ConductorCell("demo/images/LongerSmallFlameWithColor.png", 25, 25, 14, 2);
		flame.setLoopStartColumn(8);
		flame.setReverse(true);

		flame.setDelay(delay);
		flame.setOffsetX(offsetX);
		flame.setOffsetY(offsetY);

		return flame;
	}

	private ConductorCell createFlame(final int delay, final int offsetX, final int offsetY, final int dissapear) {
		final ConductorCell flame = new ConductorCell("demo/images/LongerSmallFlameWithColor.png", 25, 25, 14, 2);
		flame.setLoopStartColumn(8);
		flame.setReverse(true);

		final LimitChange limit = new LimitChange(delay + dissapear);
		limit.setLoop(false);
		limit.setReverse(true);
		limit.setLoopStartColumn(0);
		limit.setLoopStartRow(0);
		limit.setRemove(true);

		flame.addLimit(limit);
		flame.setDelay(delay);
		flame.setOffsetX(offsetX);
		flame.setOffsetY(offsetY);

		return flame;
	}
}
