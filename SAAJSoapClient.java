import javax.xml.soap.*;

public class SAAJSoapClient {
    public static void main(String args[]) throws Exception {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        String wsdlURL = "http://www.webservicex.net/geoipservice.asmx?WSDL";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), wsdlURL);

        // print SOAP Response
        System.out.print("Response SOAP Message:");
        soapResponse.writeTo(System.out);

        soapConnection.close();
    }

    /**
     * Creat SOAP Request message
     * 
     *  Construct SOAP Request Message:
        <soapenv:envelope xmlns:web="http://www.webservicex.net/" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
               <soapenv:header></soapenv:header>
               <soapenv:body>
                      <web:getgeoip>
                         <web:ipaddress>172.168.0.45</web:ipaddress>
                      </web:getgeoip>
               </soapenv:body>
        </soapenv:envelope>
     * @return
     * @throws Exception
     */
    private static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://www.webservicex.net/";

        /** 
         * Construct SOAP Envelope
         * <soapenv:envelope xmlns:web="http://www.webservicex.net/" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"></soapenv:envelope>
         *  
         */
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("web", serverURI);

        /**
         * Construct SOAP Body 
         * 
         * <soapenv:body>
                      <web:getgeoip>
                         <web:ipaddress>172.168.0.45</web:ipaddress>
                      </web:getgeoip>
               </soapenv:body>
         *  
         */
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("GetGeoIP", "web");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("IPAddress", "web");
        soapBodyElem1.addTextNode("172.168.0.45");

        /**
         * Construct SOAP Header
         * 
         * <soapenv:header></soapenv:header>
         */
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "GetGeoIP");

            soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }
}
