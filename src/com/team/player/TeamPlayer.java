package com.team.player;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeamPlayer extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(mAddListener);
    }
    
    private OnClickListener mAddListener = new OnClickListener()
    {
    	public void onClick(View v)
    	{	
    		switch(v.getId())
    		{
    		case R.id.loginButton:
	    		try
	    		{
	    			Intent intent = new Intent(TeamPlayer.this, TeamPage.class);
    				TeamPlayer.this.startActivity(intent);
	    		}
	    		catch (Exception ex)
	    		{			
			    	Toast.makeText(getApplicationContext(), ex.toString(), 2).show();
	    		}
			    break;
    		}

    		
    	}
    };
}