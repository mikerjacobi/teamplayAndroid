package com.team.player;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeamPage extends ListActivity
{
	public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teampage);
		
		Button backButton = (Button)findViewById(R.id.createTeam);
		backButton.setOnClickListener(mAddListener);
    }
		
		private OnClickListener mAddListener = new OnClickListener()
	    {
	    	public void onClick(View v)
	    	{	
	    		switch(v.getId())
	    		{
	    		case R.id.createTeam:
		    		try
		    		{
		    			Intent intent = new Intent(TeamPage.this, CreateTeam.class);
	    				TeamPage.this.startActivity(intent);
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

