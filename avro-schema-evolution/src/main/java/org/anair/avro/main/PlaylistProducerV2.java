package org.anair.avro.main;

import org.anair.avro.service.AvroSerde;
import org.anair.playlist.Artist;
import org.anair.playlist.Playlist;
import org.anair.playlist.Song;

//TODO 1: Uncomment below line
//import org.anair.playlist.Type;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import java.io.File;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Serialize Playlist V2 to file "playlist_v2.avro"
 *
 */
public class PlaylistProducerV2 {
    private final static Logger LOG = LoggerFactory.getLogger(PlaylistProducerV2.class);
    static final String AVRO_FILENAME = "playlist_v2.avro";


    public static void main(String[] args) throws Exception{
        deleteFile();

        AvroSerde<Playlist> serde = new AvroSerde<>();
        Playlist playlist = null;

        //TODO 2: Uncomment below line
        //playlist = PlaylistProducerV2.playlistBuilder();

        serde.serialize(playlist, PlaylistProducerV1.PLAYLIST_AVRO_PATH+AVRO_FILENAME);

        LOG.info("Serialized playlist to file at : {}", PlaylistProducerV1.PLAYLIST_AVRO_PATH+AVRO_FILENAME);
    }

    private static void deleteFile() {
        File file = FileUtils.getFile(PlaylistProducerV1.PLAYLIST_AVRO_PATH+AVRO_FILENAME);
        if(file.exists()){
            FileUtils.deleteQuietly(file);
        }
    }

    //TODO 3: Uncomment method
    /*static Playlist playlistBuilder(){
        Artist artist =  new Artist();
        artist.setName("Chris Martin");

        Song song1 = Song.newBuilder()
                .setArtist(artist)
                .setName("Fix You") //CHANGE
                .setType(Type.POP)  //CHANGE
                .setReleaseDate(LocalDate.now().toEpochDay())
                .setLanguage("Spanish")
                .build();

        Song song2 = new Song();
        song2.put("artist", artist);
        song2.put("name", "Hymn for the weekend");  //CHANGE
        song2.put("type", Type.POP);  //CHANGE
        song2.put("language", "English");
        song2.put("releaseDate", LocalDate.now().toEpochDay());

        Playlist pl = Playlist.newBuilder()
                    .setName("Coldplay songs")
                    .setSongs(Arrays.asList(song1, song2))
                    .build();

        return pl;
    }*/
}
