import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Nettosphere;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class NettoSsl {

  public static void main(String[] args) {
    try {

      System.out.println("hi");

      Config.Builder configBuilder = new Config.Builder();
      configBuilder.port(8080);

      boolean ssl = true;
      if (ssl) {
        SelfSignedCertificate ssc = new SelfSignedCertificate("localhost");
        SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey(), null).build();
        configBuilder.sslContext(sslCtx);
      }

      configBuilder.resource("./");
      Nettosphere nettosphere = new Nettosphere.Builder().config(configBuilder.build()).build();
      nettosphere.start();

    } catch (Exception e) {
      System.out.println("main threw");
      e.printStackTrace();
    }

  }

}
