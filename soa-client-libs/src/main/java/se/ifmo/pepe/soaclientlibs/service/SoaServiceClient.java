package se.ifmo.pepe.soaclientlibs.service;

import dto.music_band.response.MusicBandView;

public interface SoaServiceClient {
    public MusicBandView addSingleToBand(long bandId, String title);
    MusicBandView removeParticipantFromBand(long bandId);
}
