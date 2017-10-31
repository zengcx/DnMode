package Soap;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.WebServiceFeature;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
/**
 * 
 * @author ZENGCX
 * date 2017-7-30ÏÂÎç02:51:30
 * (c)copyright seahigh 2017
 */
public class Soaputil {
	public SOAPPart initsoappart() throws SOAPException {

		SOAPMessage soapmessage = MessageFactory.newInstance().createMessage();

		SOAPPart soappart = soapmessage.getSOAPPart();

		SOAPEnvelope soapenvelope = soappart.getEnvelope();
		SOAPHeader soapheader = soapenvelope.getHeader();
		SOAPElement cwmp = soapenvelope.addNamespaceDeclaration("cwmp",
				"urn:dslforum-org:cwmp-1-0");
		SOAPElement xsi = soapenvelope.addNamespaceDeclaration("xsi",
				"http://www.w3.org/2001/xmlschema-instance");
		SOAPElement xsd = soapenvelope.addNamespaceDeclaration("xsd",
				"http://www.w3.org/2001/xmlschema");

		SOAPElement enc = soapenvelope.addNamespaceDeclaration("soap-enc",
				"http://schemas.xmlsoap.org/soap/encoding/");

		SOAPElement id = soapheader.addChildElement("id", "cwmp");
		id.setTextContent("1");
		return soappart;
	}

	public void soap2string(Source source) throws Exception {
		if (source != null) {
			Node root = null;
			if (source instanceof DOMSource) {
				root = ((DOMSource) source).getNode();
			} else if (source instanceof SAXSource) {
				InputSource insource = ((SAXSource) source).getInputSource();
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				dbf.setNamespaceAware(true);
				Document doc = dbf.newDocumentBuilder().parse(insource);
				root = (Node) doc.getDocumentElement();
			}
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(root), new StreamResult(
					System.out));
		}
	}

	public Source informresponse(SOAPPart part) throws Exception {
		SOAPEnvelope soapenvelope = part.getEnvelope();
		SOAPBody soapbody = soapenvelope.getBody();
		SOAPElement informres = soapbody.addChildElement("informresponse",
				"cwmp");
		SOAPElement max = SOAPFactory.newInstance().createElement(
				"maxenvelopes", "", "");
		max.setTextContent("1");
		informres.addChildElement(max);
		return part.getContent();
	}

	public Source getparametervalues(SOAPPart part, List list) throws Exception {
		SOAPEnvelope soapenvelope = part.getEnvelope();
		SOAPBody soapbody = soapenvelope.getBody();
		SOAPElement informres = soapbody.addChildElement("getparametervalues",
				"cwmp");
		SOAPFactory soapfactory = SOAPFactory.newInstance();
		SOAPElement names = soapfactory.createElement("parameternames", "", "");
		names.addAttribute(new QName("soap-enc:arraytype"), "xsd:string["
				+ list.size() + "]");
		SOAPElement nameelement = null;
		for (int i = 0; i < list.size(); i++) {
			nameelement = soapfactory.createElement("string", "", "");
			nameelement.setTextContent((String) list.get(i));
			names.addChildElement(nameelement);
		}
		informres.addChildElement(names);
		return part.getContent();
	}

	public static void main(String[] args) throws Exception {
		Soaputil util = new Soaputil();
		SOAPPart part = util.initsoappart();
		Source inform = util.getparametervalues(part, util.gettestnames());
		util.soap2string(inform);
	}

	public List<String> gettestnames() {
		List<String> list = new ArrayList<String>();
		list.add("internetgatewaydevice.deviceinfo.x_ct-com_cpu");
		list.add("internetgatewaydevice.deviceinfo.x_ct-com_worktime");
		list.add("internetgatewaydevice.deviceinfo.softwareversion");
		list.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.ssid");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_receivenoise");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_lan-totalbytesreceived");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_lan-totalbytessent");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_wan-totalbytesreceived");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_wan-totalbytessent");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_lan-totalpacketsreceived");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_lan-totalpacketssent");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_wan-totalpacketsreceived");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_wan-totalpacketssent");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_stat.responsepass");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_stat.askpass");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_stat.successpass");
		list.add("internetgatewaydevice.deviceinfo.x_ct-com_temp");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_stat.lan-packetserror");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_stat.lan-totalbytesreceived");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_stat.lan-totalbytessent");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_stat.wan-totalbytesreceived");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_stat.wan-packetserror");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.x_ct-com_stat.wan-totalbytessent");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.associateddevice.1.x_ct-com_receiverate");
		list
				.add("internetgatewaydevice.landevice.1.wlanconfiguration.1.associateddevice.1.x_ct-com_sendrate");
		list.add("internetgatewaydevice.deviceinfo.serialnumber");
		list.add("internetgatewaydevice.deviceinfo.manufactureroui");
		return list;
	}
}