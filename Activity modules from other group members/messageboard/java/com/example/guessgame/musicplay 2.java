package com.example.guessgame;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class musicplay extends IntentService {

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.guessgame.action.FOO";
    public static final String ACTION_BAZ = "com.example.guessgame.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.guessgame.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.guessgame.extra.PARAM2";

    //action的声明
    public static final String Action_music = "com.example.guessgame.action.music";
    //声明media
    private MediaPlayer mediaPlayer;

    public musicplay() {
        super("musicplay");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }

            if(Action_music.equals(action)){
                handleActionmusic();
            }
        }
    }


    private void handleActionmusic(){
        if(mediaPlayer==null){
            mediaPlayer=MediaPlayer.create(this,R.raw.music1);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }


    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}