package org.tia.openness.model.util;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;

public class TiaOpennesExtendedMetadata extends BasicExtendedMetaData {

	public TiaOpennesExtendedMetadata(EPackage.Registry registry) {
		super(registry);
	}

	public TiaOpennesExtendedMetadata(String annotationURI, EPackage.Registry registry) {
		super(annotationURI, registry);
	}

	protected boolean isFeatureNamespaceMatchingLax() {
		return true;
	}
}
