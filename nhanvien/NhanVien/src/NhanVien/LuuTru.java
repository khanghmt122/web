package NhanVien;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LuuTru {
    public boolean luuFile(Object obj, String filePath) throws Exception {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(obj);
            oos.flush();
            return true;
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }

    public Object docFile(String filePath) throws Exception {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            return ois.readObject();
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }
}
