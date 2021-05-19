package com.eligustilo.triviaapp1.ui.trivia

import android.os.AsyncTask
import android.util.Log
import java.lang.Exception
import java.net.URL

class DataDownloader(private val callingObjectAKAListener: OnJSONReady, private val urlString: String): AsyncTask<String,Void,String>() {
    private val TAG ="DataDownloader"

    interface OnJSONReady {//TODO how exactly do these stupid interfaces work?
        fun jsonReady(jsonData: String)
    }

    override fun doInBackground(vararg params: String?): String {
        try {
            val jsonResults = URL(urlString).readText()
//            val jsonResults = Jsoup.parse(urlString).text()
            Log.d(TAG, "jsonResults are $jsonResults")
            return jsonResults
        } catch(e: Exception) {
            val error = e.localizedMessage
            if(error != null){
                Log.e(TAG, error)
                return error
            } else {
                return "null error"
            }
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if(result != null){
            callingObjectAKAListener.jsonReady(result)
            Log.d(TAG, "postExecute Results: $result")
        } else {
            Log.e(TAG, "Error Downloading: $status")
        }
    }
}