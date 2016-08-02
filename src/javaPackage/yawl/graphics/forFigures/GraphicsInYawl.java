/**
 * 
 */
package javaPackage.yawl.graphics.forFigures;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.pnml.tools.epnk.gmf.extensions.graphics.GraphicalExtension;
import org.pnml.tools.epnk.gmf.extensions.graphics.IUpdateableFigure;
import org.pnml.tools.epnk.gmf.extensions.graphics.figures.ArcFigure;
import org.pnml.tools.epnk.pnmlcoremodel.Arc;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

import yawl.YawlPackage;



public class GraphicsInYawl extends GraphicalExtension {

	public GraphicsInYawl() {
		super();
	}
	// TODO Auto-generated constructor stub }

	@Override
	public List<EClass> getExtendedNetTypes() {
		ArrayList<EClass> list = new ArrayList<EClass>();
		list.add(YawlPackage.eINSTANCE.getYAWLNet());
		return list;

	}

	@Override
	public List<EClass> getExtendedNetObjects(EClass netType) {
		ArrayList<EClass> list = new ArrayList<EClass>();
		if (netType.equals(YawlPackage.eINSTANCE.getYAWLNet())) {
			list.add(YawlPackage.eINSTANCE.getArc());
		    list.add(YawlPackage.eINSTANCE.getPlace());
			list.add(YawlPackage.eINSTANCE.getTransition());
		}
		return list;
	}

	/*
	 * The method createArcFigure() should return a new instance of the
	 * ArcFigure you implemented in the previous step (with the current arc as
	 * its parameter to the constructor).
	 *
	 * @see org.pnml.tools.epnk.gmf.extensions.graphics.GraphicalExtension#
	 * createArcFigure(org.pnml.tools.epnk.pnmlcoremodel.Arc)
	 */
	@Override
	public ArcFigure createArcFigure(Arc arc) {
		if (arc instanceof yawl.Arc) {
			return new ArcInYawl((yawl.Arc) arc);
		}
		return null;
	}

	@Override
	public IUpdateableFigure createPlaceFigure(Place place) {
		if (place instanceof yawl.Place) {
			return new ConditionInYawl((yawl.Place) place);
		}
		return null;
	}

	@Override
	public IUpdateableFigure createTransitionFigure(Transition transition) {
		if (transition instanceof yawl.Transition) {
			return new TransitionInYawl((yawl.Transition) transition);
		}
		return null;
	}
}
