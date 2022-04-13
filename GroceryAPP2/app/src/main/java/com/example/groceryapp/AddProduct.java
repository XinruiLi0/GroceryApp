package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class AddProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ImageView back, productImage, imageTest;
    EditText productNameText, priceText, quantityText, restockTimeText;
    String productCategory;
    DatePickerDialog picker;
    Button saveBtn;
    int SELECT_PICTURE = 200;
    SharedPreferences sharedPreferences;
    private String sharedStoreId;

    // create array of Strings and store name of the categories
    String[] categories = { "Product", "Meat & Seafood", "Dairy & Eggs", "Snacks", "Others" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productNameText = (EditText) findViewById(R.id.addProductName);
        priceText = (EditText) findViewById(R.id.addProductPrice);
        quantityText = (EditText) findViewById(R.id.addProductQuantity);
        saveBtn = (Button) findViewById(R.id.addProductBtn);
        productImage = (ImageView) findViewById(R.id.addProductImage);

        // move back if back button clicked
        back = (ImageView) findViewById(R.id.addProductBackView);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        // Extract store id from local
        sharedPreferences = getSharedPreferences("StorePrefs", Context.MODE_PRIVATE);
        sharedStoreId = sharedPreferences.getString("storeId", null);

        // Take the instance of Spinner and apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked
        Spinner spin = findViewById(R.id.categoriesSpinner);
        spin.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                categories);

        // set simple layout resource file for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the Spinner which binds data to spinner
        spin.setAdapter(ad);

        // pick image from gallery
        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an instance of the intent of the type image
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                // pass the constant to compare it with the returned requestCode
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });

        // create a date picker of restock time
        restockTimeText = (EditText) findViewById(R.id.restockTime);
        restockTimeText.setInputType(InputType.TYPE_NULL);
        restockTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                picker = new DatePickerDialog(AddProduct.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String monthOfYear = "";
                                String dayOfMonth = "";
                                if (i1 < 9){
                                    monthOfYear = "0" + (i1 + 1);
                                }else{
                                    monthOfYear = "" + (i1 + 1);
                                }
                                if (i2 < 10){
                                    dayOfMonth = "0" + i2;
                                }else{
                                    dayOfMonth = "" + i2;
                                }
                                restockTimeText.setText(i + "-" + monthOfYear + "-" + dayOfMonth);
                            }
                        }, year, month, day);

                // set maximum date to be selected as today
                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();
            }
        });

        // upload data to database if save button clicked
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check the correction
                if (checkCorrection()){
                    // get the encoded image string
                    String imageString = convertProductImageToString();
                    // Update product data to database
                    String query = ("insert into Items(ItemName, ItemStock, RestockTime, ItemPrice, ItemCategory, ItemImage, RetailerId) " + "values ('"
                            + productNameText.getText() + "', '"
                            + new Float(quantityText.getText().toString()) + "', '"
                            + restockTimeText.getText() + "', '"
                            + new Float(priceText.getText().toString()) + "', '"
                            + productCategory + "', '"
                            + imageString + "', '"
                            + sharedStoreId +"')");

                    int temp = DBUtil.Update(query);
                    if (temp == 1) {
                        // Success
                        Toast.makeText(AddProduct.this,"Success!", Toast.LENGTH_LONG).show();
                        // Jump to previous page
                        finish();
                    } else {
                        // Error
                        Toast.makeText(AddProduct.this,"Error!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    // this function is used to encode the image into string
    public String convertProductImageToString(){
        //encode image to base64 string
        BitmapDrawable bitmapDrawable = (BitmapDrawable) productImage.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes , Base64.DEFAULT);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        productCategory = categories[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean checkCorrection(){
        if (!productNameText.getText().toString().matches("")
                && !priceText.getText().toString().matches("")
                && !quantityText.getText().toString().matches("")
                && !restockTimeText.getText().toString().matches("")
                && !productCategory.matches("")
                && productImage != null){
            return true;
        }else{
            Toast.makeText(this, "Please complete all information", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
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
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    productImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

}