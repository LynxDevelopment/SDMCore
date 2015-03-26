package com.lynxspa.coac.plannings.ftpprocess.nodes;

import java.util.Properties;

/**
 * Class to set an http and ftp proxy, or a socks server or reset the server settings.
 * @see <a href="http://java.sun.com/j2se/1.5.0/docs/guide/net/properties.html">
 *  java 1.5 network property list</a>
 * @author joseluis.llorente
 *
 */
public class StablishProxy {
    protected String proxyHost = null;
    protected int proxyPort = 80;
    private String socksProxyHost = null;
    private int socksProxyPort = 1080;
    private String proxyUser = null;
    private String proxyPassword = null;
    
    public void setProxyHost(String hostname) {
        proxyHost = hostname;
    }
    
    public void setProxyPort(int port) {
        proxyPort = port;
    }

    public void setSocksProxyHost(String host) {
        this.socksProxyHost = host;
    }

    public void setSocksProxyPort(int port) {
        this.socksProxyPort = port;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }


    private void setProxyProperties() {
        Properties props = System.getProperties();
        if (proxyHost != null) {
            if (proxyHost.length() != 0) {
                props.put("http.proxyHost", proxyHost);
                String portString = Integer.toString(proxyPort);
                props.put("http.proxyPort", portString);
                props.put("https.proxyHost", proxyHost);
                props.put("https.proxyPort", portString);
                props.put("ftp.proxyHost", proxyHost);
                props.put("ftp.proxyPort", portString);
                if (proxyUser != null) {
                    props.put("http.proxyUser", proxyUser);
                    props.put("http.proxyPassword", proxyPassword);
                }
            } else {
               
                props.remove("http.proxyHost");
                props.remove("http.proxyPort");
                props.remove("http.proxyUser");
                props.remove("http.proxyPassword");
                props.remove("https.proxyHost");
                props.remove("https.proxyPort");
                props.remove("ftp.proxyHost");
                props.remove("ftp.proxyPort");
            }
        }

        //socks
        if (socksProxyHost != null) {
            if (socksProxyHost.length() != 0) {
                props.put("socksProxyHost", socksProxyHost);
                props.put("socksProxyPort", Integer.toString(socksProxyPort));
                if (proxyUser != null) {
                    props.put("java.net.socks.username", proxyUser);
                    props.put("java.net.socks.password", proxyPassword);
                }
            } else {
                props.remove("socksProxyHost");
                props.remove("socksProxyPort");
                props.remove("java.net.socks.username");
                props.remove("java.net.socks.password");
            }
        }
    }

    public void stablish() throws Exception {
        setProxyProperties();
    }

}
