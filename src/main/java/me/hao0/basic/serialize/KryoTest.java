package me.hao0.basic.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import me.hao0.basic.entities.Person;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Author: haolin
 * Date:   8/12/16
 * Email:  haolin.h0@gmail.com
 */
public class KryoTest {

    @Test
    public void testSerialize() throws FileNotFoundException {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("file.bin"));
        Person p = new Person();
        p.setId(1L);
        p.setUsername("haolin");
        p.setAge(10);
        p.setGrade(1);
        kryo.writeObject(output, p);
        output.close();
    }

    @Test
    public void testDeserialize() throws FileNotFoundException {
        Kryo kryo = new Kryo();
        Input input = new Input(new FileInputStream("file.bin"));
        Person p = kryo.readObject(input, Person.class);
        System.out.println(p);
        input.close();
    }

    @Test
    public void testSerialize2() throws FileNotFoundException {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("file.bin"));
        Person p = new Person();
        p.setId(1L);
        p.setUsername("haolin");
        p.setAge(10);
        //p.setGrade(1);

        CompatibleFieldSerializer serializer = new CompatibleFieldSerializer(kryo, Person.class);

        kryo.writeObject(output, p, serializer);

        output.close();
    }

    @Test
    public void testDerialize2() throws FileNotFoundException {
        Kryo kryo = new Kryo();
        Input input = new Input(new FileInputStream("file.bin"));

        CompatibleFieldSerializer serializer = new CompatibleFieldSerializer(kryo, Person.class);
        Person p = kryo.readObject(input, Person.class, serializer);

        System.out.println(p);
        input.close();
    }
}
