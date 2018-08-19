package com.springboot.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MyWebServerFactoryCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        TomcatServletWebServerFactory  tomcat = (TomcatServletWebServerFactory)server;
        tomcat.setPort(9000);
        tomcat.setBaseDirectory(new File("/Users/anfu"));
//        tomcat.setContextValves();
        tomcat.addConnectorCustomizers(new MyTomcatContextCustomizer());
        System.out.println(server.getClass());
    }

}


class MyTomcatContextCustomizer  implements TomcatConnectorCustomizer {


    @Override
    public void customize(Connector connector) {
        Http11NioProtocol http = (Http11NioProtocol) connector.getProtocolHandler();
        http.setMaxConnections(2000);//最大连接数
        http.setPollerThreadCount(300);//最大线程
    }
}