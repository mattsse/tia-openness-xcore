package org.tia.openness.model.serde;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;

public class TiaOpennessXMLHelper extends XMLHelperImpl {

	/**
	 * @see http://tweakeclipse.blogspot.nl/2008/10/save-emf-resource-with-or-without.html
	 */
	protected String getQName(EPackage pkg, String name, boolean mustHavePrefix) {
		return super.getQName(pkg, name, false);
	}

	@Override
	public EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name) {
		return super.getFeature(eClass, namespaceURI, name);
	}

	@Override
	public EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name, boolean isElement) {
		return super.getFeature(eClass, namespaceURI, name, isElement);
	}
}
