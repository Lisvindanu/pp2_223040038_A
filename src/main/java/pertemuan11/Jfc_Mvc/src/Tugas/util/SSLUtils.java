package main.java.pertemuan11.Jfc_Mvc.src.Tugas.util;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.swing.ImageIcon;

public class SSLUtils {
    public static void disableSSLVerification() {
        try {
            // Create a trust manager that trusts all certificates
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            // Create SSL context and initialize it with the trust-all manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            // Set the default SSL socket factory
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create and set a hostname verifier that trusts all hostnames
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage loadImageSecurely(String imageUrl) throws Exception {
        if (!imageUrl.startsWith("http")) {
            imageUrl = "https://virtual-realm.my.id" + imageUrl;
        }

        // Create URL connection with proper timeouts
        URL url = new URL(imageUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        // Use try-with-resources to properly handle the input stream
        try (InputStream in = conn.getInputStream()) {
            return ImageIO.read(in);
        }
    }
}