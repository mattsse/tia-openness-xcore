package org.tia.openness.model.serde

import java.io.File
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.tia.openness.model.openness.Section
import org.tia.openness.model.openness.util.TiaOpennessResourceFactoryImpl
import org.tia.openness.model.openness.Member

class TiaSerdeTest {

	ResourceSet resourceSet

	@Before
	public def void setUp() {
		resourceSet = new ResourceSetImpl

		ResourceFactoryRegistryImpl.INSTANCE.getExtensionToFactoryMap().put("xml", new TiaOpennessResourceFactoryImpl);
		ResourceFactoryRegistryImpl.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl);
	}

	@Test
	public def void loadXmlTest() {
		val xmlFile = new File("samples/Main_OB.xml")
		val serializer = new TiaOpennessSerializer
		val resource = serializer.loadXMLResource(xmlFile)
		resource.contents.head.eAllContents.filter(Member).forEach[p|println(p.datatype)]
		Assert.assertNotNull(resource)
	}
//	
//		@Test
//	public void serializeTest() throws IOException {
//		File xmlFile = new File("examples/minimal.xml");
//		PlcOpenSerializer serializer = new PlcOpenSerializer();
//		serializer.writeXMI(xmlFile, resourceSet, "generated/demo_xmi.xmi");
//		Resource resource = serializer.loadXMIResource(new File("generated/demo_xmi.xmi"));
//		Assert.assertNotNull(resource);
//		resource.save(null);
//	}
//
//	@Test
//	public void roundtripTest() throws IOException {
//		File xmlFile = new File("examples/minimal.xml");
//		PlcOpenSerializer serializer = new PlcOpenSerializer();
//		serializer.writeXMI(xmlFile, resourceSet, "generated/demo_xmi.xmi");
//		URI modelURI = URI.createFileURI(new File("generated/demo_xmi.xmi").getAbsolutePath());
//		serializer.writeXML(resourceSet.getResource(modelURI, true), "generated/demo_generated.xml");
//	}
}
