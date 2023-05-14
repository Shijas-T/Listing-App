package com.example.listingapp;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddProductFragment extends Fragment {

    //Declaration
    View view;
    private Button buttonAddProduct;
    private EditText editTextProductName, editTextProductPrice, editTextProductTaxRate;
    private AutoCompleteTextView autoCompleteTextViewType;
    private ImageView imageViewProduct;

    int SELECT_PICTURE = 200;
    Uri selectedImageUri;

    public AddProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_product, container, false);

        editTextProductName = view.findViewById(R.id.edit_text_product_name);
        editTextProductPrice = view.findViewById(R.id.edit_text_product_price);
        editTextProductTaxRate = view.findViewById(R.id.edit_text_product_tax);
        buttonAddProduct = view.findViewById(R.id.btn_addProduct);
        autoCompleteTextViewType = view.findViewById(R.id.auto_complete_tv_product_type);
        imageViewProduct = view.findViewById(R.id.img_product_photo_to_sell);

        //Strings for DropDown
        String[] type = getResources().getStringArray(R.array.product_type);
        //Setting Adapter
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,type);
        autoCompleteTextViewType.setAdapter(typeAdapter);

        imageViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an instance of the
                // intent of the type image
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                // pass the constant to compare it
                // with the returned requestCode
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        return view;
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageViewProduct.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private void addProduct() {

        // Take the value of two edit texts in Strings
        String productName, productPrice, productProductTaxRate, productType;
        productName = editTextProductName.getText().toString();
        productPrice = editTextProductPrice.getText().toString();
        productProductTaxRate = editTextProductTaxRate.getText().toString();
        productType = autoCompleteTextViewType.getText().toString();

        // validations for inputs
        if (TextUtils.isEmpty(productName)) {
            Toast.makeText(getContext(),
                            "Please enter name!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(productPrice)) {
            Toast.makeText(getContext(),
                            "Please enter price!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(productProductTaxRate)) {
            Toast.makeText(getContext(),
                            "Please enter tax rate!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(productType)) {
            Toast.makeText(getContext(),
                            "Please select type!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }


    }
}