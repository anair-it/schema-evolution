package org.anair.avro.main;

import org.anair.avro.service.AvroSerde;
import org.anair.playlist.Artist;

//TODO 1: Comment below line
import org.anair.playlist.Genre;
import org.anair.playlist.Playlist;
import org.anair.playlist.Song;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

/**
 * Serialize Playlist V1 to file "playlist_v1.avro"
 *
 */
public class PlaylistProducerV1 {
    private final static Logger LOG = LoggerFactory.getLogger(PlaylistProducerV1.class);

    static final String AVRO_FILENAME = "playlist_v1.avro";

    static final String PLAYLIST_AVRO_PATH = "/home/vagrant/";

    public static void main(String[] args) throws Exception{
        deleteFile();

        AvroSerde<Playlist> serde = new AvroSerde<>();
        Playlist playlist = null;

        //TODO 2: Comment below line
        playlist = PlaylistProducerV1.playlistBuilder();

        serde.serialize(playlist, PLAYLIST_AVRO_PATH+AVRO_FILENAME);

        LOG.info("Serialized playlist to file at : {}", PLAYLIST_AVRO_PATH+AVRO_FILENAME);
    }

    private static void deleteFile() {
        File file = FileUtils.getFile(PLAYLIST_AVRO_PATH+AVRO_FILENAME);
        if(file.exists()){
            FileUtils.deleteQuietly(file);
        }

        file = FileUtils.getFile(PLAYLIST_AVRO_PATH+PlaylistProducerV2.AVRO_FILENAME);
        if(file.exists()){
            FileUtils.deleteQuietly(file);
        }
    }

    //TODO 3: Comment method
    static Playlist playlistBuilder(){
        Artist artist =  new Artist();
        artist.setName("Chris Martin");

        Song song1 = Song.newBuilder()
                .setArtist(artist)
                .setDuration(5)
                .setTitle("Fix You")
                .setGenre(Genre.POP)
                .build();

        Song song2 = new Song();
        song2.put("artist", artist);
        song2.put("title", "Hymn for the weekend");
        song2.put("genre", Genre.POP);

        Playlist pl = Playlist.newBuilder()
                    .setName("Coldplay songs")
                    .setSongs(Arrays.asList(song1, song2))
                    .build();

        return pl;
    }
}
