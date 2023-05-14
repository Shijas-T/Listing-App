package com.example.listingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductListingFragment extends Fragment {

    //Declaration
    View view;

    private RecyclerView recyclerViewProducts;
    private ArrayList<ProductModel> arrayListAllProducts;
    private ProductAdapter productAdapter;

    private ProgressBar progressBar;

    public ProductListingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_listing, container, false);

        recyclerViewProducts = view.findViewById(R.id.recyclerview_products);
        arrayListAllProducts = new ArrayList<>();

        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,"https://app.getswipe.in/api/public/get", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++){

                                JSONObject info = response.getJSONObject(i);

                                arrayListAllProducts.add(new ProductModel(
                                        info.getString("image"),
                                        info.getInt("price"),
                                        info.getString("product_name"),
                                        info.getString("product_type"),
                                        info.getInt("tax")
                                ));
                            }
                            if(response.length()>0){
                                productAdapter = new ProductAdapter(arrayListAllProducts, getContext());
                                recyclerViewProducts.setHasFixedSize(true);
                                recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerViewProducts.setAdapter(productAdapter);
                                productAdapter.notifyDataSetChanged();
                            }
                            else{
                                Toast.makeText(getContext(), "NO DATA FOUND", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        });
        mQueue.add(request);

        return view;
    }
}