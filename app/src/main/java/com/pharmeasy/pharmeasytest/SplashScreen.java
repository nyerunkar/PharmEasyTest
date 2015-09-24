package com.pharmeasy.pharmeasytest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by Nilesh on 24/09/15.
 */
public class SplashScreen extends Activity
{

    private TableMaster tableMaster;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        tableMaster = new TableMaster(this);

//        tableMaster.truncateTable(TableMaster.MASTER_DATA);
        if(tableMaster.getAllData(TableMaster.MASTER_DATA).length < 2)
        {
            new getNetData("https://www.1mg.com/api/v1/search/autocomplete?name=b&pageSize=10000000&_=1435404923427").execute();
        }
        else
        {
            nextScreen();
        }
    }

    Handler handler = new Handler();
    private class getNetData extends AsyncTask<Void, String, Object> {
        ProgressDialog progressDialog;
        private String sURL;

        public getNetData(String sURL)

        {
            this.sURL = sURL;
        }

        protected void onPreExecute()
        {
            handler.post(new Runnable() {
                public void run() {
                    progressDialog = ProgressDialog.show(SplashScreen.this, "Loading ...", "Fetching data from server and storing into local database");
                }
            });

        }

        String sRe = "";

        protected Object doInBackground(Void... params)
        {
            if (android.os.Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            sRe = connectLong(sURL);
            try
            {
                JSONObject jsonObject = new JSONObject(sRe);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int k=0; k < jsonArray.length() ; k++)
                {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(k);
                    tableMaster.addRecordDB_MASTER_DATA(jsonObject1.getString("id"),jsonObject1.getString("hkpDrugCode"),jsonObject1.getString("mfId"),jsonObject1.getString("label"),jsonObject1.getString("name"),jsonObject1.getString("type"),jsonObject1.getString("packSize"),jsonObject1.getString("manufacturer"),jsonObject1.getString("uPrice"),jsonObject1.getString("oPrice"),jsonObject1.getString("mrp"),jsonObject1.getString("su"),jsonObject1.getString("slug"),jsonObject1.getString("packForm"),jsonObject1.getString("form"),jsonObject1.getString("imgUrl"),jsonObject1.getString("uip"),jsonObject1.getString("generics"),jsonObject1.getString("productsForBrand"),jsonObject1.getString("discountPerc"),jsonObject1.getString("pForm"),jsonObject1.getString("available"));
                }

            }
            catch (Exception e)
            {
                System.out.println("PP "+e);
            }

            return null;
        }

        protected void onPostExecute(Object result)
        {


            progressDialog.dismiss();
            nextScreen();

        }
    }
    public String connectLong(String url)
    {
        String response ="";

        url = url.replace(" ", "");
        System.out.println("API Get:-"+url);
        try
        {
            HttpParams httpParameters = new BasicHttpParams();
            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpGet request = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(request);
            httpResponse.getStatusLine().getStatusCode();
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, "iso-8859-1");

        }
        catch(Exception e)
        {}
        if(response.startsWith("<HTML>") || response.startsWith("<!DOCTYPE HTML") || response.startsWith("<html>"))
        {
            response="";
        }
        System.out.println("Response--"+response);

        return response;

    }


    private void nextScreen()
    {
        new Thread(new Runnable() {
            @Override

            public void run() {
                try
                {
                    Thread.sleep(2000);

                    finish();
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                }catch (Exception e)
                {

                }
            }
        }).start();

    }
}
