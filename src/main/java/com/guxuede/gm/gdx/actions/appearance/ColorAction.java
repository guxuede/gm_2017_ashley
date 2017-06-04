
package com.guxuede.gm.gdx.actions.appearance;

import com.badlogic.gdx.graphics.Color;
import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.PresentableComponent;

public class ColorAction extends TemporalAction {
	private float startR, startG, startB, startA;
	private Color color;
	private final Color end = new Color();

	protected void begin () {
		if (color == null) color = Mappers.presentableCM.get(actor).color;
		startR = color.r;
		startG = color.g;
		startB = color.b;
		startA = color.a;
	}

	protected void update (float percent) {
		float r = startR + (end.r - startR) * percent;
		float g = startG + (end.g - startG) * percent;
		float b = startB + (end.b - startB) * percent;
		float a = startA + (end.a - startA) * percent;
		color.set(r, g, b, a);
	}

	public void reset () {
		super.reset();
		color = null;
	}

	public Color getColor () {
		return color;
	}

	/** Sets the color to modify. If null (the default), the {@link #getActor() actor's} {@link PresentableComponent#color  color} will be
	 * used. */
	public void setColor (Color color) {
		this.color = color;
	}

	public Color getEndColor () {
		return end;
	}

	/** Sets the color to transition to. Required. */
	public void setEndColor (Color color) {
		end.set(color);
	}
}
