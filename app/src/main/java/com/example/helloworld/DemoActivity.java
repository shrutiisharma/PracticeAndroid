package com.example.helloworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.helloworld.databinding.ActivityDemoBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;

import java.util.List;

public class DemoActivity extends AppCompatActivity {

    ActivityDemoBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityDemoBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("ImageGallery Demo");
        loadImage();
    }



    //Actions Menu methods------------------------------------------------

    //to inflate optionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo, menu);
        return true;
    }


    //handle click events
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh){
            refresh();
            return true;                                            //return true signifies that we have handled this event.
        }
        return false;
    }


    private void refresh() {

        //finish this activity
        finish();

        //call intent of this same activity again
        startActivity(new Intent(this, DemoActivity.class));


        /*

        ---------------------------------------------------------------------------
         This is not working as the Glide is still keeping the cache
         even though it is set to none
         so this is taking effect for just a fraction of second
         and almost immediately loadImage() is there
         and hence no visible changes are there.

         So another method to do this is to restart the whole activity.

         Update: with skipMemoryCache(true), we can use both methods.
        ---------------------------------------------------------------------------

        //show loader
        b.loader.setVisibility(View.VISIBLE);
        b.subtitle.setText(R.string.loading_image);

        //vacate the already filled image view for the new image to come
        b.imageView.setImageDrawable(null);

        //again load the image
        loadImage();
        */

    }

    
    //to load a random image
    private void loadImage() {
        Glide.with(DemoActivity.this)
                .asBitmap()
                .load("https://picsum.photos/1080")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        b.loader.setVisibility(View.GONE);
                        b.subtitle.setText(getString(R.string.image_load_failed, e.toString()));
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        b.loader.setVisibility(View.GONE);
                        b.subtitle.setText(R.string.image_loaded);
                        b.imageView.setImageBitmap(resource);

                        //labelImage(resource);
                        createPaletteAsync(resource);
                        return true;
                    }
                })
                /*.listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        b.loader.setVisibility(View.GONE);
                        b.subtitle.setText(getString(R.string.image_load_failed, e.toString()));
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        b.loader.setVisibility(View.GONE);
                        b.subtitle.setText(R.string.image_loaded);
                        b.imageView.setImageDrawable(resource);
                        return true;
                    }
                })*/
                .into(b.imageView);
    }

    private void labelImage(Bitmap bitmap) {
        //image from bitmap
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        ImageLabeler labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
        labeler.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
                    @Override
                    public void onSuccess(List<ImageLabel> labels) {
                        // Task completed successfully
                        new MaterialAlertDialogBuilder(DemoActivity.this)
                                .setTitle("Labels Fetched")
                                .setMessage(labels.toString())
                                .show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        new MaterialAlertDialogBuilder(DemoActivity.this)
                                .setTitle("Labels Fetched")
                                .setMessage(e.toString())
                                .show();
                    }
                });
    }



    // Generate palette asynchronously and use it on a different
    // thread using onGenerated()
    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                // Use generated instance
                new MaterialAlertDialogBuilder(DemoActivity.this)
                        .setTitle("Palette Fetched")
                        .setMessage(p.getSwatches().toString())
                        .show();
            }
        });
    }


    /*

    --------------------------------------------------GalleryApp-------------------------------------------------------------------

    //In OnCreate Method

    new ItemHelper()
                .fetchData(1920, 1080, getApplicationContext(), new ItemHelper.OnCompleteListener() {
                    @Override
                    public void onFetched(Bitmap image, Set<Integer> colors, List<String> labels) {
                        b.loader.setVisibility(View.GONE);
                        b.imageView.setImageBitmap(image);
                        inflateColorChips(colors);
                        inflateLabelChips(labels);
                    }

                    @Override
                    public void onError(String error) {
                        b.loader.setVisibility(View.GONE);
                        new MaterialAlertDialogBuilder(MainActivity.this)
                                .setTitle("Error")
                                .setMessage(error)
                                .show();
                    }
                });


    //Label Chips------------------------------------------------------

    private void testLabelChips() {

        //drawable to bitmap i.e generate bitmap from drawable
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.field);

        //image from bitmap
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        ImageLabeler labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
        labeler.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
                    @Override
                    public void onSuccess(List<ImageLabel> labels) {
                        // Task completed successfully
                        inflateLabelChips(labels);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        new MaterialAlertDialogBuilder(MainActivity.this)
                                .setTitle("Labels Fetched")
                                .setMessage(e.toString())
                                .show();
                    }
                });
    }

    private void inflateLabelChips(List<ImageLabel> labels) {
        for (ImageLabel label : labels){
            ChipLabelBinding binding = ChipLabelBinding.inflate(getLayoutInflater());
            binding.getRoot().setText(label.getText());
            b.labelChips.addView(binding.getRoot());
        }
    }


    //Color Chips-----------------------------------------------------

    private void testColorChips() {

        //drawable to bitmap i.e generate bitmap from drawable
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.field);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                // Use generated instance
                inflateColorChips(p);
            }
        });
    }

    private void inflateColorChips(Palette p) {
        for (int color : getColorsFromPalette(p)){
            ChipColorBinding binding = ChipColorBinding.inflate(getLayoutInflater());
            binding.getRoot().setChipBackgroundColor(ColorStateList.valueOf(color));
            b.colorChips.addView(binding.getRoot());
        }
    }

    private Set<Integer> getColorsFromPalette(Palette p) {

        // We have used set as there is no duplicacy in set
        // so we don't have to remove zero

        Set<Integer> colors = new HashSet<>();

        colors.add(p.getVibrantColor(0));
        colors.add(p.getLightVibrantColor(0));
        colors.add(p.getDarkVibrantColor(0));

        colors.add(p.getMutedColor(0));
        colors.add(p.getLightMutedColor(0));
        colors.add(p.getDarkMutedColor(0));

        colors.remove(0);

        return colors;
    }


    //Test Dialog Box-------------------------------------------------

    private void testDialog() {
        DialogAddImageBinding binding = DialogAddImageBinding.inflate(getLayoutInflater());

        AlertDialog dialog = new MaterialAlertDialogBuilder(this, R.style.CustomDialogTheme)
                .setView(binding.getRoot())
                .show();

        binding.fetchImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inputDimensionsRoot.setVisibility(View.GONE);
                binding.progressIndicatorRoot.setVisibility(View.VISIBLE);

                new Handler()
                        .postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                binding.progressIndicatorRoot.setVisibility(View.GONE);
                                binding.mainRoot.setVisibility(View.VISIBLE);
                            }
                        }, 2000);
            }
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    */
}
