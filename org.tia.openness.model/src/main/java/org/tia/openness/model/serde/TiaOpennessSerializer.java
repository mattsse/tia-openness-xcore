package org.tia.openness.model.serde;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.tia.openness.model.openness.TiaOpennessPackage;
import org.tia.openness.model.openness.util.TiaOpennessXMLProcessor;
import org.tia.openness.model.util.TiaOpennesExtendedMetadata;
import org.tia.openness.model.util.TiaOpennessResourceImpl2;
import org.tia.openness.model.util.TiaOpennessXMLProcessor2;

public class TiaOpennessSerializer {

	/**
	 * Saves the xmi modelFile to an XML file. Loads the resource from the xmlFile
	 * first and then saves it as xmi
	 * 
	 * @param xmlFile
	 * @param resourceSet
	 * @param modelFile
	 * @throws IOException
	 */
	public void writeXMI(File xmlFile, ResourceSet resourceSet, String modelFile) throws IOException {
		Resource resource = loadXMLResource(xmlFile);
		URI modelURI = URI.createFileURI(new File(modelFile).getAbsolutePath());
		Resource xmiResource = resourceSet.createResource(modelURI);
		xmiResource.getContents().add(EcoreUtil.copy(resource.getContents().get(0).eContents().get(0)));
		xmiResource.save(null);
	}

	/**
	 * Loads an Xml file to an emf resource
	 * 
	 * @param xmlFile
	 * @return
	 * @throws IOException
	 */
	public Resource loadXMLResource(File xmlFile) throws IOException {
		FileInputStream stream = new FileInputStream(xmlFile);
		TiaOpennessXMLProcessor2 processor = new TiaOpennessXMLProcessor2();
		final ExtendedMetaData extendedMetaData = new TiaOpennesExtendedMetadata(processor.getEPackageRegistry());
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		Resource resource = processor.load(stream, options);
		return resource;
	}

	/**
	 * Loads an Xmi file to an emf resource
	 * 
	 * @param xmiFile
	 * @return
	 * @throws IOException
	 */
	public Resource loadXMIResource(File xmiFile) throws IOException {
		final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		final Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put(TiaOpennessPackage.eINSTANCE.getName(), new XMIResourceFactoryImpl());
		m.put("xmi", new XMIResourceFactoryImpl());
		m.put("*", new XMIResourceFactoryImpl());
		final ResourceSet resSet = new ResourceSetImpl();
		Resource resource = resSet.getResource(URI.createFileURI(xmiFile.getAbsolutePath()), true);
		return resource;
	}

	/**
	 * Saves an emf resource model to an Xml file
	 * 
	 * @param xmiResource
	 * @param xmlFile
	 * @throws IOException
	 */
	public void writeXML(Resource xmiResource, String xmlFile) throws IOException {
		// create the resource set and register the metamodel packages
		xmiResource.getResourceSet().getPackageRegistry().put(TiaOpennessPackage.eINSTANCE.getNsURI(),
				TiaOpennessPackage.eINSTANCE);
		final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(
				xmiResource.getResourceSet().getPackageRegistry());
		// put metadata options
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		URI xmlURI = URI.createFileURI(new File(xmlFile).getAbsolutePath());
		TiaOpennessResourceImpl2 resource = new TiaOpennessResourceImpl2(xmiResource.getURI());
		resource.getContents().add(EcoreUtil.copy(xmiResource.getContents().get(0)));
		resource.setURI(xmlURI);
		resource.save(options);
	}

}
