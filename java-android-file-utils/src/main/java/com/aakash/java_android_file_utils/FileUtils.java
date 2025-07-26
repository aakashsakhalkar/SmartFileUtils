package com.aakash.java_android_file_utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.webkit.MimeTypeMap;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtils {

    public static String formatFileSize(long sizeInBytes) {
        if (sizeInBytes <= 0) return "0 B";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(sizeInBytes) / Math.log10(1024));
        return String.format(Locale.getDefault(), "%.1f %s",
                sizeInBytes / Math.pow(1024, digitGroups),
                units[digitGroups]);
    }

    public static double getFileSizeInKB(File file) {
        return file.exists() ? (file.length() / 1024.0) : 0;
    }

    public static double getFileSizeInMB(File file) {
        return file.exists() ? (file.length() / (1024.0 * 1024.0)) : 0;
    }

    public static String getFileExtension(String fileName) {
        if (fileName == null) return "";
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex >= 0) ? fileName.substring(dotIndex + 1).toLowerCase() : "";
    }

    public static String getFileNameWithoutExtension(String fileName) {
        if (fileName == null) return "";
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex >= 0) ? fileName.substring(0, dotIndex) : fileName;
    }

    public static boolean isImageFile(String fileName) {
        String ext = getFileExtension(fileName);
        return ext.matches("jpg|jpeg|png|gif|bmp|webp|heic|svg");
    }

    public static boolean isVideoFile(String fileName) {
        String ext = getFileExtension(fileName);
        return ext.matches("mp4|mkv|webm|avi|mov|flv|3gp");
    }

    public static boolean isAudioFile(String fileName) {
        String ext = getFileExtension(fileName);
        return ext.matches("mp3|wav|aac|ogg|m4a|flac|amr");
    }

    public static boolean isPdfFile(String fileName) {
        return "pdf".equalsIgnoreCase(getFileExtension(fileName));
    }

    public static boolean isDocumentFile(String fileName) {
        String ext = getFileExtension(fileName);
        return ext.matches("pdf|doc|docx|ppt|pptx|xls|xlsx|txt|rtf");
    }

    public static String getMimeTypeFromExtension(String extension) {
        if (extension == null) return "*/*";
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
    }

    public static String getMimeTypeFromFile(File file) {
        String ext = getFileExtension(file.getName());
        return getMimeTypeFromExtension(ext);
    }

    public static boolean isMimeType(File file, String typePrefix) {
        String mime = getMimeTypeFromFile(file);
        return mime != null && mime.startsWith(typePrefix);
    }

    public static boolean deleteFileIfExists(File file) {
        return file != null && file.exists() && file.delete();
    }

    public static boolean isValidFile(File file) {
        return file != null && file.exists() && file.isFile() && file.length() > 0;
    }

    public static String getLastModifiedFormatted(File file) {
        if (file == null || !file.exists()) return "N/A";
        Date date = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
        return sdf.format(date);
    }

    public static String getReadableFileNameFromUri(Context context, Uri uri) {
        if (uri == null) return "";
        String result = null;
        if ("content".equals(uri.getScheme())) {
            Cursor cursor = context.getContentResolver()
                    .query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            if (result != null) {
                int cut = result.lastIndexOf('/');
                if (cut != -1) result = result.substring(cut + 1);
            }
        }
        return result != null ? result : "";
    }

    public static String getParentFolderName(File file) {
        if (file == null || file.getParentFile() == null) return "";
        return file.getParentFile().getName();
    }

    public static File renameFile(File file, String newName) {
        if (!file.exists() || newName == null) return null;
        File newFile = new File(file.getParent(), newName);
        return file.renameTo(newFile) ? newFile : null;
    }

    public static boolean copyFile(File src, File dest) {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static String guessTypeFromMime(String mime) {
        if (mime == null) return "unknown";
        if (mime.startsWith("image/")) return "Image";
        if (mime.startsWith("video/")) return "Video";
        if (mime.startsWith("audio/")) return "Audio";
        if (mime.startsWith("text/")) return "Text";
        if (mime.equals("application/pdf")) return "PDF";
        if (mime.contains("msword") || mime.contains("wordprocessingml")) return "Document";
        return "File";
    }

    public static String getFileAgeDescription(File file) {
        if (!file.exists()) return "unknown";
        long diff = System.currentTimeMillis() - file.lastModified();
        long days = diff / (1000 * 60 * 60 * 24);
        if (days == 0) return "today";
        if (days == 1) return "yesterday";
        return days + " days ago";
    }
}
