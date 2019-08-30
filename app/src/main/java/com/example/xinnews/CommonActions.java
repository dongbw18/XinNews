package com.example.xinnews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.example.xinnews.database.NewsEntry;

import java.util.ArrayList;

public class CommonActions {

    // TODO: Bug, java.lang.String cannot be cast to java.util.ArrayList
    public static void share(NewsEntry newsEntry, Context context) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, newsEntry.getTitle());
        shareIntent.putExtra(Intent.EXTRA_TITLE, newsEntry.getTitle());
        String shareContent = newsEntry.getShareContent();
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
        shareIntent.putExtra("sms_body", shareContent);
        shareIntent.putExtra("Kdescription", shareContent);

        if (newsEntry.hasImage()) {
            ArrayList<Uri> imageUris = Bridge.generateAllImagesUri(newsEntry.getNewsId(), context, newsEntry.getImageCount());
            shareIntent.setType("text/plain, image/*");
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        } else {
            shareIntent.setType("text/plain");
        }
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(shareIntent);
    }

    public static void favorite(NewsEntry newsEntry) {

    }
}
