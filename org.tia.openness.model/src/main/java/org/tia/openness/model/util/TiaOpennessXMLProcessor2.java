package org.tia.openness.model.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.tia.openness.model.openness.TiaOpennessPackage;

public class TiaOpennessXMLProcessor2 extends XMLProcessor {

	public TiaOpennessXMLProcessor2() {
		super(new EPackageRegistryImpl(EPackage.Registry.INSTANCE));
		extendedMetaData.putPackage(null, TiaOpennessPackage.eINSTANCE);
		extendedMetaData.putPackage("http://www.siemens.com/automation/Openness/SW/Interface/v2",
				TiaOpennessPackage.eINSTANCE);
	}

	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new TiaOpennessResourceFactoryImpl2());
			registrations.put(STAR_EXTENSION, new TiaOpennessResourceFactoryImpl2());
		}
		return registrations;
	}

}
