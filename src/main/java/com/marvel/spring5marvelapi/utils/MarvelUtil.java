package com.marvel.spring5marvelapi.utils;

import com.marvel.spring5marvelapi.dto.marvel.MarvelDTO;
import com.marvel.spring5marvelapi.dto.marvel.Result;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vamshikirangullapelly on 04/11/2018.
 */
public class MarvelUtil {


    public static Set<Integer> addToFile(MarvelDTO marvelDTO, String fileName) throws IOException {

        Set<Integer> ids = new HashSet<>();
        List<Result> results = marvelDTO.getData().getResults();

        StringBuilder sb = new StringBuilder();
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Result result : results) {
            ids.add(result.getId());
            sb.append(result.getId() + "%");
            sb.append(result.getName() + "%");
            sb.append(result.getDescription().replaceAll("\n", " ") + "%");
            sb.append(result.getThumbnail().getPath() + "%");
            sb.append(result.getThumbnail().getExtension() + "\n");
        }
        bw.write(sb.toString());
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw.close();

        return ids;
    }

    public static String getMarvelClientURl(int limit, int offset, String privateKey, String publicKey, String baseUrl, String apiURL) {
        long timeStamp = System.currentTimeMillis();
        String stringToHash = timeStamp + privateKey + publicKey;
        String hash = DigestUtils.md5Hex(stringToHash);
        String url = String.format(baseUrl + apiURL, timeStamp, publicKey, hash, offset, limit);
        return url;
    }

    public static long getFileCretedTime(String fileName) throws IOException {
        long result = 0;
        Path path = Paths.get(fileName);
        if (path != null) {
            BasicFileAttributes view
                    = Files.getFileAttributeView(path, BasicFileAttributeView.class)
                    .readAttributes();
            FileTime fileTime = view.lastModifiedTime();
            result = fileTime.toMillis();
        }
        return result;
    }

    public static long getFileSize(String fileName) throws IOException {
        long result = 0;
        Path path = Paths.get(fileName);
        if (path != null) {
            BasicFileAttributes view
                    = Files.getFileAttributeView(path, BasicFileAttributeView.class)
                    .readAttributes();
            long fileSize = view.size();
            result = fileSize;
        }
        return result;
    }

    public static void clearFileContent(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        if (path != null) {
            Files.deleteIfExists(path);
            Files.createFile(path);
        }
    }

}
