package se.ifmo.pepe.soaclientlibs.service;

import dto.music_band.response.MusicBandView;
import org.glassfish.jersey.SslConfigurator;

import javax.enterprise.context.RequestScoped;
import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchProviderException;
import java.util.Objects;

import static java.lang.String.format;

@RequestScoped
public class SoaServiceClientImpl implements SoaServiceClient {

    @Override
    public MusicBandView addSingleToBand(long bandId, String title) {
        Invocation.Builder invocationBuilder = getSinglesWebTarget(bandId).request(MediaType.APPLICATION_JSON_TYPE);
        Response response = invocationBuilder.post(Entity.entity(title,
                MediaType.APPLICATION_JSON_TYPE));
        return response.readEntity(MusicBandView.class);
    }

    @Override
    public MusicBandView removeParticipantFromBand(long bandId) {
        Invocation.Builder invocationBuilder = getParticipantsWebTarget(bandId).request(MediaType.APPLICATION_JSON_TYPE);
        Response response = invocationBuilder.delete();
        return response.readEntity(MusicBandView.class);
    }

    private Client client() {
        SslConfigurator sslConfigurator = SslConfigurator.newInstance()
                .keyStoreFile("/Users/aleksey.chaika/IdeaProjects/self/SOA/soa-parent/soa-common/src/main/resources/keystore/soa.p12")
                .keyStorePassword("soasoa")
                .trustStoreFile("/Users/aleksey.chaika/IdeaProjects/self/SOA/soa-parent/soa-common/src/main/resources/keystore/soa.p12")
                .trustStorePassword("soasoa");

        return ClientBuilder.newBuilder()
                .sslContext(sslConfigurator.createSSLContext())
                .hostnameVerifier((hostname, sslSession) -> hostname.equals("localhost"))
                .build();
    }

    private WebTarget getSinglesWebTarget(long bandId) {
        WebTarget webTarget = Objects.requireNonNull(client()).target(root());
        return webTarget.path(format(singlesUrl(), bandId));
    }

    private WebTarget getParticipantsWebTarget(long bandId) {
        WebTarget webTarget = Objects.requireNonNull(client()).target(root());
        return webTarget.path(format(participantsUrl(), bandId));
    }

    private String root() {
        return "https://localhost:8080/api";
    }

    private String singlesUrl() {
        return "/music-bands/%d/singles";
    }

    private String participantsUrl() {
        return "/music-bands/%d/participants";
    }
}
