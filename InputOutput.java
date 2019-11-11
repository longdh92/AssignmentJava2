/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Lien
 */
public class InputOutput {

    public static Object readObject(String path) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Object object = ois.readObject();
            ois.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public static void writeObject(String path, Object object) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path, false));
            oos.writeObject(object);
            oos.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
