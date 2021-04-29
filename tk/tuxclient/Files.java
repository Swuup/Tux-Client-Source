package tk.tuxclient;

import com.google.gson.Gson;

import java.io.*;

public class Files {
    private static Gson gson = new Gson();

    private static File TexFolder = new File("assets", "TuxClient");
    private static File PositionFolder = new File(TexFolder, "pos");

    public static void init() {
        if (!TexFolder.exists()) {
            TexFolder.mkdirs();
        }

        if (!PositionFolder.exists()) {
            PositionFolder.mkdirs();
        }
    }

    public static Gson getGson() {
        return gson;
    }

    public static File getPosFolder() {
        return PositionFolder;
    }

    public static boolean writeJsonToFile(File file, Object obj) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(gson.toJson(obj).getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static <T extends Object> T readFromJson(File file, Class<T> c) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder builder = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            return gson.fromJson(builder.toString(), c);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
