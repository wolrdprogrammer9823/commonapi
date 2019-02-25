package tool.common_useage_apis;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FileUtils {

    private FileUtils() {}

    public static void closeIO(Closeable... closeItems) {

        for (Closeable closeItem : closeItems) {
            if (closeItem != null) {
                try {

                    closeItem.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeFileToSdCard(String storageDir, byte[] bugContents) {

        File rootFile = new File(Environment.getExternalStorageDirectory()
                + File.separator + Environment.getExternalStorageDirectory()
                + File.separator + storageDir);

        String strDate = DateUtils.parseDate2Str2(new Date());
        String fileName = strDate + ".txt";

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            if (!rootFile.exists()) {

                rootFile.mkdirs();
            }

            File bugFile = new File(rootFile, fileName);

            if (!bugFile.exists()) {
                try {

                    bugFile.createNewFile();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

            FileOutputStream fos = null;

            try {

                fos = new FileOutputStream(bugFile);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }

            BufferedOutputStream bos = new BufferedOutputStream(fos);
            try {

                bos.write(bugContents, 0, bugContents.length);
            } catch (IOException e) {

                e.printStackTrace();
            }

            try {

                bos.flush();
            } catch (IOException e) {

                e.printStackTrace();
            } finally {

                closeIO(bos);
                closeIO(fos);
            }
        }
    }
}
