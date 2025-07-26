# SmartFileUtils

A lightweight Android Java library that provides file utility functions for working with files, types, sizes, MIME, and more — without any external dependencies or paid APIs.

Perfect for Android devs who want quick and clean file handling in their projects.

## Features

✅ Format file sizes (e.g., `1.2 MB`)  
✅ Detect file types (image, video, audio, pdf, doc)  
✅ Get file name, extension, MIME type  
✅ Rename, copy, or delete files  
✅ Handle URIs from `content://`  
✅ Get file age or last modified time  
✅ All pure Java, no permissions needed for internal files

## Installation

Add this to your `build.gradle` using [JitPack](https://jitpack.io/):

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.aakashsakhalkar:SmartFileUtils:v1.0.0'
}
```

## Usage

```java
File file = new File(getFilesDir(), "sample.pdf");

// Size & Format
Log.d(TAG, "formatFileSize: " + FileUtils.formatFileSize(1234567));
Log.d(TAG, "getFileSizeInKB: " + FileUtils.getFileSizeInKB(file));
Log.d(TAG, "getFileSizeInMB: " + FileUtils.getFileSizeInMB(file));

// Type & Extension
String name = "example_document.pdf";
Log.d(TAG, "getFileExtension: " + FileUtils.getFileExtension(name));
Log.d(TAG, "isImageFile: " + FileUtils.isImageFile(name));
Log.d(TAG, "isPdfFile: " + FileUtils.isPdfFile(name));
Log.d(TAG, "getMimeTypeFromFile: " + FileUtils.getMimeTypeFromFile(file));

// File Info
Log.d(TAG, "getLastModifiedFormatted: " + FileUtils.getLastModifiedFormatted(file));
Log.d(TAG, "getParentFolderName: " + FileUtils.getParentFolderName(file));

// URI Handling
Uri uri = Uri.parse("content://...");
Log.d(TAG, "getReadableFileNameFromUri: " + FileUtils.getReadableFileNameFromUri(context, uri));

// File Actions
Log.d(TAG, "copyFile: " + FileUtils.copyFile(srcFile, destFile));
Log.d(TAG, "renameFile: " + FileUtils.renameFile(file, "new_name.pdf"));
Log.d(TAG, "deleteFileIfExists: " + FileUtils.deleteFileIfExists(file));
```

## License

MIT License © 2025 [Aakash Sakhalkar](https://github.com/aakashsakhalkar)