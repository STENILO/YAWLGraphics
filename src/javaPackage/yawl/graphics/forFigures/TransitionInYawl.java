/**
 * 
 */
package javaPackage.yawl.graphics.forFigures;

import java.math.BigInteger;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.util.Enumerator;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.TransitionFigure;

import yawl.Transition;
import yawl.TypeOfT;
import yawl.TypeOfTransition;

/**
 * @author Konstantin
 * @author Martin
 * @author Thomas
 *
 * @generated NOT
 */

public class TransitionInYawl extends TransitionFigure {
	private enum Type {
		AND, XOR, OR
	}

	private Type splittype;
	private Type jointype;

	public TransitionInYawl(Transition action) {
		super(action);
		splittype = getSplitType();
		jointype = getJoinType();

	}

	@Override
	public void update() {

		Type oldJoinType = jointype;
		Type oldSplitType = splittype;

		splittype = getSplitType();
		jointype = getJoinType();

		if ((oldSplitType != splittype) || (oldJoinType != jointype)) {
			this.repaint();
		}

	}

	private Type getJoinType() {
		if (this.transition instanceof Transition) {
			TypeOfTransition TypeOfTransition = ((Transition) transition).getTypeOfJoin();
			if (TypeOfTransition != null) {
				switch (TypeOfTransition.getText()) {
				case AND:
					return Type.AND;
				case OR:
					return Type.OR;
				case XOR:
					return Type.XOR;
				}
			}
		}

		return Type.AND;

	}

	private Type getSplitType() {
		TypeOfTransition type = (yawl.TypeOfTransition) ((Transition) transition).getTypeOfSplit();
		if (type != null) {
			switch (type.getText()) {
			case AND:
				return Type.AND;
			case OR:
				return Type.OR;
			case XOR:
				return Type.XOR;
			}
		}
		return Type.AND;
	}

	@Override
	protected void fillShape(Graphics graphics) {
		super.fillShape(graphics);
		org.eclipse.draw2d.geometry.Rectangle rectangle = this.getClientArea();
		int cx = rectangle.x + rectangle.width / 2;
		int cy = rectangle.y + rectangle.height / 2;
		int width = rectangle.width;
		int height = rectangle.height;
		int offSetX = width/5;
		if (transition instanceof Transition) {

			graphics.drawLine(new Point(cx-offSetX/2,rectangle.getTopLeft().y), 
					new Point(cx-offSetX/2,rectangle.getBottomLeft().y));
			graphics.setBackgroundColor(getForegroundColor());
			if (jointype == Type.AND) {

				
				// Set up AND join figure
				
				int[] cornerPoints = {rectangle.getTopLeft().x(),rectangle.getTopLeft().y(),
						cx-offSetX/2,cy,
						rectangle.getBottomLeft().x(),rectangle.getBottomLeft().y()};
				graphics.drawPolygon(cornerPoints);
				graphics.drawPolygon(cornerPoints);
				

			} else if (jointype == Type.OR) {
				// Set up OR split figure
				
				int[] cornerPoints1 = {rectangle.getTopLeft().x(), cy,
						rectangle.x+offSetX, rectangle.getTopLeft().y(),
						cx-offSetX/2, cy,
						rectangle.x+offSetX, rectangle.getBottomLeft().y()};
				graphics.drawPolygon(cornerPoints1);
				
			} else if (jointype == Type.XOR) {

				// Set up XOR join figure
				
				int[] cornerPoints2 = {cx-offSetX/2,rectangle.getTopLeft().y(),
						rectangle.getTopLeft().x(),cy,
						cx-offSetX/2,rectangle.getBottomLeft().y()};
				graphics.drawPolygon(cornerPoints2);
				
				

			}
		

			graphics.drawLine(new Point(cx+offSetX/2,rectangle.getTopLeft().y), 	
					new Point(cx+offSetX/2,rectangle.getBottomLeft().y));
			
			if (splittype == Type.AND) {
				// Set up AND split figure
				
				int[] cornerPoints = {rectangle.getTopRight().x(),rectangle.getTopRight().y(),
						cx+offSetX/2,cy,
						rectangle.getBottomRight().x(),rectangle.getBottomRight().y()};
				graphics.drawPolygon(cornerPoints);
				

			} else if (splittype == Type.OR) {
				// Set up OR join figure
				
				int[] cornerPoints1 = {rectangle.getTopRight().x(), cy,
						rectangle.x+width-offSetX, rectangle.getTopRight().y(),
						cx+offSetX/2, cy,
						rectangle.x+width-offSetX, rectangle.getBottomRight().y()};
				graphics.drawPolygon(cornerPoints1);
			} else if (splittype == Type.XOR) {
				
				// Set up XOR split figure
				int[] cornerPoints2 = {cx+offSetX/2,rectangle.getTopRight().y(),
						rectangle.getBottomRight().x,cy,
						cx+offSetX/2,rectangle.getBottomRight().y()};
				graphics.drawPolygon(cornerPoints2);
				

			}

		}

	}

}
