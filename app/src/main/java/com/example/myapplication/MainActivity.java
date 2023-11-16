package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import org.json.JSONException;
import android.widget.Toast;
import android.os.AsyncTask;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    ArrayList<ServerModel> serverModelArrayList = new ArrayList<>();
    ProgressBar progressBar;
    ServerFactory serverFactory;
    RecyclerView recyclerView;
    SM_RecyclerViewAdapter adapter;

    public void setupServerList(RecyclerView recyclerView) {
        try {
            serverModelArrayList = serverFactory.setUpserverModelArrayList();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        adapter = new SM_RecyclerViewAdapter(this, serverModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void updateServerList() {
            adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        serverFactory = new ServerFactory();

        setupServerList(recyclerView);

        startAsyncTask();

    }

    public void addServerButton() {

    }








    /**
     * This Part of the code is created to take user arraylist of servers and attempt to contact them,
     * it will then update the list of servers on screen
     */

    @Override
    public void processFinish(ArrayList<ServerModel> output){
        serverModelArrayList = output;
        updateServerList();
    }

    public void startAsyncTaskButton(View v) {
        AsyncServerConnectorTask task = new AsyncServerConnectorTask(this);

        //this override the implemented method from asyncTask
        task.delegate = this;

        task.execute(serverFactory.getServerModelArrayList());
    }

    public void startAsyncTask() {
        AsyncServerConnectorTask task = new AsyncServerConnectorTask(this);

        //this override the implemented method from asyncTask
        task.delegate = this;

        task.execute(serverFactory.getServerModelArrayList());
    }

    private static class AsyncServerConnectorTask extends AsyncTask<ArrayList<ServerModel>, Integer, ArrayList<ServerModel>> {

        public AsyncResponse delegate = null;

        private WeakReference<MainActivity> activityWeakReference;
        ArrayList<ServerModel> serverModelArrayList = new ArrayList<>();
        ServerFactory serverFactory = new ServerFactory();

        AsyncServerConnectorTask(MainActivity activity) {
            activityWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            activity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<ServerModel> doInBackground(ArrayList<ServerModel>... arrayLists) {
            ArrayList<ServerModel> servers = arrayLists[0];
            for (int i = 0; i < servers.size(); i++) {
                publishProgress((i * 100) / servers.size());
                try {
                    servers.set(i, serverFactory.formConnection(servers.get(i)));
                } catch (IOException | JSONException e) {
                    servers.get(i).setVersion("Connection Failed");
                }
            }

            return serverModelArrayList;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            activity.progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<ServerModel> servers) {
            super.onPostExecute(servers);

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            Toast.makeText(activity, "Finished", Toast.LENGTH_SHORT).show();
            activity.progressBar.setProgress(0);
            activity.progressBar.setVisibility(View.INVISIBLE);

            delegate.processFinish(servers);
        }


    }

    //End of Server arraylist Setup/update



}