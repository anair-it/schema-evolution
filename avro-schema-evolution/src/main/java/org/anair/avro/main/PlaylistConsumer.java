package org.anair.avro.main;

import org.anair.avro.service.AvroSerde;
import org.anair.playlist.Playlist;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class PlaylistConsumer {

    public static void main(String[] args) throws Exception{
        AvroSerde<Playlist> serde = new AvroSerde<>();
        File file = null;

        //Deserialize playlist_v1.avro
        file = FileUtils.getFile(PlaylistProducerV1.PLAYLIST_AVRO_PATH+PlaylistProducerV1.AVRO_FILENAME);
        if(file.exists()) {
            serde.deserialize(Playlist.class, file);
        }

        //Deserialize playlist_v2.avro
        file = FileUtils.getFile(PlaylistProducerV1.PLAYLIST_AVRO_PATH+PlaylistProducerV2.AVRO_FILENAME);
        if(file.exists()) {
            serde.deserialize(Playlist.class, file);
        }
    }
}
