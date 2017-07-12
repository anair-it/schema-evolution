# Apache Avro Schema Evolution Demo
Schema evolution is the concept where consumers and producers can work on different data schemas with backwards compatibility built into it. This project demo's how to to schema evolution using Apache Avro. Go through the reference section to get a detailed understanding of how Schema evolution works and some caveats.      
This project uses Avro IDL format to define schemas, which I think is more readable than JSON.


## Pre-requisites
1. Java 8
2. Maven 3.x
3. Git

## Understand the components
1. __pom.xml__: Go through the avro dependencies and maven plugin 
2. __src/main/resources/playlist.avdl__: This is the Avro IDL protocol file
3. __src/main/resources/playlist_v1.txt__: Initial version of playlist schema
4. __src/main/resources/playlist_v2.txt_: Evolved version of playlist schema
5. __org.anair.avro.service.AvroSerde.java__: Generic Avro Serializer and deserializer using the file system
6. __org.anair.avro.main.PlaylisProducerV1.java__: Producer using the initial (v1) version of playlist schema
7. __org.anair.avro.main.PlaylistProducerV2.java__: Producer using the evolved (v2) version of playlist schema
8. __org.anair.avro.main.PlaylistConsumer.java__: Consumer using the evolved (v2) version of playlist schema



## Typical work week
### Day 1 scenario  - New schema
The team gets a new requirement to build a Playlist schema, a Consumer and Producer.

- Copy _playlist_v1.txt_ content to _playlist.avdl_
- `mvn clean generate-sources` to generate Avro Java playlist components at _target/avro-gen_
- Add _target/avro-gen_ to source path
- Go through the generated components and familiarize as required
- Open _org.anair.avro.main.PlaylistProducerV1.java_ and update the constant _PLAYLIST_AVRO_PATH_ with correct file path. DO NOT change the avro file name
- Run _org.anair.avro.main.PlaylistProducerV1.java_ application. This will create playlist object and serialize to an avro file at the path specified above. 
- Open playlist_v1.avro and there will be the producer schema and the binary encoded version of playlist data
- Run _org.anair.avro.main.PlaylistConsumer.java_. This will print the JSON representation of binary data


### Day 2 scenario - Consumer Schema has evolved
Playlist schema has evolved with breaking changes at the Consumer side. Lets look at how the consumer reads V1 avro file.

- Replace _playlist.avdl_ with _playlist_v2.txt_ content
- Open playlist.avdl and follow the changes. Changes are marked.
- Open _org.anair.avro.main.PlaylistProducerV1.java_ and follow __TODO__ statements
- `mvn clean generate-sources` to generate Avro Java playlist V2 components
- Add _target/avro-gen_ to source path
- Run _org.anair.avro.main.PlaylistConsumer.java_. This will print the JSON representation of V1 binary data
- Look at the JSON put put in detail to identify new default attributes and other changes. The consumer is backwards compatible

### Day 3 scenario - New Producer using Playlist V2 schema
The team has to build a new Producer that uses Playlist V2 schema. The consumer should receive and accept data from Producer V1 and Producer V2.

- Open _org.anair.avro.main.PlaylistProducerV2.java_ and follow __TODO__ statements. Familiarize with with attribute changes marked with __//CHANGE__
- Run _org.anair.avro.main.PlaylistProducerV2.java_ application. This will create a playlist object and serialize to an avro file at the path specified above. 
- Open playlist_v2.avro and there will be the producer schema and the binary encoded version of playlist data
- Run _org.anair.avro.main.PlaylistConsumer.java_. This will print the JSON representation of V1 and V2 binary data.


## Reference
- https://avro.apache.org/docs/1.8.2/spec.html#Schema+Resolution
- https://docs.oracle.com/cd/E26161_02/html/GettingStartedGuide/schemaevolution.html
- https://martin.kleppmann.com/2012/12/05/schema-evolution-in-avro-protocol-buffers-thrift.html
