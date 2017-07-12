package org.anair.avro.service;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class AvroSerde<S extends SpecificRecordBase> {

    private final static Logger LOG = LoggerFactory.getLogger(AvroSerde.class);

    public void serialize(S data, String outputFilePath) throws Exception {
        DatumWriter<S> playlistDatumWriter = new SpecificDatumWriter<>(data.getSchema());
        DataFileWriter<S> dataFileWriter = new DataFileWriter<>(playlistDatumWriter);

        dataFileWriter.create(data.getSchema(), new File(outputFilePath));
        dataFileWriter.append(data);
        dataFileWriter.close();
    }

    public void deserialize(Class<S> targetType, File file) throws Exception {
        DatumReader<S> datumReader =
                new SpecificDatumReader<>(targetType.newInstance().getSchema());

        DataFileReader<S> dataFileReader = new DataFileReader<>(file, datumReader);
        S data = null;
        while (dataFileReader.hasNext()) {
            data = dataFileReader.next(data);
            LOG.info(data.toString());
        }
    }
}
