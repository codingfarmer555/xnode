package com.chaoxing.websourceviewer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static String getStringFromStream(InputStream inputStream) {
        ByteArrayOutputStream baso = new ByteArrayOutputStream();
        int len = -1;
        byte[] buffer = new byte[1024];

        try {
            while (!((len = inputStream.read(buffer)) != -1)) {
                baso.write(buffer, 0, len);
                byte[] bytes = baso.toByteArray();
                return new String(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
        return null;

    }

}

