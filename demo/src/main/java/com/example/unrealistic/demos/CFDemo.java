package com.example.unrealistic.demos;

import java.util.Random;

import org.percepta.mgrankvi.unrealistic.ConductorCell;
import org.percepta.mgrankvi.unrealistic.UnrealisticComponent;
import org.percepta.mgrankvi.unrealistic.client.ui.data.DelayPosition;
import org.percepta.mgrankvi.unrealistic.client.ui.data.LimitChange;

public class CFDemo extends UnrealisticComponent {

	private static final long serialVersionUID = -1374360803043332849L;

	private final Random random = new Random(System.currentTimeMillis());

	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;

	public CFDemo() {
	}

	public void init() {
		setWidth(WIDTH + "px");
		setHeight(HEIGHT + "px");
		setFps(20);

		createStartRain();
		createGrowingRain();
		createGrowingTree();
		createDemoText();

	}

	private void createDemoText() {
		final ConductorCell text = new ConductorCell("demo/images/cfDemo/CFDemoText.png", 250, 150, 29, 3);
		text.setLoopStartColumn(29);
		text.setLoopStartRow(3);
		text.setDelay(40);

		final DelayPosition delay = new DelayPosition(80, 8, 3);
		delay.setRemove(true);
		text.addDelay(delay);

		LimitChange limit = new LimitChange(120);
		limit.setFadeOut(7000);
		text.addLimit(limit);

		addImage(text);
		ConductorCell flicker = new ConductorCell("demo/images/cfDemo/Flicker1.png", 250, 150, 2, 2);
		flicker.setLoopStartColumn(2);
		flicker.setLoopStartRow(2);
		flicker.setDelay(text.getDelay() + 11);

		DelayPosition del = new DelayPosition(50, 0, 1);// 31
		// del.setRemove(true);
		flicker.addDelay(del);

		del = new DelayPosition(40, 1, 1);// 9
		// del.setRemove(true);
		flicker.addDelay(del);

		del = new DelayPosition(28, 0, 2);// 18
		// del.setRemove(true);
		flicker.addDelay(del);

		limit = new LimitChange(55);
		limit.setFadeOut(3000);
		flicker.addLimit(limit);

		addImage(flicker);

		flicker = new ConductorCell("demo/images/cfDemo/Flicker2.png", 250, 150, 2, 2);
		flicker.setLoopStartColumn(2);
		flicker.setLoopStartRow(2);
		flicker.setDelay(text.getDelay() + 20);

		del = new DelayPosition(48, 0, 1);
		// del.setRemove(true);
		flicker.addDelay(del);

		del = new DelayPosition(18, 1, 1);
		// del.setRemove(true);
		flicker.addDelay(del);

		del = new DelayPosition(18, 0, 2);
		// del.setRemove(true);
		flicker.addDelay(del);

		limit = new LimitChange(50);
		limit.setFadeOut(2500);
		flicker.addLimit(limit);

		addImage(flicker);

		final ConductorCell vaadin = new ConductorCell("demo/images/cfDemo/vaadin.png", 100, 100, 1, 1);
		vaadin.setDelay(50);
		vaadin.setOffsetX(20);
		vaadin.setOffsetY(HEIGHT - 150);

		limit = new LimitChange(0);
		limit.setFadeIn(5000);
		vaadin.addLimit(limit);

		limit = new LimitChange(120);
		limit.setFadeOut(5000);
		limit.setLoop(false);
		vaadin.addLimit(limit);

		addImage(vaadin);

	}

	private void createGrowingTree() {
		final ConductorCell tree = new ConductorCell("demo/images/cfDemo/SmallTreeSprites.png", 180, 250, 16, 2);
		tree.setLoop(false);
		tree.setDelay(100);
		tree.setFrameDelay(5);
		tree.setOffsetX(190);
		tree.setOffsetY(35);

		DelayPosition delay = new DelayPosition(58, 2, 1);
		delay.setRemove(true);
		tree.addDelay(delay);

		addImage(tree);

		final ConductorCell leaves = new ConductorCell("demo/images/cfDemo/Leaves.png", 155, 225, 15, 3);
		leaves.setLoop(false);
		leaves.setHide(true);
		leaves.setFrameDelay(2);
		leaves.setDelay(tree.getDelay() + (32 * tree.getFrameDelay()) + 100);
		leaves.setOffsetX(215);
		leaves.setOffsetY(60);

		delay = new DelayPosition(80, 8, 1);
		delay.setRemove(true);
		leaves.addDelay(delay);

		delay = new DelayPosition(120, 15, 1);
		delay.setRemove(true);
		leaves.addDelay(delay);

		addImage(leaves);

		final ConductorCell ground = new ConductorCell("demo/images/cfDemo/ground.png", 400, 37, 1, 1);
		ground.setLoop(false);
		ground.setOffsetY(HEIGHT - 37);

		final LimitChange fadeIn = new LimitChange(0);
		fadeIn.setFadeIn(2500);
		ground.addLimit(fadeIn);

		addImage(ground);
	}

	private void createStartRain() {
		ConductorCell drop;
		for (int i = 0; i < 30; i++) {
			drop = new ConductorCell("demo/images/Rain.png", 1, 300, 84, 1);

			drop.setOffsetX(random.nextInt(WIDTH));
			drop.setFrameDelay(random.nextInt(2));
			drop.setEnd(57 - random.nextInt(15), 1);

			final int delay = random.nextInt(25);
			drop.setDelay(delay);

			final LimitChange change = new LimitChange(90 - (delay * (drop.getFrameDelay() + 1)));
			change.setRemove(true);
			drop.addLimit(change);

			addImage(drop);
		}
		for (int i = 0; i < 30; i++) {
			drop = new ConductorCell("demo/images/Rain.png", 1, 300, 84, 1);

			drop.setOffsetX(random.nextInt(WIDTH));
			drop.setFrameDelay(random.nextInt(2));
			drop.setEnd(57 - random.nextInt(15), 1);

			final int delay = 25 + random.nextInt(25);
			drop.setDelay(delay);

			final LimitChange change = new LimitChange(90 - (delay * (drop.getFrameDelay() + 1)));
			change.setRemove(true);
			drop.addLimit(change);

			addImage(drop);
		}
	}

	private void createGrowingRain() {
		ConductorCell drop;
		for (int k = 1; k <= 20; k++) {
			for (int i = 1; i < 40; i++) {
				drop = new ConductorCell("demo/images/Rain.png", 1, 300, 84, 1);

				drop.setOffsetX(random.nextInt(WIDTH));
				drop.setFrameDelay(random.nextInt(3));
				drop.setEnd(52 - random.nextInt(10), 1);

				final int delay = 90 + random.nextInt(250);
				drop.setDelay(delay);

				final LimitChange change = new LimitChange((random.nextInt(50) / (drop.getFrameDelay() + 1)) + 40);
				change.setRemove(true);

				drop.addLimit(change);

				addImage(drop);
			}
		}
	}
}
