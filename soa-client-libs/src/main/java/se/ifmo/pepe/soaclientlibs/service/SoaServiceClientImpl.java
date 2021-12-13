package se.ifmo.pepe.soaclientlibs.service;

import dto.music_band.response.MusicBandView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class SoaServiceClientImpl implements SoaServiceClient {
    private final RestTemplate restTemplate;

    @Override
    public MusicBandView addSingleToBand(long bandId, String title) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(singlesUrl())
                .buildAndExpand(bandId)
                .toUriString();

        return restTemplate.postForObject(uri, title, MusicBandView.class);
    }

    @Override
    public MusicBandView removeParticipantFromBand(long bandId) {
        return exchangeFor(bandId, participantsUrl(), MusicBandView.class, HttpMethod.DELETE);
    }

    private <T> T exchangeFor(long id, String url, Class<T> responseType, HttpMethod method) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(url)
                .buildAndExpand(id)
                .toUriString();

        return restTemplate.exchange(uri,
                method,
                null,
                responseType).getBody();
    }

    private String root() {
        return "http://localhost:8080/api";
    }

    private String singlesUrl() {
        return root() + "/music-bands/{id}/singles";
    }

    private String participantsUrl() {
        return root() + "/music-bands/{id}/participants";
    }
}
