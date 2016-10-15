package com.codingmechanic.loginpage;

import android.content.Context;
import android.os.AsyncTask;

import com.codingmechanic.loginpage.App.AppConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by mofi on 9/12/16.
 */
public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;

    BackgroundWorker(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... values) {
        String type = values[0];
        String loginUrl = AppConfig.URL_LOGIN;
        String registerUrl = AppConfig.URL_REGISTER;
        String addCollege = AppConfig.URL_ADD_COLLEGE;
        if (type.equals("login")) {
            try {
                String userName = values[1];
                String password = values[2];
                URL url = new URL(loginUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("register")) {
            try {
                String userName = values[1];
                String emailAddr = values[2];
                String password = values[3];
                String userType = values[4];
                URL url = new URL(registerUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(emailAddr, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(userType, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("add_college")) {
            try {
                String collegecode = values[1];
                String colllgename = values[2];
                URL url = new URL(addCollege);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postData = URLEncoder.encode("college_code", "UTF-8") + "=" + URLEncoder.encode(collegecode, "UTF-8") + "&" +
                        URLEncoder.encode("college_name", "UTF-8") + "=" + URLEncoder.encode(colllgename, "UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String value) {
        super.onPostExecute(value);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
