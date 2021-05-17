package com.example.timedrive.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.timedrive.R;

public class ProfileFragment extends Fragment {

    private ImageView profileIcon;
    private AppCompatTextView changePhoto;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        profileIcon = root.findViewById(R.id.itemPhoto);
        profileIcon.setImageResource(R.mipmap.ic_egorov);

        changePhoto = root.findViewById(R.id.ChangeEgorov);
        changePhoto.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Это лучшее фото,\n которое можно сюда поставить!\n " +
                   "Вам не следует менять его!" ,Toast.LENGTH_LONG).show();
        });

        return root;
    }


}

