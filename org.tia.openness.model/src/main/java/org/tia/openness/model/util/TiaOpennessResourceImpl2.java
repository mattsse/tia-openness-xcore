package org.tia.openness.model.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.tia.openness.model.serde.TiaOpennessXMLHelper;

public class TiaOpennessResourceImpl2 extends XMLResourceImpl {

	public TiaOpennessResourceImpl2(URI uri) {
		super(uri);
	}

	protected XMLHelper createXMLHelper() {
		return new TiaOpennessXMLHelper();
	}

}
