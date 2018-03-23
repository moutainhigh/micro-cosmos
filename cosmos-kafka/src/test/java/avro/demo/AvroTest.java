package avro.demo;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by thomas on 2018/3/15 15:22.
 */
public class AvroTest {
    public static void main(String[] args){
        AvroUser user1 = new AvroUser();
        user1.setName("Alyssa")
                .setFavoriteNumber(256);
        // Alternate constructor
        AvroUser user2 = new AvroUser("Ben", 7, "red");

        //Serialize user1, user2 and user3 to disk
        File file = new File("users.avro");
        DatumWriter<AvroUser> userDatumWriter =
                new SpecificDatumWriter<AvroUser>(AvroUser.class);
        DataFileWriter<AvroUser> dataFileWriter =
                new DataFileWriter<AvroUser>(userDatumWriter);
        try {
            dataFileWriter.create(user1.getSchema(), new File("users.avro"));
            dataFileWriter.append(user1);
            dataFileWriter.append(user2);
            dataFileWriter.close();
        } catch (IOException e) {
        }
        //Deserialize Users from dist
        DatumReader<AvroUser> userDatumReader =
                new SpecificDatumReader<AvroUser>(AvroUser.class);
        DataFileReader<AvroUser> dataFileReader = null;
        try {
            dataFileReader = new DataFileReader<AvroUser>(file, userDatumReader);
        } catch (IOException e) {
        }
        AvroUser user = null;
        try {
            while (dataFileReader.hasNext()) {
                // Reuse user object by passing it to next(). This saves
                // us from allocating and garbage collecting many objects for
                // files with many items.
                user = dataFileReader.next(user);
                System.out.println(user);
            }
        } catch (IOException e) {
        }
    }
}
