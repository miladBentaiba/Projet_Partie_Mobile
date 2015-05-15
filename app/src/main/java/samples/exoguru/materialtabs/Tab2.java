package samples.exoguru.materialtabs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab2 extends Fragment {

    ImageView img ;
    ImageView add_video;
    ImageView add_picture;
    VideoView video;
    private MediaController mediaControls;
    TextView utilisateur;
    TextView time;
    TextView contenu;
    Button bouton_publier;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_publier_statut,container,false);

        video = (VideoView)v.findViewById(R.id.video_view);
        if (mediaControls == null) {
            mediaControls = new MediaController(getActivity());
        }
        video.setMediaController(mediaControls);
        add_video = (ImageView)v.findViewById(R.id.add_video);
        add_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Log.e("add_video", "on the click of the add_video");
                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("video/*");
                startActivityForResult(pickIntent, 3);
                Tab2.this.img.setVisibility(View.GONE);
                Tab2.this.video.setVisibility(View.VISIBLE);

            }
        });

        img = (ImageView)v.findViewById(R.id.pic);
        img.setImageResource(R.drawable.deux);

        add_picture = (ImageView)v.findViewById(R.id.add_pic);
        add_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                selectImage();

            }
        });

        final EditText textAPublier = (EditText)v.findViewById(R.id.statut_added);

        utilisateur = (TextView)v.findViewById(R.id.user);
        utilisateur.setText("Milad");
        time = (TextView)v.findViewById(R.id.time);

        contenu = (TextView)v.findViewById(R.id.text);

        bouton_publier = (Button)v.findViewById(R.id.publier);
        bouton_publier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Tab2.this.contenu.setText(textAPublier.getText().toString());
                time.setText("Dans un instant");
                textAPublier.setVisibility(View.GONE);
            }
        });
        return v;
    }


    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                    Tab2.this.video.setVisibility(View.GONE);
                    Tab2.this.img.setVisibility(View.VISIBLE);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                    // Publier_statut.this.add_video.setVisibility(View.GONE);
                    Tab2.this.video.setVisibility(View.GONE);
                    Tab2.this.img.setVisibility(View.VISIBLE);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    img.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getActivity().getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path", picturePath + "");
                img.setImageBitmap(thumbnail);
            }else if(requestCode == 3)
            {
                Uri selectedVideo = data.getData();
                Uri mVideoURI = data.getData();
                video.setVideoURI(mVideoURI);
                /*String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedVideo,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path", picturePath + "");
                img.setImageBitmap(thumbnail);*/
            }
        }
    }

}