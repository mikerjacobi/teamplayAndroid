package com.team.player;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CreateTeam extends ListActivity
{
	public String sport = "";
	
	public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createteam);
		
		
		String[] sports = getResources().getStringArray(R.array.sports_array);
		ListAdapter x = new ArrayAdapter<String>(this, R.layout.sportlistitem, sports);
		setListAdapter(x);
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,int position, long id)
		    {
		    	sport = ((TextView) view).getText().toString();
		        Toast.makeText(getApplicationContext(), ((TextView) view).getText().toString() + " selected.",1).show();
		    }
		  });
		
		Button selectSportButton = (Button)findViewById(R.id.createTeamButton);
		selectSportButton.setOnClickListener(mAddListener);
    }
		
		private OnClickListener mAddListener = new OnClickListener()
	    {
	    	public void onClick(View v)
	    	{	
	    		switch(v.getId())
	    		{
	    		case R.id.createTeamButton:
		    		try
		    		{
		    			
		    			
		    			
				        //database entry here!
				        
				        EditText nameET = (EditText)findViewById(R.id.teamnameET);
				        String teamName = nameET.getText().toString();
				        
				        Toast.makeText(getApplicationContext(), teamName + ": created",1).show();
				        
		    			Intent intent = new Intent(CreateTeam.this, TeamPage.class);
	    				CreateTeam.this.startActivity(intent);
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

