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

import com.example.groceryapp.StoreProductHelper.MyAdapter;
import com.example.groceryapp.StoreProductHelper.StoreProductHelperClass;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class UpdateProduct extends AppCompatActivity{
    ImageView back, productImage;
    Button delete;
    EditText productNameText, priceText, quantityText, restockTimeText;
    String productCategory;
    DatePickerDialog picker;
    Button saveBtn;
    int SELECT_PICTURE = 200;
    SharedPreferences sharedPreferences;
    private String sharedStoreId;
    private String itemId;
    Uri selectedImageUri;

    // create array of Strings and store name of the categories
    List<String> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        categories.add("Product");
        categories.add("Meat & Seafood");
        categories.add("Dairy & Eggs");
        categories.add("Snacks");
        categories.add("Others");

        // retrieve data from previous page
        Intent intent = getIntent();
        itemId = intent.getStringExtra("itemId");
        String previousItemName = intent.getStringExtra("itemName");
        String previousItemCategory = intent.getStringExtra("itemCategory");
        String previousItemPrice = intent.getStringExtra("itemPrice");
        String previousItemStock = intent.getStringExtra("itemStock");
        String previousItemRestockTime = intent.getStringExtra("restockTime");

        productNameText = (EditText) findViewById(R.id.updateProductName);
        priceText = (EditText) findViewById(R.id.updateProductPrice);
        quantityText = (EditText) findViewById(R.id.updateProductQuantity);
        saveBtn = (Button) findViewById(R.id.updateProductBtn);
        productImage = (ImageView) findViewById(R.id.updateProductImage);
        restockTimeText = (EditText) findViewById(R.id.updateRestockTime);

        // set default values for each edit text
        productNameText.setText(previousItemName);
        priceText.setText(previousItemPrice);
        quantityText.setText(previousItemStock);
        restockTimeText.setText(previousItemRestockTime);
        // retrieve the previous image from database and display
//        productImage.setImageBitmap(convertStringToBitImage(getImageName(itemId)));
        loadImage(getImageName(itemId));

        // move back if back button clicked
        back = (ImageView) findViewById(R.id.updateProductBackView);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        // delete the product if delete button clicked
        delete = (Button) findViewById(R.id.deleteBtn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = ("delete from Products where id = " + itemId);
                updateDB(query);
            }
        });

        // Extract store id from local
        sharedPreferences = getSharedPreferences("StorePrefs", Context.MODE_PRIVATE);
        sharedStoreId = sharedPreferences.getString("storeId", null);

        // Take the instance of Spinner and apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked
        Spinner spin = findViewById(R.id.updateCategoriesSpinner);

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
        if (categories.contains(previousItemCategory)){
            int position = ad.getPosition(previousItemCategory);
            spin.setSelection(position);
        }

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                productCategory = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
        restockTimeText = (EditText) findViewById(R.id.updateRestockTime);
        restockTimeText.setInputType(InputType.TYPE_NULL);
        restockTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                picker = new DatePickerDialog(UpdateProduct.this,
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

        // update data to database if save button clicked
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check the correction
                if (checkCorrection()){
                    String query;
                    if (null != selectedImageUri) {
                        String fileName = updateImage();
                        // Update product data to database
                        query = ("update Products set "
                                + "ItemName = '" + productNameText.getText() + "', "
                                + "ItemStock = '" + new Float(quantityText.getText().toString()) + "', "
                                + "RestockTime = '" + restockTimeText.getText() + "', "
                                + "ItemPrice = '" + new Float(priceText.getText().toString()) + "', "
                                + "ItemCategory = '" + productCategory + "', "
                                + "ItemImage = '" + fileName + "', "
                                + "RetailerId = '" + sharedStoreId + "' where id = " + itemId + ";");
                    }else{
                        query = ("update Products set "
                                + "ItemName = '" + productNameText.getText() + "', "
                                + "ItemStock = '" + new Float(quantityText.getText().toString()) + "', "
                                + "RestockTime = '" + restockTimeText.getText() + "', "
                                + "ItemPrice = '" + new Float(priceText.getText().toString()) + "', "
                                + "ItemCategory = '" + productCategory + "', "
                                + "RetailerId = '" + sharedStoreId + "' where id = " + itemId + ";");
                    }
                    updateDB(query);
                }
            }
        });
    }

    public String updateImage(){
        // generate a unique string as the file name
        String fileName = UUID.randomUUID().toString();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images/" + fileName);
        storageReference.putFile(selectedImageUri);
        return fileName;
    }

    public void loadImage(String fileName) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/" + fileName);
        try {
            final File localFile = File.createTempFile(fileName, "image");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    productImage.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateDB(String query){
        int temp = DBUtil.Update(query);
        if (temp == 1) {
            // Success
            Toast.makeText(UpdateProduct.this,"Success!", Toast.LENGTH_LONG).show();
            // Jump to previous page
            finish();
        } else {
            // Error
            Toast.makeText(UpdateProduct.this,"Error!", Toast.LENGTH_LONG).show();
        }
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

    // this function is used to retrieve the image string from database
    public String getImageName(String itemId){
        // Request product list from db
        ArrayList<ArrayList<String>> productList = DBUtil.Query(
                "select ItemImage from Products where id = " + itemId);
        if (productList.size() != 0) {
            return productList.get(0).get(0);
        }
        return null;
    }

    // this function is used to decode the string into image
    public Bitmap convertStringToBitImage(String imageString){
        // decode string to image
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
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
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    productImage.setImageURI(selectedImageUri);
                }
            }
        }
    }


}