package com.team.player;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TeamPage extends ListActivity
{
	public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teampage);
		
		String responseBody = null;
		
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet("http://192.168.1.106/teamplay/getTeams.php");
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
	    		//Toast.makeText(getApplicationContext(), ((TextView) view).getText(),Toast.LENGTH_LONG).show();
			      
		      	try
	    		{
	    			Intent intent = new Intent(TeamPage.this, PlayerPage.class);
	    			Bundle b = new Bundle();
	    			b.putString("team", ((TextView) view).getText().toString());
	    			intent.putExtras(b);
					TeamPage.this.startActivity(intent);
	    		}
	    		catch (Exception ex)
	    		{			
			    	Toast.makeText(getApplicationContext(), ex.toString(), 2).show();
	    		}
		    }
		});
		
		
		
		
		
		
		
		
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

    


