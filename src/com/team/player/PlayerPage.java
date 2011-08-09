package com.team.player;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class PlayerPage extends ListActivity
{
	public void onCreate(Bundle savedInstanceState)
    {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.playerpage);
		
		Bundle b = getIntent().getExtras();
		String team = b.getString("team");
		TextView teamTV = (TextView) findViewById(R.id.teamName);
		teamTV.setText(team);

		String responseBody = null;
		
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet("http://192.168.1.106/teamplay/getPlayers.php?team="+team);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpclient.execute(httpget, responseHandler);
		} 
		catch (Exception e) 
		{
			Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
		}
		
		String[] data = responseBody.split(",");
		setListAdapter(new ArrayAdapter<String>(this, R.layout.sportlistitem, data));
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		
		
		
		
		
		lv.setOnItemClickListener(new OnItemClickListener() 
		{
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
		    {
			    // do stuff with onClick event
	    		Toast.makeText(getApplicationContext(), ((TextView) view).getText(),Toast.LENGTH_LONG).show();
			   
		    }
		});
		
	}
	
	
}

