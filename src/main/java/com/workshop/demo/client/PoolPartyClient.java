package com.workshop.demo.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;

@Configuration
@PropertySource("classpath:application.properties")
public class PoolPartyClient {

    private static final Logger LOG = Logger.getLogger("biz.poolparty.cli.client.PoolPartyClient");

    @Value("${poolparty.serverurl}")
    private String serverurl;

    @Value("${poolparty.username}")
    private String username;

    @Value("${poolparty.password}")
    private String password;

    @Value("${poolparty.project}")
    private String project;

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    @Value("${concept}")
    private String concept;

    public String getServerUrl() {
        return serverurl;
    }

    public void setServerUrl(String url) {
        this.serverurl = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    private CloseableHttpClient createClient() {
        // prepare HTTP client, providing credentials for basic auth
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(getUsername(), getPassword()));
        return HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
    }

    // ----------- PoolParty Thesaurus API calls ------------
    public String retrievePoolPartyVersion() throws IOException {
        CloseableHttpClient client = createClient();

        HttpGet getRequest = new HttpGet(getServerUrl() + "/PoolParty/api/version");

        CloseableHttpResponse versionResponse = client.execute(getRequest);

        String responseString = new BasicResponseHandler().handleResponse(versionResponse);

        client.close();

        return responseString;
    }


    public String getConceptDescription() throws IOException {
        CloseableHttpClient client = createClient();

        HttpGet getRequest = new HttpGet(getServerUrl() + "/PoolParty/api/thesaurus/"+getProject()+"/concept?concept="+getConcept()+"&properties=skos:definition");

        CloseableHttpResponse versionResponse = client.execute(getRequest);

        String responseString = new BasicResponseHandler().handleResponse(versionResponse);

        client.close();

        return responseString;
    }
}