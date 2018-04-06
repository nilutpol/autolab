package com.nordicoverseas.nilutpol.autolab;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Body.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Body#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Body extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static final int REQUEST_IMAGE_CAPTURE = 1;

    int btn_index;

    static final int REQUEST_IMAGE_CAPTURE_FRONT = 1;
    static final int REQUEST_IMAGE_CAPTURE_BACK = 2;
    static final int REQUEST_IMAGE_CAPTURE_LEFT = 3;
    static final int REQUEST_IMAGE_CAPTURE_RIGHT = 4;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Body() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Customer.
     */
    // TODO: Rename and change types and number of parameters
    public static Body newInstance(int param1, String param2) {
        Body fragment = new Body();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body, container, false);

        rootView.findViewById(R.id.btn_front).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                take_picture(view, REQUEST_IMAGE_CAPTURE_FRONT);
            }
        });

        rootView.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                take_picture(view, REQUEST_IMAGE_CAPTURE_BACK);
            }
        });

        rootView.findViewById(R.id.btn_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                take_picture(view, REQUEST_IMAGE_CAPTURE_LEFT);
            }
        });

        rootView.findViewById(R.id.btn_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                take_picture(view, REQUEST_IMAGE_CAPTURE_RIGHT);
            }
        });
        return rootView;
    }

    public void take_picture(View view, int index) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        btn_index = index; // store which button was clicked

        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView image_view;

            switch (btn_index) {
                case REQUEST_IMAGE_CAPTURE_FRONT:
                    image_view = getView().findViewById(R.id.imageView_front);
                    break;
                case REQUEST_IMAGE_CAPTURE_BACK:
                    image_view = getView().findViewById(R.id.imageView_back);
                    break;
                case REQUEST_IMAGE_CAPTURE_LEFT:
                    image_view = getView().findViewById(R.id.imageView_left);
                    break;
                case REQUEST_IMAGE_CAPTURE_RIGHT:
                    image_view = getView().findViewById(R.id.imageView_right);
                    break;
                    default:
                        return;
            }

            image_view.setImageBitmap(imageBitmap);
        }
    }
}
