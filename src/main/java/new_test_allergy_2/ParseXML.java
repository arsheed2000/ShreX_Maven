package new_test_allergy_2;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParseXML {

    public static void main(String[] args) {
        try {
            // Path to the XML file
            File xmlFile = new File("src/main/XMLData/Fhir_test/XML_Data/AllergyIntolerance-example.xml");

            // Create a DocumentBuilderFactory and DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parse the XML file and obtain the Document
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Extract data from the XML with null checks
            String clinicalStatus = getElementValue(doc, "clinicalStatus", "code");
            String verificationStatus = getElementValue(doc, "verificationStatus", "code");
            String category = getElementValue(doc, "category", null);
            String criticality = getElementValue(doc, "criticality", null);

            // Extract code details
            String code = getElementValue(doc, "code", "code");
            String display = getElementValue(doc, "code", "display");
            String codeText = getElementValue(doc, "code", "text");

            // Extract patient details
            String patientReference = getElementValue(doc, "patient", "reference");
            String patientDisplay = getElementValue(doc, "patient", "display");

            // Extract reaction details
            String reactionSeverity = getElementValue(doc, "reaction", "severity");
            String manifestationCode = getElementValue(doc, "reaction", "manifestation", "code");
            String manifestationDisplay = getElementValue(doc, "reaction", "manifestation", "display");
            String manifestationText = getElementValue(doc, "reaction", "manifestation", "text");

            // Insert data into the database using DatabaseHelper
            DatabaseHelper.insertAllergyIntolerance(clinicalStatus, verificationStatus, category, criticality,
                    code, display, codeText, patientReference, patientDisplay,
                    reactionSeverity, manifestationCode, manifestationDisplay, manifestationText);

            System.out.println("Data inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to extract element values from XML
    private static String getElementValue(Document doc, String parentTag, String childTag) {
        NodeList nodeList = doc.getElementsByTagName(parentTag);
        if (nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            if (childTag != null) {
                NodeList childNodeList = element.getElementsByTagName(childTag);
                if (childNodeList.getLength() > 0) {
                    Node childNode = childNodeList.item(0);
                    if (childNode != null && childNode.getAttributes() != null) {
                        Node valueNode = childNode.getAttributes().getNamedItem("value");
                        if (valueNode != null) {
                            return valueNode.getNodeValue();
                        }
                    }
                }
            } else if (element.getAttributes() != null) {
                Node valueNode = element.getAttributes().getNamedItem("value");
                if (valueNode != null) {
                    return valueNode.getNodeValue();
                }
            }
        }
        return null;
    }


    // Overloaded helper method for nested elements
    private static String getElementValue(Document doc, String parentTag, String subParentTag, String childTag) {
        NodeList nodeList = doc.getElementsByTagName(parentTag);
        if (nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            NodeList subParentNodeList = element.getElementsByTagName(subParentTag);
            if (subParentNodeList.getLength() > 0) {
                Element subParentElement = (Element) subParentNodeList.item(0);
                NodeList childNodeList = subParentElement.getElementsByTagName(childTag);
                if (childNodeList.getLength() > 0) {
                    Node childNode = childNodeList.item(0);
                    if (childNode != null && childNode.getAttributes() != null) {
                        Node valueNode = childNode.getAttributes().getNamedItem("value");
                        if (valueNode != null) {
                            return valueNode.getNodeValue();
                        }
                    }
                }
            }
        }
        return null;
    }
}
