package com.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

import com.dropbox.core.v2.sharing.RequestedVisibility;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.SharedLinkSettings;
import com.dropbox.core.v2.users.FullAccount;

import java.util.ArrayList;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class DBMain {
	
	public static ArrayList<String> shareurl = new ArrayList<String>();
	
    private static final String ACCESS_TOKEN = "E0dIWcfO49AAAAAAAAAAIfx65iVc6Aoxujlb2MBRTQQP72W_KfYw5iEclN-7mX0P";

    public void DBMain(String imagePath) throws  IOException, Exception, DbxException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

        // Upload "Generated image" to Dropbox
        System.out.println("Upload file-"+imagePath);
        try (InputStream in = new FileInputStream(imagePath)) {
            FileMetadata metadata = client.files().uploadBuilder("/"+imagePath).uploadAndFinish(in);
            // Get files and folder metadata from Dropbox root directory
            ListFolderResult result1 = client.files().listFolder("");
            while (true) {
                for (Metadata metadata1 : result1.getEntries()) {
                    System.out.println(metadata1.getPathLower());
                }

                if (!result1.getHasMore()) {
                    break;
                }

                result1 = client.files().listFolderContinue(result1.getCursor());
            }
           
            SharedLinkMetadata slm = client.sharing().createSharedLinkWithSettings("" + "/" + imagePath, SharedLinkSettings.newBuilder().withRequestedVisibility(RequestedVisibility.PUBLIC).build());
            String tempurl = slm.getUrl();
            
           shareurl.add(tempurl) ;
            System.out.println("Shared URL -"+shareurl);
            
        }
        
        //delete file from dropbox
       
       
             
        
        
    }
}