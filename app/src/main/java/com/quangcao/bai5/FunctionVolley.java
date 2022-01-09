package com.quangcao.bai5;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FunctionVolley {

    // Đọc dữ liệu từ chuỗi
    public void getStringVolley(Context context , TextView textView){
        // 1. Tạo request
        RequestQueue queue = Volley.newRequestQueue(context);
        // 2. URL ( API ) : https://www.google.com/
        String url = "https://www.google.com/";
        // 3. Truyền tham số
        // StringRequest(phương thức , url , xử lí khi thành công , xử lí khi thất bại)
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                // khi thành công
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // vì chuỗi lấy về quá dài nên ta chỉ lấy 500 ký tự
                        textView.setText( "Result : " + response.substring(0 , 500) );
                    }
                },
                // khi thất bại
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getMessage() );
                    }
                }
        );
        // 4. add request vào xử lí
        queue.add(stringRequest);
    }
    // hàm xử lí JSON ( Mảng của các đối tượng )
    String strJSON = "";
    String errorToString = "";
    public void getJsonArray(Context context, TextView textView){
        // 1. Tạo Request
        RequestQueue queue = Volley.newRequestQueue(context);
        // 2. url : https://batdongsanabc.000webhostapp.com/mob403lab3/array_json_new.json
        String url = "https://batdongsanabc.000webhostapp.com/mob403lab3/array_json_new.json";
        // 3. Gọi Request
        /* vì ở đây trong mảng có những đối tượng => gọi mảng trước , đối tượng sau */
        // JsonArrayRequest(url , xử lí khi thành công , xử lí khi thất bại)
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                url,
                // khi thành công
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // chuyển mảng => đối tượng
                        // Đọc từng phần tử và chuyển nó sang đối tượng
                        for (int i = 0; i < response.length() ; i++) {
                            try{
                                // Lấy về 1 đối tượng
                                JSONObject person = response.getJSONObject(i);
                                // bóc tách đối tượng
                                String id = person.getString("id");
                                String name = person.getString("name");
                                String email = person.getString("email");
                                JSONObject phone = person.getJSONObject("phone");
                                    String mobile = phone.getString("mobile");
                                    String home = phone.getString("home");
                                // đưa vào chuỗi để hiển thị
                                strJSON += "\n\nName : " + name + "\n";
                                strJSON += "Email : " + email + "\n";
                                strJSON += "Mobile : " + mobile + "\n";
                                strJSON += "Home : " + home + "\n";
                            } catch (Exception ex){
                                errorToString += ex.getMessage();
                            }
                        }
                        // hiển thị kết quả trên màn hình
                        textView.setText( strJSON );
                    }
                },
                // khi thất bại
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText( error.getMessage() );
                    }
                }
        );
        // 4. add Request
        queue.add(jsonArrayRequest);
    }

    public void insertVolley(Context context , TextView textView , String name , String price , String description){
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://polyprojects.herokuapp.com/api/add_products";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText( response.toString() );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getMessage() );
                    }
                } )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myDATA = new HashMap<>();
                myDATA.put("name" , name);
                myDATA.put("price" , price);
                myDATA.put("description" , description);
                return myDATA;
            }
        };

        queue.add(stringRequest);
    }

    public void updateVolley(Context context , TextView textView, String id , String name , String price , String description){
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://polyprojects.herokuapp.com/api/update_products";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText( response.toString() );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getMessage() );
                    }
                } )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myDATA = new HashMap<>();
                myDATA.put("id" , id);
                myDATA.put("name" , name);
                myDATA.put("price" , price);
                myDATA.put("description" , description);
                return myDATA;
            }
        };

        queue.add(stringRequest);
    }

    public void deleteVolley(Context context , TextView textView , String id){
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://polyprojects.herokuapp.com/api/delete_products";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText( response.toString() );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getMessage() );
                    }
                } )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myDATA = new HashMap<>();
                myDATA.put("id" , id);
                return myDATA;
            }
        };

        queue.add(stringRequest);
    }

}
