# saaj-soap-client
Consume a SOAP WS using SAAJ framework.

From my blog: http://blogs.niteshapte.com/2014-07-20-how-to-create-a-soap-client-in-java-using-saaj.htm

# What is SAAJ?

    SOAP with Attachments API for Java (SAAJ) is mainly used for dealing directly with SOAP Request/Response messages which happens behind the scenes in any Web Service API. It allows the developers to directly send and receive soap messages instead of using JAX-WS.

To know more about SAAJ framework, please check official documentation – http://docs.oracle.com/javaee/5/tutorial/doc/bnbhg.html

I am going to use an open service available at http://www.webservicex.net/. We will be calling Geo IP method. This method is for fetching the country name and its code based on the IP address supplied as input. . You can get the complete WSDL here – http://www.webservicex.net/geoipservice.asmx?WSDL.

First of all, you need to build the SOAP request message that we need to send. To know how the SOAP request should like, you can used Soap UI tool. The required SOAP request message for GeoIP looks like below:

    <soapenv:envelope xmlns:web="http://www.webservicex.net/" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
       <soapenv:header> </soapenv:header>
       <soapenv:body>
          <web:getgeoip>        
             <web:ipaddress>172.168.0.45</web:ipaddress>
          </web:getgeoip>
       </soapenv:body>
    </soapenv:envelope>

Now let’s see how you need to construct this message using SAAJ framework and send the request: SAAJSoapClient.java
SAAJSoapClient.java is in repository. :D

Execute it. You will see SOAP response message in your console.

    <soap:envelope xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <soap:body>
          <getgeoipresponse xmlns="http://www.webservicex.net/">
             <getgeoipresult>
                <returncode>1</returncode>
                <ip>172.168.0.45</ip>
                <returncodedetails>Success</returncodedetails>
                <countryname>United States</countryname>
                <countrycode>USA</countrycode>
             </getgeoipresult>
          </getgeoipresponse>
       </soap:body>
    </soap:envelope>

That is it. Easy isn’t it? Hope you guys likes it.
